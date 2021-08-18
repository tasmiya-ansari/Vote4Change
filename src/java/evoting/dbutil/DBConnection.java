/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author tasmi
 */
public class DBConnection {
    
    private static Connection conn=null;//static coz we need only 1 entry of it...since our class have only  static data thus acc to java we'll maintain only static blocks or static methods
    
    static
    {
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");//is class ko available krane ke liye add ojdbc6.jar
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//Tasmiya-PC:1521/xe", "Vote4Change","evoting");
            System.out.println("Driver loaded and connection opened!");
        }
        catch(ClassNotFoundException cnf)
        {
            cnf.printStackTrace();
        }
        catch(SQLException sqle)
        {
            sqle.printStackTrace();
        }  
    }
    
    
    public static Connection getConnection()//coz conn is private and we need it in DAO classes
    {
        return conn;
    }
    
    public static void closeConnection()
    {
        try
        {
          conn.close();  
        }
        catch(SQLException sqle)
        {
            sqle.printStackTrace();
        }  
        
    }
    
}
