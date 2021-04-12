
package main.Class;


public class Operation implements Contact
{
    private int id_operation;
    private double amount;
    private String date;
    private String type;
    private String reason;
    private String method;
    private String location;
    private int paid_by;
    private String paid_to;
    private int given_to;
    private int id_contact;
    private String surname;
    private String name;
    private String date_birth;
    private String address;
    private String phone1;
    private String phone2;
    private String phone3;
    private int id_family;
    private int id_group;
    private String name_group;
    private String post;
    private int active_contact;
    private int active_operation;
    
    
    
    //Constructeurs
    public Operation(){}
    
    public Operation(int id_operation, double amount, String date, String type, String reason, String method, String location, int paid_by, String paid_to, int given_to, int id_contact, String surname, String name, String date_birth, String address, String phone1, String phone2, String phone3, int id_family, int id_group, String name_group, String post, int active_contact, int active_operation)
    {
        this.id_operation = id_operation;
        this.amount = amount;
        this.type = type;
        this.reason = reason;
        this.date = date;
        this.method = method;
        this.location = location;
        this.paid_by = paid_by;
        this.paid_to = paid_to;
        this.given_to = given_to;
        this.id_contact = id_contact;
        this.surname = surname;
        this.name = name;
        this.date_birth = date_birth;
        this.address = address;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.phone3 = phone3;
        this.id_family = id_family;
        this.id_group = id_group;
        this.name_group = name_group;
        this.post = post;
        this.active_contact = active_contact;
        this.active_operation = active_operation;
    }
    
    
    //getters
    public int getIdOperation()
    {
        return id_operation;
    }
    
    @Override
    public int getIdContact()
    {
        return id_contact;
    }
    
    public double getAmount()
    {
        return amount;
    }
    
    public String getDate()
    {
        return date;
    }
    
    public String getMethod()
    {
        return method;
    }
    
    public String getType()
    {
        return type;
    }
    
    public String getReason()
    {
        return reason;
    }
    
    public String getLocation()
    {
        return location;
    }
    
    public String getPaidTo()
    {
        return paid_to;
    }
    
    public int getPaidBy()
    {
        return paid_by;
    }
    
    public int getGivenTo()
    {
        return given_to;
    }
    
    @Override
    public String getSurname()
    {
        return surname;
    }
    
    @Override
    public String getName()
    {
        return name;
    }
    
    @Override
    public String getDateBirth()
    {
        return date_birth;
    }
    
    @Override
    public String getAddress()
    {
        return address;
    }
    
    @Override
    public String getPhone1()
    {
        return phone1;
    }
    
    @Override
    public String getPhone2()
    {
        return phone2;
    }
    
    @Override
    public String getPhone3()
    {
        return phone3;
    }
    
    @Override
    public int getIdFamily()
    {
        return id_family;
    }
    
    @Override
    public int getIdGroup()
    {
        return id_group;
    }
    
    @Override
    public String getNameGroup()
    {
        return name_group;
    }
    
    @Override
    public String getPost()
    {
        return post;
    }
    
    @Override
    public int getActiveContact()
    {
        return active_contact;
    }
   
    public int getActiveOperation()
    {
        return active_operation;
    }
    
    //setters
    public void setIdOperation(int id_operation)
    {
        this.id_operation = id_operation;
    }
    
    @Override
    public void setIdContact(int id_contact)
    {
        this.id_contact = id_contact;
    }
    
    public void setAmount(double amount)
    {
        this.amount = amount;
    }
    
    public void setType(String type)
    {
        this.type = type;
    }
    
    public void setReason(String reason)
    {
        this.reason = reason;
    }
    
    public void setDate(String date)
    {
        this.date = date;
    }
    
    public void setMethod(String method)
    {
        this.method = method;
    }
    
    public void setLocation(String location)
    {
        this.location = location;
    }
    
    public void setPaidTo(String paid_to)
    {
        this.paid_to = paid_to;
    }
    
    public void setPaidBy(int paid_by)
    {
        this.paid_by = paid_by;
    }
    
    public void setGivenTo(int given_to)
    {
        this.given_to = given_to;
    }
    
    
    @Override
    public void setSurname(String surname)
    {
        this.surname = surname;
    }
    
    @Override
    public void setName(String name)
    {
        this.name = name;
    }
    
    @Override
    public void setDateBirth(String date_birth)
    {
        this.date_birth = date_birth;
    }
    
    @Override
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    @Override
    public void setPhone1(String phone1)
    {
        this.phone1 = phone1;
    }
    
    @Override
    public void setPhone2(String phone2)
    {
        this.phone2 = phone2;
    }
    
    @Override
    public void setPhone3(String phone3)
    {
        this.phone3 = phone3;
    }
    
    
    @Override
    public void setIdFamily(int id_family)
    {
        this.id_family = id_family;
    }
    
    @Override
    public void setIdGroup(int id_group)
    {
        this.id_group = id_group;
    }
    
    @Override
    public void setNameGroup(String name_group)
    {
        this.name_group = name_group;
    }
    
    @Override
    public void setPost(String post)
    {
        this.post = post;
    }
    
    @Override
    public void setActiveContact(int active_contact)
    {
        this.active_contact = active_contact;
    }
    
    public void setActiveOperation(int active_operation)
    {
        this.active_operation = active_operation;
    }
    
}
