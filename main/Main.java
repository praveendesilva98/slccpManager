package main;

import main.Class.DAO.UserDAO;
import main.Class.User;


public class Main 
{
    public static void main(String[] args) 
    {
        double b = 10.12;
        double a = 43.12;
        double c = a + b;
        String d = String.format( "%.2f", c);
        System.out.println(d);
    }
}