import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseTestMethods {

    // **** Request Table Methods ****

    /**
     * Create the default request table for use in the tests.
     */
    public static void defaultRequestTable() {
        // Set the database to the expected default state.
        try {
            Database.getConnection();
            Statement s = Database.connection.createStatement();


            s.execute("DELETE FROM Request");

            String sql = "CREATE TABLE IF NOT EXISTS Request (\n" +
                    "   RID VARCHAR(255) NOT NULL, \n " +
                    "   Status VARCHAR(255) NOT NULL, \n" +
                    "   Date VARCHAR(255) NOT NULL, \n" +
                    "   PUsername VARCHAR(255) NOT NULL)";
            s.executeUpdate(sql);

            insertRequest("100", "New", "stg");
            insertRequest("101", "New", "stg");
            insertRequest("102", "In Progress", "stg");
            insertRequest("103", "In Progress", "stg");
            insertRequest("104", "Closed","stg");
            insertRequest("105", "Closed", "stg");

        } catch (SQLException ignored) {
        }
    }

    /**
     * Method to insert new requests into the request table.
     *
     * @param rid
     * @param status
     * @param pUsername
     */
    public static void insertRequest(String rid, String status, String pUsername) {

        String sql = "INSERT INTO Request(RID, Status, Date, PUsername) VALUES(?, ?, ?, ?)";

        try {
            PreparedStatement pst = Database.connection.prepareStatement(sql);
            pst.setString(1, rid);
            pst.setString(2, status);
            Date date = new Date();
            String date1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date);
            pst.setString(3, date1);
            pst.setString(4, pUsername);
            pst.executeUpdate();
            pst.close();
        } catch (SQLException se) {
            se.printStackTrace();
            System.out.println("Exception on insertRequest()");
        }
    }

    /**
     * Check to see if the input string is located within the request table.
     *
     * @param testString Desired RID
     * @return true/false
     */
    public static boolean isRIDAvailableRequest(String testString) {
        boolean returnVal = false;

        try {

            PreparedStatement pst = Database.connection.prepareStatement("select * from Request where RID = ?");
            pst.setString(1, testString);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) returnVal = true;

        } catch (SQLException e) {
            System.out.println("SQLException in isRIDAvailableRequest: " + e.getMessage());
        }

        System.out.println(returnVal);
        return returnVal;
    }

    /**
     * Check to see if the RID is located within the Message table.
     *
     * @param testString Desired RID.
     * @return true/false.
     */
    public static boolean isRIDAvailableMessage(String testString) {
        boolean returnVal = false;

        try {

            PreparedStatement pst = Database.connection.prepareStatement("select * from Message where RID = ?");
            pst.setString(1, testString);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) returnVal = true;

        } catch (SQLException e) {
            System.out.println("SQLException in isRIDAvailableMessage: " + e.getMessage());
        }

        System.out.println(returnVal);
        return returnVal;
    }

    /**
     * Method to return if the desired status is located within the status table.
     *
     * @param testString Desired status.
     * @return true/false.
     */
    public static boolean isStatusAvailable(String testString) {
        boolean returnVal = false;

        try {

            PreparedStatement pst = Database.connection.prepareStatement("select * from Request where Status = ?");
            pst.setString(1, testString);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) returnVal = true;
        } catch (SQLException e) {
            System.out.println("SQLException in isStatusAvailable: " + e.getMessage());
        }

        System.out.println(returnVal);
        return returnVal;
    }

    /**
     *Method to return the status of the desired Message from the Message table.
     *
     * @param rid RID of desired message.
     * @return String value of the status.
     */
    public static String getStatus(String rid) {
        String result = "";

        try {
            PreparedStatement pst = Database.connection.prepareStatement("SELECT Status FROM Request WHERE RID = ?");
            pst.setString(1, rid);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) result = rs.getString("Status");
        } catch (SQLException e) {
            System.out.println("SQLException in getStatus(): " + e.getMessage());
        }
        return result;
    }

    /**
     * Method used to clear the request table.
     */
    public static void wipeRequests() {

        try {
            Database.getConnection();
            Statement s = Database.connection.createStatement();
            s.execute("DELETE FROM Request");
            s.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    /**
     * method used to determine the largest RID located within the Test table.
     *
     * @return Maximum RID int.
     */
    public static int getMaxRID() {
        int maxID = 0;

        try {
            Database.getConnection();
            Statement s = Database.connection.createStatement();

            String sql = "SELECT MAX(RID) FROM Request";
            s.execute(sql);
            ResultSet rs = s.getResultSet();

            while (rs.next()) {
                maxID = rs.getInt(1);
            }
            rs.close();
            s.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return maxID;
    }

    // **** Patient Table Methods ****

    /**
     * Generate the default patient table used for testing.
     */
    public static void defaultPatientTable() {
        // Set the database to the expected default state.
        try {
            Database.getConnection();
            Statement s = Database.connection.createStatement();

            s.execute("DELETE FROM Patient");

            String sql = "CREATE TABLE IF NOT EXISTS Patient (\n" +
                    "   Name VARCHAR(255) NOT NULL, \n " +
                    "   Username VARCHAR(255) NOT NULL, \n" +
                    "   Password VARCHAR(255) NOT NULL)";
            s.execute(sql);

            insertPatient("Seth", "stg", "1234");
        } catch (SQLException ignored) {
        }
    }

    /**
     * Method used to insert a new patient into the Patient table.
     *
     * @param name String value for patient name.
     * @param username String value for patient username.
     * @param password String value for patient password.
     */
    public static void insertPatient(String name, String username, String password) {

        try {
            PreparedStatement pst = Database.connection.prepareStatement("INSERT INTO Patient(Name, Username, Password) VALUES(?, ?, ?)");
            pst.setString(1, name);
            pst.setString(2, username);
            pst.setString(3, password);
            pst.execute();
            pst.close();
        } catch (SQLException se) {
            se.printStackTrace();
            System.out.println("Exception in insertPatient()");
        }
    }

    /**
     * Method used to determine if the desired patient exists within the Patient table.
     *
     * @param testString String value of the desired patient.
     * @return true/false.
     */
    public static boolean isPatientAvailable(String testString) {
        boolean returnVal = false;

        try {

            PreparedStatement pst = Database.connection.prepareStatement("select * from Patient where Username = ?");
            pst.setString(1, testString);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) returnVal = true;

        } catch (SQLException e) {
            System.out.println("SQLException in isPatientAvailable: " + e.getMessage());
        }

        System.out.println(returnVal);
        return returnVal;
    }

    // **** Doctor Table Methods ****

    /**
     * Method used to generate the default Doctor table used for testing.
     */
    public static void defaultDoctorTable() {
        // Set the database to the expected default state.
        try {
            Database.getConnection();
            Statement s = Database.connection.createStatement();

            s.execute("DELETE FROM Doctor");

            String sql = "CREATE TABLE IF NOT EXISTS Doctor (\n" +
                    "   Username VARCHAR(255) NOT NULL, \n " +
                    "   Password VARCHAR(255) NOT NULL)";
            s.execute(sql);

            insertDoctor("drstg", "1234");
        } catch (SQLException ignored) {
        }
    }

    /**
     * Method used to insert a new doctor into the Doctor table.
     *
     * @param username String value of the doctors username.
     * @param password String value of the doctors password.
     */
    public static void insertDoctor(String username, String password) {

        try {
            PreparedStatement pst = Database.connection.prepareStatement("INSERT INTO Doctor(Username, Password) VALUES(?, ?)");
            pst.setString(1, username);
            pst.setString(2, password);
            pst.execute();
            pst.close();
        } catch (SQLException se) {
            se.printStackTrace();
            System.out.println("Exception occured in insertDoctor()");
        }
    }

    /**
     * Method used to determine whether a doctor already exists within the Doctor table.
     *
     * @param testString String value of the desired doctor.
     * @return true/false.
     */
    public static boolean isDoctorAvailable(String testString) {
        boolean returnVal = false;

        try {

            PreparedStatement pst = Database.connection.prepareStatement("select * from Doctor where Username = ?");
            pst.setString(1, testString);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) returnVal = true;

        } catch (SQLException e) {
            System.out.println("SQLException in isDoctorAvailable: " + e.getMessage());
        }

        System.out.println(returnVal);
        return returnVal;
    }

    // **** Message Table Methods ****

    /**
     * Method used to generate the default message table used for testing.
     */
    public static void defaultMessageTable() {
        // Set the database to the expected default state.
        try {
            Database.getConnection();
            Statement s = Database.connection.createStatement();

            s.execute("DELETE FROM Message");

            String sql = "CREATE TABLE IF NOT EXISTS Message (\n" +
                    "   RID VARCHAR(255) NOT NULL, \n " +
                    "   DUsername VARCHAR(255), \n" +
                    "   TimeStamp VARCHAR(255) NOT NULL, \n" +
                    "   Message VARCHAR(255) NOT NULL)";
            s.execute(sql);

            insertMessage("100", "drstg", "This is a test 1.");
            insertMessage("101", "drstg", "This is a test 2.");
            insertMessage("101", "drstg", "This is a test 2a.");
            insertMessage("102", "drstg", "This is a test 3.");
            insertMessage("103", "drstg", "This is a test 4.");
            insertMessage("104", "drstg", "This is a test 5.");
            insertMessage("105", "drstg", "This is a test 6.");
        } catch (SQLException ignored) {
        }
    }

    /**
     * Method used to insert a new message into the Message table.
     *
     * @param rid String value of the desired RID.
     * @param dUsername String value of the desired doctors username.
     * @param message String value of the new message.
     */
    public static void insertMessage(String rid, String dUsername, String message) {

        try {
            PreparedStatement pst = Database.connection.prepareStatement("INSERT INTO Message(RID, DUsername, TimeStamp, Message) VALUES(?, ?, ?, ?)");
            pst.setString(1, rid);
            pst.setString(2, dUsername);
            Date date = new Date();
            String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date);
            pst.setString(3, timeStamp);
            pst.setString(4, message);
            pst.execute();
            pst.close();
        } catch (SQLException se) {
            se.printStackTrace();
            System.out.println("Exception in insertMessage");
        }
    }

    /**
     * Method used to determine whether a message exists within the Message table.
     *
     * @param testString String value of the desired message.
     * @return true/false.
     */
    public static boolean isMessageAvailable(String testString) {
        boolean returnVal = false;

        try {

            PreparedStatement pst = Database.connection.prepareStatement("select * from Message where Message= ?");
            pst.setString(1, testString);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) returnVal = true;

        } catch (SQLException e) {
            System.out.println("SQLException in isMessageAvailable: " + e.getMessage());
        }

        System.out.println(returnVal);
        return returnVal;
    }
}
