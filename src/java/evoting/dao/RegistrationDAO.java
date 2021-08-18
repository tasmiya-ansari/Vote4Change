/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.UserDetails;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author tasmi
 */
public class RegistrationDAO {
    private static PreparedStatement ps1,ps2;//ps1 and ps2 static coz they'll be required everytime a user visits the app(search and register will be called whenever a user registers) .
    //if we create them in methods then there will be multiple copies as it's a web app and not a desktop app
    
    static
    {
        try{
        ps1=DBConnection.getConnection().prepareStatement("select * from user_details where adhar_no=?");
        ps2=DBConnection.getConnection().prepareStatement("insert into user_details values(?,?,?,?,?,?,?,?,?)");
        }
        catch(SQLException sqle)
        {
            sqle.printStackTrace();
        }
    }
    
    public static boolean searchUser(String adhar_no)throws SQLException
    {
        ps1.setString(1, adhar_no);
        return ps1.executeQuery().next();//ResultSet rs=ps.executeQuery();return rs.next();
    }
    
    public static boolean registerUser(UserDetails ud)throws SQLException
    {
        ps2.setString(1, ud.getAdahar_no());
        ps2.setString(2, ud.getPassword());
        ps2.setString(3, ud.getUsername());
        ps2.setString(4,ud.getAddress());
        ps2.setString(5,ud.getCity());
        ps2.setString(6, ud.getEmail());
        ps2.setLong(7, ud.getMobile_no());
        ps2.setString(8, "voter");
        ps2.setString(9, ud.getGender());
        return ps2.executeUpdate()==1;
    }
    
}
