package main.Class.DAO;

import java.sql.*; 
import java.time.LocalTime;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import main.Class.Implements.ContactImp;
import main.Class.Implements.GroupImp;

public class ContactDAO implements DAO<ContactImp>
{
    
    private String url;
    private String user;
    private String password;
    long millis=System.currentTimeMillis();  
    java.sql.Date date = new java.sql.Date(millis); 
    LocalTime time = LocalTime.now();
    
    GroupImp group = new GroupImp();
    
    public ContactDAO()
    {
        this.url = "jdbc:mysql://slccpmanager.ccmtouylia9i.eu-west-3.rds.amazonaws.com:3306/slccp?autoReconnect=true&useSSL=false";
        this.user = "praveen";
        this.password = "praveen1998";
         
    }
    

    @Override
    public void add(ContactImp contact, JLabel label, JLabel userLabel)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url,user,password);

            String sql = "INSERT INTO contacts (name, surname, date_birth, address, phone_1, phone_2, phone_3, id_family, id_group, post, active) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, contact.getName());
            statement.setString(2, contact.getSurname());
            statement.setString(3, contact.getDateBirth());
            statement.setString(4, contact.getAddress());
            statement.setString(5, contact.getPhone1());
            statement.setString(6, contact.getPhone2());
            statement.setString(7, contact.getPhone3());
            statement.setInt(8, contact.getIdFamily());
            statement.setInt(9, contact.getIdGroup());
            statement.setString(10, contact.getPost());
            statement.setInt(11, 1);
            statement.execute();
            
            label.setText(contact.getName() + " " + contact.getSurname() + " has been successfully added to the Database !");
            
            String sql1 = "INSERT INTO action (user, type, action, date, time) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setString(1, userLabel.getText());
            statement1.setString(2, "Add Contact");
            statement1.setString(3, userLabel.getText()+" added a new contact: "+ contact.getName() +" "+ contact.getSurname()+" (family n°"+contact.getIdFamily()+")");
            statement1.setString(4, date.toString());
            statement1.setString(5, time.toString());
            statement1.execute();

        }
        catch(SQLException e)
        {
            e.printStackTrace();           
            label.setText("ERROR : Attempt to add the contact is unsuccessfull !");
        }
    }
    
    
    @Override
    public void update(ContactImp contact, int id, String type, String value, JLabel label, JLabel userLabel)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url,user,password);
            
            String sql1 = "SELECT * FROM contacts WHERE id_contact = ?";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setInt(1, id);
            ResultSet resultSet = statement1.executeQuery();

            if(resultSet.next())
            {
                String sql = "UPDATE contacts SET " + type + " = ? WHERE id_contact = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, value);
                statement.setInt(2, id);
                statement.execute();
                
                label.setText("Contact N°" + id + " has been updated successfully !");
                System.out.println("Contact N°" + id + " has been updated successfully !");
                
                String sql2 = "INSERT INTO action (user, type, action, date, time) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement statement2 = connection.prepareStatement(sql2);
                statement2.setString(1, userLabel.getText());
                statement2.setString(2, "Update Contact");
                statement2.setString(3, userLabel.getText()+" updated the "+type+" of "+ contact.getName() +" "+ contact.getSurname() + " ("+id+")");
                statement2.setString(4, date.toString());
                statement2.setString(5, time.toString());
                statement2.execute();
            }
            else
            {
                label.setText("ERROR : No contact is related to that id !\n");
                System.out.println("ERROR : No contact is related to that id !\n");
            }

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("ERROR : Unable to update the contact !");
        }
    }
    
    
    @Override
    public void delete(ContactImp contact, int id, JLabel userLabel)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url,user,password);
            
            String sql1 = "SELECT * FROM contacts WHERE id_contact = ?";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setInt(1, id);
            ResultSet resultSet = statement1.executeQuery();

            if(resultSet.next())
            {
                String sql = "UPDATE contacts SET active = ? WHERE id_contact = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, 0);
                statement.setInt(2, id);
                statement.execute();

                System.out.println("The contact n°" + id + " has been deleted from the Database successfully !");
                
                String sql2 = "INSERT INTO action (user, type, action, date, time) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement statement2 = connection.prepareStatement(sql2);
                statement2.setString(1, userLabel.getText());
                statement2.setString(2, "Delete Contact");
                statement2.setString(3, userLabel.getText()+" deleted the contact n°"+id);
                statement2.setString(4, date.toString());
                statement2.setString(5, time.toString());
                statement2.execute();
            }
            else
            {
                System.out.println("ERROR : No contact is related to that id !\n");
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("ERROR : Unable to update the contact !");
        }
    }
    

    public void selectAll(ContactImp contact, JTable table)
    {
        try
        {        
            Connection connection = DriverManager.getConnection(url,user,password);
            
            String sql = "SELECT * FROM contacts WHERE active = ? ORDER BY id_family DESC";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, 1);
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
                
            }
            resultSet.close();
            statement.close();
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            
        }
    }
    
    public void search(ContactImp contact, JTable table, String type, String value)
    {
        try
        {        
            Connection connection = DriverManager.getConnection(url,user,password);
            
            String sql = "SELECT * FROM contacts WHERE "+type+" LIKE ? AND active = ? ORDER BY id_family DESC";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, value+'%');
            statement.setInt(2, 1);
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