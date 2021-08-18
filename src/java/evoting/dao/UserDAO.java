/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.UserDTO;
import evoting.dto.UserDetails;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author tasmi
 */
public class UserDAO {
    private static PreparedStatement ps1,ps2,ps3,ps4,ps5;
    static
    {
        try{
        ps1=DBConnection.getConnection().prepareStatement("select usertype from user_details where adhar_no=? and password=?");
        ps2=DBConnection.getConnection().prepareStatement("select * from user_details where usertype!='admin'");
        ps3=DBConnection.getConnection().prepareStatement("select adhar_no from user_details where usertype!='admin'");
        ps4=DBConnection.getConnection().prepareStatement("select * from user_details where adhar_no=?");
        ps5=DBConnection.getConnection().prepareStatement("delete from user_details where adhar_no=?");
        }
        catch(SQLException sqle)
        {
            sqle.printStackTrace();
        }
    }
    
    public static String validateUser(UserDTO user)throws SQLException
    {
        ps1.setString(1, user.getUserid());
        ps1.setString(2, user.getPwd());
        ResultSet rs=ps1.executeQuery();
        if(rs.next())
            return rs.getString(1);
        else
            return null;
    }
    public static ArrayList<UserDetails> getVoterDetails()throws SQLException
    {
        ResultSet rs=ps2.executeQuery();
        ArrayList<UserDetails> userDetailsList=new ArrayList<>();
        while(rs.next())
        {
            UserDetails ud=new UserDetails(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),Long.parseLong(rs.getString(7)),rs.getString(8));
            userDetailsList.add(ud);
        }
            return userDetailsList;
    }
    public static ArrayList<String> getUserIds()throws SQLException
    {
        ResultSet rs=ps3.executeQuery();
        ArrayList<String> userIdList=new ArrayList<>();
        while(rs.next())
        {
            userIdList.add(rs.getString(1));
        }
            return userIdList;
    }
    public static UserDetails getUserDetailById(String adhar_no)throws SQLException
    {
        ps4.setString(1, adhar_no);
        ResultSet rs=ps4.executeQuery();
        if(rs.next())
            return new UserDetails(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),Long.parseLong(rs.getString(7)),rs.getString(8));
        else
            return null;
    }
    public static boolean removeUser(String uid) throws SQLException
    {
        ps5.setString(1,uid);
        return ps5.executeUpdate()==1;
    }
}
