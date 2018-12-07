/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class ConnectToH2 {
    
 private java.sql.Connection connection = null;
    
    void connect()
    {
        try
        {
            Class.forName("org.h2.Driver");
            try
            {
                connection = DriverManager.getConnection("jdbc:h2:D:\\KULIAH\\Semester 4\\Pemrograman Berorientasi Objek\\PROYEK\\PROJECT PBO TTT\\resources","miacrnfrska","esjagung");
                try (java.sql.Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM HISTORY")) {
                    while (rs.next())
                    {
                        System.out.println(rs.getString(1) + " " + rs.getString(2));
                    }
                    System.out.println(" ");
                }
                connection.close();
            }
            catch (SQLException ex)
            {
                System.out.println("SQLExeption: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
        catch (Exception ex)
        {
        }
    }
    
    public static void main (String[] args)
    {
        new ConnectToH2().connect();
    }
}

