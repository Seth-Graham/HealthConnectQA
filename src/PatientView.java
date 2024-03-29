import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public final class PatientView extends javax.swing.JFrame {
    ResultSet rs = null;
    PreparedStatement pst = null;
    int curRow = 0;
    String username, userType;
    DefaultListModel model = new DefaultListModel();
    int index;
    int requestID;

    /**
     * Creates new form PatientView
     *
     * @param patient
     */
    public PatientView(String patient) {
        initComponents();

        Database.getConnection();

        username = patient;
        setUsername(patient);
        userType = "Patient";
        setUserType(userType);
        jList1.setVisible(false);

    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRequestID() {
        return this.requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public String getUserType() {
        return this.userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    // TODO: Change back to private once testing has been completed.
    public void initComponents() {

        InProgressButton = new javax.swing.JButton();
        closedButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollBar1 = new javax.swing.JScrollBar();
        jLabel1 = new javax.swing.JLabel();
        openRequest = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        newButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        InProgressButton.setText("In Progress Requests");
        InProgressButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InProgressButtonActionPerformed(evt);
            }
        });

        closedButton.setText("Closed Requests");
        closedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closedButtonActionPerformed(evt);
            }
        });

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {};

            public int getSize() {
                return strings.length;
            }

            public Object getElementAt(int i) {
                return strings[i];
            }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel1.setFont(new java.awt.Font("Eras Demi ITC", 3, 24)); // NOI18N
        jLabel1.setText("Your Request History");

        openRequest.setText("Open Selected Request");
        openRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openRequestActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Papyrus", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 255));
        jLabel7.setText("HealthConnect");

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        newButton.setText("New Requests");
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(openRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(234, 234, 234)
                                                .addComponent(backButton))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 703, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(newButton, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(65, 65, 65)
                                                .addComponent(InProgressButton)
                                                .addGap(67, 67, 67)
                                                .addComponent(closedButton)
                                                .addGap(115, 115, 115)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addComponent(jLabel7))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(InProgressButton)
                                        .addComponent(closedButton)
                                        .addComponent(newButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(openRequest)
                                        .addComponent(backButton))
                                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>

    /**
     * This method will handle the event when the In Progress button is clicked, which will select
     * the users Requests from the database where the status has been set to "In Progress"
     * @param evt
     */
    // TODO: Change to private once testing has been completed.
    public void InProgressButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        jLabel1.setText("Your Opened Requests");
        jList1.setVisible(true);
        String element;
        String sql = "select RID,Date from Request where Status=? and PUsername=?";
        model.removeAllElements();
        element = "RID        Date";
        model.addElement(element);
        try {
            pst = Database.connection.prepareStatement(sql);
            pst.setString(1, "In Progress");
            pst.setString(2, username);
            rs = pst.executeQuery();
            if (rs.next()) {
                //JOptionPane.showMessageDialog(null, "Username and Password is correct");
                element = rs.getString("RID") + "        " + rs.getString("Date");
                model.addElement(element);
                while (rs.next()) {
                    element = rs.getString("RID") + "        " + rs.getString("Date");
                    model.addElement(element);
                }
                jList1.setModel(model);
            } else {
                JOptionPane.showMessageDialog(null, "No requests are in progress.");
            }
            rs.close();
            pst.close();
        } catch (SQLException e) { JOptionPane.showMessageDialog(null, e); }
    }

    /**
     * This method will handle the event when the New Request button is clicked, which will select
     * the users Requests from the database where the status has been set to "New"
     * @param evt
     */
    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        jLabel1.setText("Your New Requests");
        jList1.setVisible(true);
        String element;
        String sql = "select RID,Date from Request where Status=? and PUsername=?";
        model.removeAllElements();
        element = "RID        Date";
        model.addElement(element);
        try {
            pst = Database.connection.prepareStatement(sql);
            pst.setString(1, "New");
            pst.setString(2, username);
            rs = pst.executeQuery();
            if (rs.next()) {
                //JOptionPane.showMessageDialog(null, "Username and Password is correct");
                element = rs.getString("RID") + "        " + rs.getString("Date");
                model.addElement(element);
                while (rs.next()) {
                    element = rs.getString("RID") + "        " + rs.getString("Date");
                    model.addElement(element);
                }
                jList1.setModel(model);
            } else {
                JOptionPane.showMessageDialog(null, "No new requests.");

            }
            rs.close();
            pst.close();
        } catch (SQLException e) { JOptionPane.showMessageDialog(null, e); }
    }
    /**
     * This method will handle the event when the Closed Requests button is clicked, which will select
     * the users Requests from the database where the status has been set to "Closed"
     * @param evt
     */

    private void closedButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        jLabel1.setText("Your Closed Requests");
        jList1.setVisible(true);
        String element;
        String sql = "select RID,Date from Request where Status=? and PUsername=?";
        model.removeAllElements();
        element = "RID        Date";
        model.addElement(element);
        try {
            pst = Database.connection.prepareStatement(sql);
            pst.setString(1, "Closed");
            pst.setString(2, username);
            rs = pst.executeQuery();
            if (rs.next()) {
                //JOptionPane.showMessageDialog(null, "Username and Password is correct");
                element = rs.getString("RID") + "        " + rs.getString("Date");
                model.addElement(element);
                while (rs.next()) {
                    element = rs.getString("RID") + "        " + rs.getString("Date");
                    model.addElement(element);
                }
                jList1.setModel(model);
            } else {
                JOptionPane.showMessageDialog(null, "No requests have been closed.");

            }
            rs.close();
            pst.close();
        } catch (SQLException e) { JOptionPane.showMessageDialog(null, e); }
    }

    /**
     * This method will handle the event when the back button is clicked, which will return the user
     * to the previous menu.
     * @param evt
     */
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        dispose();
        Profile p = new Profile(username);
        p.setVisible(true);
    }

    /**
     * This method will handle the event when the open request button is clicked,
     * which will open the selected request from the list.
     *
     * @param evt
     */
    private void openRequestActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if (jList1.getSelectedIndex() != -1) {
            String temp_requestID = jList1.getSelectedValue().toString();
            temp_requestID = temp_requestID.substring(0, 3);
            requestID = Integer.parseInt(temp_requestID);
            setRequestID(requestID);
            RequestConversation r = new RequestConversation(requestID, username, userType);
            dispose();
            r.setVisible(true);
        } else
            JOptionPane.showMessageDialog(null, "Please select a request");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        SwingUtilities.invokeLater(()->{
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (Exception ignored) {
            }
        });
        //</editor-fold>
        final NewJFrame s = new NewJFrame();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PatientView(s.getUsername()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    // TODO: Change back to private after testing has been completed.
    public javax.swing.JButton InProgressButton;
    public javax.swing.JButton backButton;
    public javax.swing.JButton closedButton;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JList jList1;
    public javax.swing.JScrollBar jScrollBar1;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JButton newButton;
    public javax.swing.JButton openRequest;
    // End of variables declaration
}

