package main.Class.DAO;

import java.sql.*; 
import java.text.NumberFormat;
import java.time.LocalTime;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import main.Class.Operation;
import main.Class.Implements.ContactImp;

public class OperationDAO implements DAO<Operation>
{
    
    private String url;
    private String user;
    private String password;
    
    long millis=System.currentTimeMillis();  
    java.sql.Date date = new java.sql.Date(millis); 
    LocalTime time = LocalTime.now();
    
    ContactImp contact = new ContactImp();
    
    public OperationDAO()
    {
        this.url = "jdbc:mysql://slccpmanager.ccmtouylia9i.eu-west-3.rds.amazonaws.com:3306/slccp?autoReconnect=true&useSSL=false";
        this.user = "praveen";
        this.password = "praveen1998";
    }
    

    @Override
    public void add(Operation operation, JLabel label, JLabel userLabel)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url,user,password);

            String sql = "INSERT INTO operation (amount, date, type, reason, method, location, active) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            if(operation.getType() == "Expenditure")
            {
                statement.setDouble(1, -operation.getAmount());
            }
            else 
            {
                statement.setDouble(1, operation.getAmount());
            }  
            statement.setString(2, operation.getDate());
            statement.setString(3, operation.getType());
            statement.setString(4, operation.getReason());
            statement.setString(5, operation.getMethod());
            statement.setString(6, operation.getLocation());
            statement.setInt(7, 1);
            statement.execute();

            String sql1 = "SELECT * FROM operation WHERE id_operation=(SELECT max(id_operation) FROM operation)";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            ResultSet resultSet1 = statement1.executeQuery();

            while(resultSet1.next())
            {
                operation.setIdOperation(resultSet1.getInt("id_operation"));
                int id_operation = operation.getIdOperation();
                
                if(operation.getType() == "Income")
                {
                    String sql3 = "INSERT INTO income (id_operation, paid_by, given_to) VALUES (?, ?, ?)";
                    PreparedStatement statement3 = connection.prepareStatement(sql3);
                    statement3.setInt(1, id_operation);
                    statement3.setInt(2, operation.getPaidBy());
                    statement3.setInt(3, operation.getGivenTo());
                    statement3.execute();
                }
                else
                {
                    String sql3 = "INSERT INTO payment (id_operation, recipient) VALUES (?, ?)";
                    PreparedStatement statement3 = connection.prepareStatement(sql3);
                    statement3.setInt(1, id_operation);
                    statement3.setString(2, operation.getPaidTo());
                    statement3.execute();                
                }
                
                String sql2 = "INSERT INTO action (user, type, action, date, time) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement statement2 = connection.prepareStatement(sql2);
                statement2.setString(1, userLabel.getText());
                statement2.setString(2, "Add Operation");
                statement2.setString(3, userLabel.getText()+" added a new operation-> amount: "+ operation.getAmount() +", reason: "+ operation.getReason()+", method: "+operation.getMethod()+", location: "+operation.getLocation());
                statement2.setString(4, date.toString());
                statement2.setString(5, time.toString());
                statement2.execute();
            
                label.setText("The operation of " + operation.getAmount() + " € has been successfully added to the Database !");
            }
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            label.setText("ERROR : Attempt to add the operation is unsuccessfull !");
        }
    }
    
    
    @Override
    public void update(Operation operation, int id, String type, String value, JLabel label, JLabel userLabel)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url,user,password);
            
            String sql1 = "SELECT * FROM operation WHERE operation.id_operation = ?";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setInt(1, id);
            ResultSet resultSet = statement1.executeQuery();

            if(resultSet.next())
            {
                String sql = "UPDATE operation SET " + type + " = ? WHERE id_operation = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, value);
                statement.setInt(2, id);
                statement.execute();
                
                String sql2 = "INSERT INTO action (user, type, action, date, time) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement statement2 = connection.prepareStatement(sql2);
                statement2.setString(1, userLabel.getText());
                statement2.setString(2, "Update Operation");
                statement2.setString(3, userLabel.getText()+" updated the "+type+" of the operation n°"+id);
                statement2.setString(4, date.toString());
                statement2.setString(5, time.toString());
                statement2.execute();
                
                label.setText("Payment N°" + id + " has been updated successfully !");
            }
            else
            {
                label.setText("ERROR : No operation is related to that id !\n");
            }

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            label.setText("ERROR : Unable to update the operation !");
        }
    }
    
    
    @Override
    public void delete(Operation operation, int id, JLabel userLabel)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url,user,password);
            
            String sql = "UPDATE operation SET active = ? WHERE id_operation = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, 0);
            statement.setInt(2, id);
            statement.execute();
            
            String sql2 = "INSERT INTO action (user, type, action, date, time) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            statement2.setString(1, userLabel.getText());
            statement2.setString(2, "Delete Operation");
            statement2.setString(3, userLabel.getText()+" deleted the operation n°"+id);
            statement2.setString(4, date.toString());
            statement2.setString(5, time.toString());
            statement2.execute();
           
            System.out.println("The operation n°" + id + " has been deleted from the Database successfully !");
           
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("ERROR : Unable to update the operation !");
        }
    }
    

    public void selectAll(Operation operation, JTable table, JLabel totalBank, JLabel totalCash, JLabel totalSavings)
    {
        try
        {        
            Connection connection = DriverManager.getConnection(url,user,password);
            
            String sql1 = "SELECT id_operation, date, type, reason, method, location, amount FROM operation WHERE active = 1 ORDER BY date DESC";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            ResultSet resultSet1 = statement1.executeQuery();
            
            while(table.getRowCount() > 0)
            {
                ((DefaultTableModel)table.getModel()).removeRow(0);
            }
            
            int col = resultSet1.getMetaData().getColumnCount();

            while(resultSet1.next())
            {
                Object [] rows = new Object[col];
                for(int i = 1; i <= col; i++)
                {
                    rows[i-1] = resultSet1.getObject(i);
                }
                ((DefaultTableModel)table.getModel()).insertRow(resultSet1.getRow() - 1, rows);
                
                String sql2 = "SELECT SUM(amount) as sumamount FROM operation WHERE active = 1 AND location = 'SLCCP Bank Account'";
                PreparedStatement statement2 = connection.prepareStatement(sql2);
                ResultSet resultSet2 = statement2.executeQuery();
                
                while(resultSet2.next())
                {
                    totalBank.setText("0.00 €");
                    double sumAmount = resultSet2.getDouble("sumamount");
                    if(sumAmount != 0d)
                    {
                        totalBank.setText(String.format( "%.2f", sumAmount) + " €");
                    }
                    else
                    {
                        totalBank.setText("0.00 €");
                    }
                    
                    String sql3 = "SELECT SUM(amount) as sumamount FROM operation WHERE active = 1 AND location = 'Liquid (Cash)'";
                    PreparedStatement statement3 = connection.prepareStatement(sql3);
                    ResultSet resultSet3 = statement3.executeQuery();

                    while(resultSet3.next())
                    {
                        totalCash.setText("0.00 €");
                        double sumAmount3 = resultSet3.getDouble("sumamount");
                        if(sumAmount3 != 0d)
                        {
                            totalCash.setText(String.format( "%.2f", sumAmount3) + " €");
                        }
                        else
                        {
                            totalCash.setText("0.00 €");
                        }
                        
                        String sql4 = "SELECT SUM(amount) as sumamount FROM operation WHERE active = 1 AND location = 'Saving Account (ADP)'";
                        PreparedStatement statement4 = connection.prepareStatement(sql4);
                        ResultSet resultSet4 = statement4.executeQuery();

                        while(resultSet4.next())
                        {
                            totalSavings.setText("0.00 €");
                            double sumAmount4 = resultSet4.getDouble("sumamount");
                            if(sumAmount4 != 0d)
                            {
                                totalSavings.setText(String.format( "%.2f", sumAmount4) + " €");
                            }
                            else
                            {
                                totalSavings.setText("0.00 €");
                            }

                        }
                        
                        resultSet4.close();
                        statement4.close();
                    }
                    resultSet3.close();
                    statement3.close();
                    
                }
                resultSet2.close();
                statement2.close();
            }
            resultSet1.close();
            statement1.close();
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            
        }
    }
  
    
    public void operationDetails(Operation operation, int id, JLabel payerLabel, JLabel givenLabel, JLabel recipientLabel)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url,user,password);

            String sql1 = "SELECT * FROM income WHERE id_operation = ?";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setInt(1, id);
            ResultSet resultSet1 = statement1.executeQuery();

            if(resultSet1.next())
            {
                operation.setPaidBy(resultSet1.getInt("paid_by"));
                operation.setGivenTo(resultSet1.getInt("given_to"));

                int id_paid_by = operation.getPaidBy();
                int id_given_to = operation.getGivenTo();

                String sql3 = "SELECT * FROM contacts WHERE id_contact = ?";
                PreparedStatement statement3 = connection.prepareStatement(sql3);
                statement3.setInt(1, id_paid_by);
                ResultSet resultSet3 = statement3.executeQuery();

                if(resultSet3.next())
                {
                    contact.setName(resultSet3.getString("name"));
                    contact.setSurname(resultSet3.getString("surname"));

                    String name_paid_by = contact.getName();
                    String surname_paid_by = contact.getSurname();

                    payerLabel.setText(name_paid_by+" "+surname_paid_by);
                    
                    String sql4 = "SELECT * FROM contacts WHERE id_contact = ?";
                    PreparedStatement statement4 = connection.prepareStatement(sql4);
                    statement4.setInt(1, id_given_to);
                    ResultSet resultSet4 = statement4.executeQuery();

                    if(resultSet4.next())
                    {
                        contact.setName(resultSet4.getString("name"));
                        contact.setSurname(resultSet4.getString("surname"));

                        String name_given_to = contact.getName();
                        String surname_given_to = contact.getSurname();

                        givenLabel.setText(name_given_to+" "+surname_given_to);

                    }
                }

            }

            String sql2 = "SELECT * FROM payment WHERE id_operation = ?";
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            statement2.setInt(1, id);
            ResultSet resultSet2 = statement2.executeQuery();

            if(resultSet2.next())
            {
                operation.setPaidTo(resultSet2.getString("recipient"));
                String recipient = operation.getPaidTo();

                recipientLabel.setText(recipient);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("ERROR : Unable to update the operation !");
        }
    }
    
    public void searchByType(Operation operation, JTable table, String type, String value, JLabel totalBank, JLabel totalCash, JLabel totalSavings)
    {
        try
        {        
            Connection connection = DriverManager.getConnection(url,user,password);
            
            String sql = "SELECT id_operation, date, type, reason, method, location, amount FROM operation WHERE "+type+" LIKE ? AND active = 1 ORDER BY date DESC";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, value+'%');
            ResultSet resultSet = statement.executeQuery();
            
            while(table.getRowCount() > 0)
            {
                ((DefaultTableModel)table.getModel()).removeRow(0);
            }
            
            int col = resultSet.getMetaData().getColumnCount();

            while(resultSet.next())
            {
                Object [] rows = new Object[col];
                for(int i = 1; i <= col; i++)
                {
                    rows[i-1] = resultSet.getObject(i);
                }
                ((DefaultTableModel)table.getModel()).insertRow(resultSet.getRow() - 1, rows);
                
                String sql1 = "SELECT SUM(amount) as sumamount FROM operation WHERE "+type+" LIKE ? AND active = 1 AND location = 'SLCCP Bank Account'";
                PreparedStatement statement1 = connection.prepareStatement(sql1);
                statement1.setString(1, value+'%');
                ResultSet resultSet1 = statement1.executeQuery();

                while(resultSet1.next())
                {
                    totalBank.setText("0.00 €");
                    double sumAmount = resultSet1.getDouble("sumamount");
                    if(sumAmount != 0d)
                    {
                        totalBank.setText(String.format( "%.2f", sumAmount) + " €");
                    }
                    else
                    {
                        totalBank.setText("0.00 €");
                    }
                    
                    String sql3 = "SELECT SUM(amount) as sumamount FROM operation WHERE "+type+" LIKE ? AND active = 1 AND location = 'Liquid (Cash)'";
                    PreparedStatement statement3 = connection.prepareStatement(sql3);
                    statement3.setString(1, value+'%');
                    ResultSet resultSet3 = statement3.executeQuery();

                    while(resultSet3.next())
                    {
                        totalCash.setText("0.00 €");
                        double sumAmount3 = resultSet3.getDouble("sumamount");
                        if(sumAmount3 != 0f)
                        {
                            totalCash.setText(String.format( "%.2f", sumAmount3) + " €");
                        }
                        else
                        {
                            totalCash.setText("0.00 €");
                        }
                        
                        String sql4 = "SELECT SUM(amount) as sumamount FROM operation WHERE "+type+" LIKE ? AND active = 1 AND location = 'Saving Account (ADP)'";
                        PreparedStatement statement4 = connection.prepareStatement(sql4);
                        statement4.setString(1, value+'%');
                        ResultSet resultSet4 = statement4.executeQuery();

                        while(resultSet4.next())
                        {
                            totalSavings.setText("0.00 €");
                            double sumAmount4 = resultSet4.getDouble("sumamount");
                            if(sumAmount4 != 0d)
                            {
                                totalSavings.setText(String.format( "%.2f", sumAmount4) + " €");
                            }
                            else
                            {
                                totalSavings.setText("0.00 €");
                            }

                        }
                    }
                    
                }
                resultSet1.close();
                statement1.close();
                
            }
            resultSet.close();
            statement.close();
            
            
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            
        }
    }
    
    public void searchByDate(Operation operation, JTable table, String date1, String date2, JLabel totalBank, JLabel totalCash, JLabel totalSavings)
    {
        try
        {        
            Connection connection = DriverManager.getConnection(url,user,password);

            String sql = "SELECT id_operation, date, type, reason, method, location, amount FROM operation WHERE date >= ? AND date <= ? AND active = 1 ORDER BY date DESC";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, date1);
            statement.setString(2, date2);
            ResultSet resultSet = statement.executeQuery();
            
            while(table.getRowCount() > 0)
            {
                ((DefaultTableModel)table.getModel()).removeRow(0);
            }
            
            int col = resultSet.getMetaData().getColumnCount();

            while(resultSet.next())
            {
                Object [] rows = new Object[col];
                for(int i = 1; i <= col; i++)
                {
                    rows[i-1] = resultSet.getObject(i);
                }
                ((DefaultTableModel)table.getModel()).insertRow(resultSet.getRow() - 1, rows);
                
                String sql1 = "SELECT SUM(amount) as sumamount FROM operation WHERE date >= ? AND date <= ? AND active = 1 AND location = 'SLCCP Bank Account'";
                PreparedStatement statement1 = connection.prepareStatement(sql1);
                statement1.setString(1, date1);
                statement1.setString(2, date2);
                ResultSet resultSet1 = statement1.executeQuery();

                while(resultSet1.next())
                {
                    totalBank.setText("0.00 €");
                    double sumAmount = resultSet1.getDouble("sumamount");
                    if(sumAmount != 0d)
                    {
                        totalBank.setText(String.format( "%.2f", sumAmount) + " €");
                    }
                    else
                    {
                        totalBank.setText("0.00 €");
                    }
                    
                    String sql3 = "SELECT SUM(amount) as sumamount FROM operation WHERE date >= ? AND date <= ? AND active = 1 AND location = 'Liquid (Cash)'";
                    PreparedStatement statement3 = connection.prepareStatement(sql3);
                    statement3.setString(1, date1);
                    statement3.setString(2, date2);
                    ResultSet resultSet3 = statement3.executeQuery();

                    while(resultSet3.next())
                    {
                        totalCash.setText("0.00 €");
                        double sumAmount3 = resultSet3.getDouble("sumamount");
                        if(sumAmount3 != 0d)
                        {
                            totalCash.setText(String.format( "%.2f", sumAmount3) + " €");
                        }
                        else
                        {
                            totalCash.setText("0.00 €");
                        }
                        
                        String sql4 = "SELECT SUM(amount) as sumamount FROM operation WHERE date >= ? AND date <= ? AND active = 1 AND location = 'Saving Account (ADP)'";
                        PreparedStatement statement4 = connection.prepareStatement(sql4);
                        statement4.setString(1, date1);
                        statement4.setString(2, date2);
                        ResultSet resultSet4 = statement4.executeQuery();

                        while(resultSet4.next())
                        {
                            totalSavings.setText("0.00 €");
                            double sumAmount4 = resultSet4.getDouble("sumamount");
                            if(sumAmount4 != 0d)
                            {
                                totalSavings.setText(String.format( "%.2f", sumAmount4) + " €");
                            }
                            else
                            {
                                totalSavings.setText("0.00 €");
                            }

                        }
                    }
                }
                resultSet1.close();
                statement1.close();
                
            }
            resultSet.close();
            statement.close();
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            
        }
    }
    
    public void searchByDateType(Operation operation, JTable table, String date1, String date2, String type, String value, JLabel totalBank, JLabel totalCash, JLabel totalSavings)
    {
        try
        {        
            Connection connection = DriverManager.getConnection(url,user,password);
            
            String sql = "SELECT id_operation, date, type, reason, method, location, amount FROM operation WHERE "+type+" LIKE ? AND date >= ? AND date <= ? AND active = 1 ORDER BY date DESC";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, value+'%');
            statement.setString(2, date1);
            statement.setString(3, date2);
            ResultSet resultSet = statement.executeQuery();
            
            while(table.getRowCount() > 0)
            {
                ((DefaultTableModel)table.getModel()).removeRow(0);
            }
            
            int col = resultSet.getMetaData().getColumnCount();

            while(resultSet.next())
            {
                Object [] rows = new Object[col];
                for(int i = 1; i <= col; i++)
                {
                    rows[i-1] = resultSet.getObject(i);
                }
                ((DefaultTableModel)table.getModel()).insertRow(resultSet.getRow() - 1, rows);
                
                String sql1 = "SELECT SUM(amount) as sumamount FROM operation WHERE date >= ? AND date <= ? AND "+type+" LIKE ? AND active = 1 AND location = 'SLCCP Bank Account'";
                PreparedStatement statement1 = connection.prepareStatement(sql1);
                statement1.setString(1, date1);
                statement1.setString(2, date2);
                statement1.setString(3, value+'%');
                ResultSet resultSet1 = statement1.executeQuery();

                while(resultSet1.next())
                {
                    double sumAmount = resultSet1.getDouble("sumamount");
                    totalBank.setText(String.format( "%.2f", sumAmount) + " €");
                    
                    String sql3 = "SELECT SUM(amount) as sumamount FROM operation WHERE date >= ? AND date <= ? AND "+type+" LIKE ? AND active = 1 AND location = 'Liquid (Cash)'";
                    PreparedStatement statement3 = connection.prepareStatement(sql3);
                    statement3.setString(1, date1);
                    statement3.setString(2, date2);
                    statement3.setString(3, value+'%');
                    ResultSet resultSet3 = statement3.executeQuery();

                    while(resultSet3.next())
                    {
                        totalCash.setText("0.00 €");
                        double sumAmount3 = resultSet3.getDouble("sumamount");

                        totalCash.setText(String.format( "%.2f", sumAmount3) + " €");
                        
                        String sql4 = "SELECT SUM(amount) as sumamount FROM operation WHERE date >= ? AND date <= ? AND "+type+" LIKE ? AND active = 1 AND location = 'Saving Account (ADP)'";
                        PreparedStatement statement4 = connection.prepareStatement(sql4);
                        statement4.setString(1, date1);
                        statement4.setString(2, date2);
                        statement4.setString(3, value+'%');
                        ResultSet resultSet4 = statement4.executeQuery();

                        while(resultSet4.next())
                        {
                            totalSavings.setText("0.00 €");
                            double sumAmount4 = resultSet4.getDouble("sumamount");

                            totalSavings.setText(String.format( "%.2f", sumAmount4) + " €");
                            
                        }
                    }
                }
                resultSet1.close();
                statement1.close();
                
            }
            resultSet.close();
            statement.close();
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            
        }
    }
    
    public void selectAllPayments(Operation operation, JTable table, JLabel total)
    {
        try
        {        
            Connection connection = DriverManager.getConnection(url,user,password);
            
            String sql1 = "SELECT operation.id_operation, date, method, location, name, surname, amount FROM contacts, income, operation WHERE income.id_operation = operation.id_operation AND contacts.id_contact = income.paid_by AND reason = ? AND operation.active = 1 ORDER BY date DESC";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setString(1, "Client Payment");
            ResultSet resultSet1 = statement1.executeQuery();
            
            while(table.getRowCount() > 0)
            {
                ((DefaultTableModel)table.getModel()).removeRow(0);
            }
            
            int col = resultSet1.getMetaData().getColumnCount();

            while(resultSet1.next())
            {
                Object [] rows = new Object[col];
                for(int i = 1; i <= col; i++)
                {
                    rows[i-1] = resultSet1.getObject(i);
                }
                ((DefaultTableModel)table.getModel()).insertRow(resultSet1.getRow() - 1, rows);
                
            }
            resultSet1.close();
            statement1.close();
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            
        }
    }
    
    public void searchPayments(Operation operation, JTable table, String type, String value, JLabel total)
    {
        try
        {        
            Connection connection = DriverManager.getConnection(url,user,password);
            
            String sql = "SELECT operation.id_operation, date, method, location, name, surname, amount FROM contacts, income, operation WHERE operation.id_operation = income.id_operation AND contacts.id_contact = income.paid_by AND reason = ? AND "+type+" LIKE ? AND operation.active = 1 ORDER BY date DESC";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "Client Payment");
            statement.setString(2, value+'%');
            ResultSet resultSet = statement.executeQuery();
            
            while(table.getRowCount() > 0)
            {
                ((DefaultTableModel)table.getModel()).removeRow(0);
            }
            
            int col = resultSet.getMetaData().getColumnCount();

            while(resultSet.next())
            {
                Object [] rows = new Object[col];
                for(int i = 1; i <= col; i++)
                {
                    rows[i-1] = resultSet.getObject(i);
                }
                ((DefaultTableModel)table.getModel()).insertRow(resultSet.getRow() - 1, rows);
                
                String sql2 = "SELECT SUM(amount) as sumamount FROM contacts, income, operation WHERE income.id_operation = operation.id_operation AND contacts.id_contact = income.paid_by AND reason = ? AND operation.active = 1 AND "+type+" LIKE ?";
                PreparedStatement statement2 = connection.prepareStatement(sql2);
                statement2.setString(1, "Client Payment");
                statement2.setString(2, value+'%');
                ResultSet resultSet2 = statement2.executeQuery();
                
                while(resultSet2.next())
                {
                    total.setText("0.00 €");
                    double sumAmount = resultSet2.getDouble("sumamount");
                    if(sumAmount != 0d)
                    {
                        total.setText(String.format( "%.2f", sumAmount) + " €");
                    }
                    else
                    {
                        total.setText("0.00 €");
                    }
                    
                }
                resultSet2.close();
                statement2.close();
                
            }
            resultSet.close();
            statement.close();
            
            
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            
        }
    }
    
    
    
   
}