package main.Class;


import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



public class Functions 
{

    private String url = "jdbc:mysql://slccpmanager.ccmtouylia9i.eu-west-3.rds.amazonaws.com:3306/slccp?autoReconnect=true&useSSL=false";
    private String user = "praveen";
    private String password = "praveen1998";
        
    public void selectAllAction(JTable table)
    {
        
        try
        {        
            Connection connection = DriverManager.getConnection(url,user,password);
            
            String sql = "SELECT user, type, action, date, time FROM action ORDER BY id_action DESC";
            PreparedStatement statement = connection.prepareStatement(sql);
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
    
    public void searchAction(JTable table, String type, String value)
    {
        try
        {        
            Connection connection = DriverManager.getConnection(url,user,password);
            
            String sql = "SELECT * FROM action WHERE "+type+" LIKE ? ORDER BY id_action DESC";
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
                
            }
            resultSet.close();
            statement.close();
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            
        }
    }
    
    public void generatePDF(JTable table, String date1, String date2, String type, String value)
    {
        String headerText = date1+" to "+date2+" : "+type+": "+value;
        MessageFormat header = new MessageFormat(headerText);
        MessageFormat footer = new MessageFormat("Page {0, number, integer}");
        
        try
        {
            PrinterJob job = PrinterJob.getPrinterJob();
            PageFormat pf = job.defaultPage();
            Paper paper = pf.getPaper();
            double margin = 5;
            paper.setImageableArea(margin, paper.getImageableY(), paper.getWidth() - 2* margin, paper.getImageableHeight());
            pf.setPaper(paper);
          
            job.setPrintable(table.getPrintable(JTable.PrintMode.FIT_WIDTH, header, footer), job.validatePage(pf));
            job.print();
            
        }
        catch(java.awt.print.PrinterException e)
        {
            System.err.format("Printing error", e.getMessage());
        }
        
    }    
        
    
}
