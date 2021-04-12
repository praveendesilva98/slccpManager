package main.Class.DAO;

import GUI.HomeGUI;
import java.sql.*; 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import main.Class.User;

public class UserDAO implements DAO<User>
{
    
    private String url;
    private String user;
    private String password;
    private int right;
    
    public UserDAO()
    {
        this.url = "jdbc:mysql://slccpmanager.ccmtouylia9i.eu-west-3.rds.amazonaws.com:3306/slccp?autoReconnect=true&useSSL=false";
        this.user = "praveen";
        this.password = "praveen1998";
    }
    
    
    @Override
    public void add(User userCon, JLabel label, JLabel userLabel){}
    
    public void addUser(User userCon, JLabel labelLogin, JLabel labelRegister, JPanel PanelLogin, JPanel PanelRegister)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url,user,password);

            String sql = "INSERT INTO user (name, surname, username, password, user_right) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userCon.getNameUser());
            statement.setString(2, userCon.getSurnameUser());
            statement.setString(3, userCon.getUsername());
            statement.setString(4, userCon.getPassword());
            statement.setInt(5, 3);
            statement.execute();
            
            PanelRegister.setVisible(false);
            PanelLogin.setVisible(true);
            labelLogin.setText(userCon.getNameUser() + " " + userCon.getSurnameUser() + " has been successfully added to the Database !");

        }
        catch(SQLException e)
        {
            e.printStackTrace();           
            labelRegister.setText("ERROR : Attempt to add the user is unsuccessfull !");
        }
    }
    
    
    @Override
    public void update(User userCon, int id, String type, String value, JLabel label, JLabel userLabel)
    {}
    
    
    public void updateUser(User userCon, String username, String type, String value, JLabel label, String name, JLabel userSettings, JLabel userHome)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url,user,password);
            
            String sql1 = "SELECT * FROM user WHERE username = ?";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setString(1, username);
            ResultSet resultSet = statement1.executeQuery();

            if(resultSet.next())
            {

                String sql = "UPDATE user SET " + type + " = ? WHERE username = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, value);
                statement.setString(2, username);
                statement.execute();
                
                label.setText("User "+name+" has been updated successfully !");
                
                if("password".equals(type))
                {
                    userSettings.setText(username);
                    userHome.setText(username);
                }
                else
                {
                    userSettings.setText(value);
                    userHome.setText(value);
                }
                
            }
            else
            {
                System.out.println("ERROR : No user is related to that id !\n");
            }

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("ERROR : Unable to update the user !");
        }
    }
    
    
    @Override
    public void delete(User userCon, int id, JLabel userLabel){}
    
    public void deleteUser(User userCon, String username)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url,user,password);
            
            String sql1 = "SELECT * FROM user WHERE username = ?";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setString(1, username);
            ResultSet resultSet = statement1.executeQuery();

            if(resultSet.next())
            {
                String sql = "DELETE FROM users WHERE username = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, username);
                statement.execute();
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public int connect(User userCon, JTextField UsernameField, JTextField PasswordField, JFrame frame, JLabel message)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url,user,password);
            
            String sql = "SELECT * FROM user WHERE (username = ? AND password = ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, UsernameField.getText());
            statement.setString(2, PasswordField.getText());
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next())
            {
                frame.setVisible(false);
                frame.dispose();
                new HomeGUI().setVisible(true);    
                
                userCon.setRight(resultSet.getInt("user_right"));
                
                right = userCon.getRight();
            }
            else
            {
                message.setText("Unknown Username or Password! Please try again");
                PasswordField.setText("");
                UsernameField.setText("");
            }
            
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("ERROR");
        }
        
        return right;
    }
    
    public void showName(User userCon, JLabel username, JLabel name, JLabel userLabel)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url,user,password);
            
            String sql = "SELECT * FROM user WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username.getText());
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next())
            {
                userCon.setNameUser(resultSet.getString("name"));
                userCon.setSurnameUser(resultSet.getString("surname"));
                
                String name_user = userCon.getNameUser();
                String surname_user = userCon.getSurnameUser();
                
                name.setText(name_user+" "+surname_user);
                userLabel.setText(username.getText());
                
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("ERROR");
        }
    }
    
}