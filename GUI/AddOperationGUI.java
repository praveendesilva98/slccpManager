
package GUI;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JTextField;
import main.Class.DAO.OperationDAO;
import main.Class.Operation;


public class AddOperationGUI extends javax.swing.JFrame {


    Operation operation = new Operation();
    OperationDAO operationDAO = new OperationDAO();
    
    public AddOperationGUI() 
    {
        initComponents();
 
        recipientLabel.setVisible(true);
        givenToLabel.setVisible(false);
        payerLabel.setVisible(false);
        recipient.setVisible(true);
        payer.setVisible(false);
        given_to.setVisible(false);
        cp.setVisible(false);

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Header = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        Dashboard = new javax.swing.JPanel();
        amountLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        typeLabel = new javax.swing.JLabel();
        payer = new javax.swing.JSpinner();
        date = new com.toedter.calendar.JDateChooser();
        location = new javax.swing.JComboBox<>();
        reasonLabel = new javax.swing.JLabel();
        methodLabel = new javax.swing.JLabel();
        locationLabel = new javax.swing.JLabel();
        recipientLabel = new javax.swing.JLabel();
        payerLabel = new javax.swing.JLabel();
        givenToLabel = new javax.swing.JLabel();
        recipient = new javax.swing.JTextField();
        type = new javax.swing.JComboBox<>();
        method = new javax.swing.JComboBox<>();
        reason = new javax.swing.JTextField();
        given_to = new javax.swing.JSpinner();
        add = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        message = new javax.swing.JLabel();
        amount = new javax.swing.JFormattedTextField();
        cp = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        Header.setBackground(new java.awt.Color(154, 159, 0));
        Header.setPreferredSize(new java.awt.Dimension(500, 50));
        Header.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                HeaderMouseDragged(evt);
            }
        });
        Header.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                HeaderMousePressed(evt);
            }
        });
        Header.setLayout(new java.awt.BorderLayout());

        title.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        title.setForeground(new java.awt.Color(240, 240, 240));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("ADD A NEW OPERATION");
        Header.add(title, java.awt.BorderLayout.CENTER);

        getContentPane().add(Header, java.awt.BorderLayout.PAGE_START);

        Dashboard.setBackground(new java.awt.Color(4, 9, 84));
        Dashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        amountLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        amountLabel.setForeground(new java.awt.Color(240, 240, 240));
        amountLabel.setText("Amount (€)");
        Dashboard.add(amountLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        dateLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dateLabel.setForeground(new java.awt.Color(240, 240, 240));
        dateLabel.setText("Date");
        Dashboard.add(dateLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        typeLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        typeLabel.setForeground(new java.awt.Color(240, 240, 240));
        typeLabel.setText("Type");
        Dashboard.add(typeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        payer.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Dashboard.add(payer, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, 220, 30));

        date.setDateFormatString("yyyy-MM-dd");
        Dashboard.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 220, 30));

        location.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        location.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SLCCP Bank Account", "Saving Account (ADP)", "Liquid (Cash)" }));
        Dashboard.add(location, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, 230, 30));

        reasonLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        reasonLabel.setForeground(new java.awt.Color(240, 240, 240));
        reasonLabel.setText("Reason");
        Dashboard.add(reasonLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

        methodLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        methodLabel.setForeground(new java.awt.Color(240, 240, 240));
        methodLabel.setText("Method");
        Dashboard.add(methodLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, -1));

        locationLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        locationLabel.setForeground(new java.awt.Color(240, 240, 240));
        locationLabel.setText("Location");
        Dashboard.add(locationLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, -1, -1));

        recipientLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        recipientLabel.setForeground(new java.awt.Color(240, 240, 240));
        recipientLabel.setText("Recipient");
        Dashboard.add(recipientLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, -1));

        payerLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        payerLabel.setForeground(new java.awt.Color(240, 240, 240));
        payerLabel.setText("Payer (ID Contact)");
        Dashboard.add(payerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, -1));

        givenToLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        givenToLabel.setForeground(new java.awt.Color(240, 240, 240));
        givenToLabel.setText("Given to (ID Contact)");
        Dashboard.add(givenToLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, -1, -1));

        recipient.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Dashboard.add(recipient, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 350, 270, 30));

        type.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Expenditure", "Income" }));
        type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeActionPerformed(evt);
            }
        });
        Dashboard.add(type, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 230, 30));

        method.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        method.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CB", "Bank Check", "Cash", "Bank Transfer", "Other" }));
        Dashboard.add(method, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 230, 30));

        reason.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Dashboard.add(reason, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 250, 30));

        given_to.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Dashboard.add(given_to, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 400, 220, 30));

        add.setBackground(new java.awt.Color(3, 11, 116));
        add.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add.setForeground(new java.awt.Color(240, 240, 240));
        add.setText("ADD");
        add.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addMouseExited(evt);
            }
        });
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        Dashboard.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 470, 110, 40));

        cancel.setBackground(new java.awt.Color(3, 11, 116));
        cancel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cancel.setForeground(new java.awt.Color(240, 240, 240));
        cancel.setText("CANCEL");
        cancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelMouseExited(evt);
            }
        });
        Dashboard.add(cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 470, 110, 40));

        message.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        message.setForeground(new java.awt.Color(240, 240, 0));
        Dashboard.add(message, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 440, 20));
        Dashboard.add(amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 230, 30));

        cp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cp.setForeground(new java.awt.Color(240, 240, 240));
        cp.setText("CP");
        cp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpActionPerformed(evt);
            }
        });
        Dashboard.add(cp, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 200, 60, 30));

        getContentPane().add(Dashboard, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(500, 585));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void changeColor(JButton hover, Color rand)
    {   
        hover.setBackground(rand);
    }
    
    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addActionPerformed

    private void cancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseClicked
        this.setVisible(false);
        dispose();
    }//GEN-LAST:event_cancelMouseClicked

    private void cancelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseEntered
        changeColor(cancel, new Color(4,9,84));
    }//GEN-LAST:event_cancelMouseEntered

    private void cancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseExited
        changeColor(cancel, new Color(3,11,116));
    }//GEN-LAST:event_cancelMouseExited

    private void addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseClicked
        double money = Double.parseDouble(amount.getText());
        operation.setAmount(money);
        operation.setType(type.getSelectedItem().toString());
        operation.setDate(((JTextField)date.getDateEditor().getUiComponent()).getText());
        operation.setReason(reason.getText());
        operation.setMethod(method.getSelectedItem().toString());
        operation.setLocation(location.getSelectedItem().toString());
        operation.setPaidBy(Integer.parseInt(payer.getValue().toString()));
        operation.setGivenTo(Integer.parseInt(given_to.getValue().toString()));
        operation.setPaidTo(recipient.getText());
        
        operationDAO.add(operation, message, HomeGUI.userLabel);
    }//GEN-LAST:event_addMouseClicked

    private void addMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseEntered
        changeColor(add, new Color(4,9,84));
    }//GEN-LAST:event_addMouseEntered

    private void addMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseExited
        changeColor(add, new Color(3,11,116));
    }//GEN-LAST:event_addMouseExited
    static int xx, yy;
    private void HeaderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HeaderMousePressed
        xx = evt.getX();
        yy = evt.getY();
    }//GEN-LAST:event_HeaderMousePressed

    private void HeaderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HeaderMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x-xx, y-yy);
    }//GEN-LAST:event_HeaderMouseDragged

    private void typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeActionPerformed
        if("Expenditure".equals(type.getSelectedItem().toString()))
        {
            recipientLabel.setVisible(true);
            givenToLabel.setVisible(false);
            payerLabel.setVisible(false);
            recipient.setVisible(true);
            payer.setVisible(false);
            given_to.setVisible(false);
            cp.setVisible(false);
            
        }
        else
        {
            recipientLabel.setVisible(false);
            givenToLabel.setVisible(true);
            payerLabel.setVisible(true);
            recipient.setVisible(false);
            payer.setVisible(true);
            given_to.setVisible(true);
            cp.setVisible(true);
            
            
        }
        
    }//GEN-LAST:event_typeActionPerformed

    private void cpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cpActionPerformed
        if(cp.isSelected())
        {
            reason.setText("Client Payment");
        }
        else
        {
            reason.setText("");
        }
    }//GEN-LAST:event_cpActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddOperationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddOperationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddOperationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddOperationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddOperationGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Dashboard;
    private javax.swing.JPanel Header;
    private javax.swing.JButton add;
    private javax.swing.JFormattedTextField amount;
    private javax.swing.JLabel amountLabel;
    private javax.swing.JButton cancel;
    private javax.swing.JRadioButton cp;
    private com.toedter.calendar.JDateChooser date;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel givenToLabel;
    private javax.swing.JSpinner given_to;
    private javax.swing.JComboBox<String> location;
    private javax.swing.JLabel locationLabel;
    private javax.swing.JLabel message;
    private javax.swing.JComboBox<String> method;
    private javax.swing.JLabel methodLabel;
    private javax.swing.JSpinner payer;
    private javax.swing.JLabel payerLabel;
    private javax.swing.JTextField reason;
    private javax.swing.JLabel reasonLabel;
    private javax.swing.JTextField recipient;
    private javax.swing.JLabel recipientLabel;
    private javax.swing.JLabel title;
    private javax.swing.JComboBox<String> type;
    private javax.swing.JLabel typeLabel;
    // End of variables declaration//GEN-END:variables
}
