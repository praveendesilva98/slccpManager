
package main.Class.DAO;

import javax.swing.JLabel;
import javax.swing.JTable;

public interface DAO<T>
{   
    void add(T t, JLabel label, JLabel userLabel);
    void update(T t, int id, String type, String value, JLabel label, JLabel userLabel);
    void delete(T t, int id, JLabel userLabel);
}
