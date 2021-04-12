package main.Class.Implements;

import main.Class.Contact;

public class ContactImp implements Contact
{
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

    
    //Constructor
    public ContactImp(){}
    
    public ContactImp(int id_contact, String surname, String name, String date_birth, String address, String phone1, String phone2, String phone3, int id_family, int id_group, String name_group, String post, int active)
    {
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
        this.active_contact = active;
    }
    
    //getters
    @Override
    public int getIdContact()
    {
        return id_contact;
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
   
    //setters
    @Override
    public void setIdContact(int id_contact)
    {
        this.id_contact = id_contact;
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

 
}