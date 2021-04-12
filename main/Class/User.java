
package main.Class;


public class User
{
    private int id_user;
    private String name_user;
    private String surname_user;
    private String username;
    private String password;
    private int right;
    
    
    //Constructeurs
    public User(){}
    
    public User(int id_user, String name_user, String surname_user, String username, String password, int right)
    {
        this.id_user = id_user;
        this.name_user = name_user;
        this.surname_user = surname_user;
        this.password = password;
        this.username = username;
        this.right = right;
    }
    
    //getters

    public int getIdUser()
    {
        return id_user;
    }
    
    
    public String getNameUser()
    {
        return name_user;
    }
    
    public String getSurnameUser()
    {
        return surname_user;
    }
    
    
    public String getUsername()
    {
        return username;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public int getRight()
    {
        return right;
    }

    //setters
    
    public void setIdUser(int id_user)
    {
        this.id_user = id_user;
    }
    
    
    public void setNameUser(String name_user)
    {
        this.name_user = name_user;
    }
    
    public void setSurnameUser(String surname_user)
    {
        this.surname_user = surname_user;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public void setRight(int right)
    {
        this.right = right;
    }
    
}
