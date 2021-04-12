
package main.Class.Implements;

import main.Class.Group;

public class GroupImp implements Group
{
    private int id_group;
    private String name_group;
    
    //Constructeurs
    public GroupImp(){}
    
    public GroupImp(int id_group, String name_group)
    {
        this.id_group = id_group;
        this.name_group = name_group;
    }
    
    //getters
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
    
    
    //setters
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
   
}
