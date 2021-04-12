package main.Class;

public interface Contact extends Group
{
    //Getters
    int getIdContact();
    String getSurname();
    String getName();
    String getDateBirth();
    String getAddress();
    String getPhone1();
    String getPhone2();
    String getPhone3();
    int getIdFamily();
    String getPost();
    int getActiveContact();
    @Override
    int getIdGroup();
    
    

    //Setters
    void setIdContact(int id_contact);
    void setSurname(String surname);
    void setName(String name);
    void setDateBirth(String date_birth);
    void setAddress(String address);
    void setPhone1(String phone1);
    void setPhone2(String phone2);
    void setPhone3(String phone3);
    void setIdFamily(int id_family);
    void setPost(String post);
    void setActiveContact(int active);
    @Override
    void setIdGroup(int id_group);
}
