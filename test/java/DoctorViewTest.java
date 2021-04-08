import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.print.Doc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class DoctorViewTest {

    @BeforeEach
    @AfterEach
    public void defaultRequestTable() {
        // Set the database to the expected default state.
        try {
            Database.getConnection();
            Statement s = Database.connection.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS Request (\n" +
                    "   RID VARCHAR(255) NOT NULL, \n " +
                    "   Status VARCHAR(255) NOT NULL, \n" +
                    "   Date VARCHAR(255) NOT NULL, \n" +
                    "   PUsername VARCHAR(255) NOT NULL)";
            s.execute("DROP TABLE Request");
            s.execute(sql);
            s.execute("INSERT INTO Request (RID, Status, Date, PUsername) VALUES "
                    + "('100', 'New', '2019-07-14', 'testPatient' ),"
                    + "('101', 'New', '2019-08-15', 'testPatient' ),"
                    + "('102', 'In Progress', '2021-07-20', 'testPatient'),"
                    + "('103', 'In Progress', '2021-08-20', 'testPatient'),"
                    + "('104', 'Closed', '2021-04-03', 'testPatient');");
        } catch (SQLException ignored) {}
    }

    @Test
    void getUsername() {

        DoctorView doctorView = new DoctorView("testDoctor");

        String actualValue = "testDoctor";
        String compareValue = doctorView.getUsername();

        assertEquals(actualValue, compareValue);
    }

    @Test
    void getSetRID() {
        DoctorView doctorView = new DoctorView("testDoctor");

        int rid = 110;

        doctorView.setRequestID(rid);

        int compareValue = doctorView.getRequestID();

        assertEquals(rid, compareValue);
    }

    @Test
    void getUserType() {

        DoctorView doctorView = new DoctorView("testDoctor");

        String userType = "Doctor";

        assertEquals(userType, doctorView.getUserType());
    }

    @Test
    void newRequestButtonActionPerformedPassed() {

    DoctorView doctorView = new DoctorView("testDoctor");

    doctorView.newRequestButton.doClick();

    assertNotNull(doctorView.model.get(0));

    assertDoesNotThrow(()->{});
    }

    @Test
    void newRequestButtonActionPerformedFailed() {

        DoctorView doctorView = new DoctorView("testDoctor");

        wipeRequests();

        doctorView.newRequestButton.doClick();

        assertFalse(isRIDAvailable("100"));

        assertDoesNotThrow(()-> {});
    }

    @Test
    void inProgressButtonActionPerformedPassed() {

        DoctorView doctorView = new DoctorView("testDoctor");

        doctorView.inProgressButton.doClick();

        assertNotNull(doctorView.model.get(0));

        assertDoesNotThrow(()->{});
    }

    @Test
    void inProgressButtonActionPerformedFailed() {

        DoctorView doctorView = new DoctorView("testDoctor");

        wipeRequests();

        doctorView.inProgressButton.doClick();

        assertFalse(isRIDAvailable("103"));

        assertDoesNotThrow(()-> {});
    }

    public void wipeRequests() {

        try {
            Database.getConnection();
            Statement s = Database.connection.createStatement();
            s.execute("DELETE FROM Request");
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static boolean isRIDAvailable(String testString) {
        boolean returnVal = false;

        try {

            PreparedStatement pst = Database.connection.prepareStatement("select * from Request where Request = ?");
            pst.setString(1, testString);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) returnVal = true;

        } catch (SQLException e) {
            System.out.println("SQLException in isRequestAvailable: " + e.getMessage());
        }

        System.out.println(returnVal);
        return returnVal;
    }
}