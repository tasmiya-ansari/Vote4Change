/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.CandidateDTO;
import evoting.dto.CandidateDetails;
import evoting.dto.CandidateInfo;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

/**
 *
 * @author tasmi
 */
public class CandidateDAO {
    private static PreparedStatement ps1,ps2,ps3,ps4,ps5,ps6,ps7,ps8,ps9,ps10,ps11;
    static
    {
        try{
        ps1=DBConnection.getConnection().prepareStatement("select max(candidate_id) from candidate");
        ps2=DBConnection.getConnection().prepareStatement("Select username from user_details where adhar_no=?");
        ps3=DBConnection.getConnection().prepareStatement("Select distinct city from user_details");
        ps4=DBConnection.getConnection().prepareStatement("Insert into candidate values(?,?,?,?,?)");
        ps5=DBConnection.getConnection().prepareStatement("select candidate_id from candidate");
        ps6=DBConnection.getConnection().prepareStatement("select * from candidate where candidate_id=?");
        ps7=DBConnection.getConnection().prepareStatement("select candidate_id,username,party,symbol from candidate,user_details where candidate.user_id=user_details.adhar_no and candidate.city=(select city from user_details where adhar_no=?)");
        ps8=DBConnection.getConnection().prepareStatement("Update candidate set city=?,party=?,symbol=? where candidate_id=? and user_id=?");
        ps9=DBConnection.getConnection().prepareStatement("delete from candidate where candidate_id=?");
        ps10=DBConnection.getConnection().prepareStatement("select adhar_no from user_details where usertype!='admin' and adhar_no not in(select user_id from candidate)");
        ps11=DBConnection.getConnection().prepareStatement("select * from candidate where party=? and city=?");
        }
        catch(SQLException sqle)
        {
            sqle.printStackTrace();
        }
    }
    
    public static String getNewId()throws SQLException
    {
        ResultSet rs=ps1.executeQuery();
        rs.next();
        String cid=rs.getString(1);
        if(cid==null)
            return "C101";
        else
        {
            int id=Integer.parseInt(cid.substring(1));
            return "C"+(id+1);
        }

    }
    
    public static String getUsernameById(String adhar_no)throws SQLException
    {
        ps2.setString(1, adhar_no);
        ResultSet rs=ps2.executeQuery();
        if(rs.next())
        {
            return rs.getString(1);
        }
        else
            return null;//agr glt adhar_no mile to
    }
    public static ArrayList<String> getCity()throws SQLException
    {
        ArrayList<String> cityList=new ArrayList<>();
        ResultSet rs=ps3.executeQuery();
        while(rs.next())
        {
            cityList.add(rs.getString(1));
        }
        return cityList;
    }
    
    public static boolean addCandidate(CandidateDTO obj) throws SQLException
    {
        ps4.setString(1, obj.getCandidateId());
        ps4.setString(2, obj.getParty());
        ps4.setString(3, obj.getUser_Id());
        ps4.setBinaryStream(4, obj.getSymbol());
        ps4.setString(5, obj.getCity());
        return ps4.executeUpdate()==1;
    }
    
    public static ArrayList<String> getAllCandidateIds() throws SQLException
    {
        ArrayList<String> cidList=new ArrayList<>();
        ResultSet rs=ps5.executeQuery();
        while(rs.next())
        {
            cidList.add(rs.getString(1));
        }
        return cidList;
    }
    
    public static CandidateDetails getDetailsById(String candidateId) throws Exception//qk sql or IOException both 
    {
        ps6.setString(1, candidateId);
        ResultSet rs= ps6.executeQuery();
        CandidateDetails cd=new CandidateDetails();
        Blob blob;//blob direct string nhi bnta ....Blob -> InputStream->Byte[]->Base64
        //setBinaryStream(method of ps) InputStream ko Blob me convert krta h
        //getBinaryStream blob ka method h jo InputStream return krta h
        InputStream inputStream;
        byte [] buffer;
        byte [] imageBytes;
        int bytesRead;
        String base64Image;
        ByteArrayOutputStream outputStream;
        if(rs.next())
        {   blob=rs.getBlob(4);//img in blob form
            inputStream=blob.getBinaryStream();//img in binary form
            //inputStream.read() utna data read krta h jitna parameter me passed byte array ka size but problem ye h hme size nhi pta...hme image ka size nhi pta
            outputStream=new ByteArrayOutputStream();//outputStream ke pass method h write jo parameter me ek array leta h jiska sie java ne standard diya h 4096 ka
            buffer=new byte[4096];
            bytesRead=-1;
            while((bytesRead=inputStream.read(buffer))!=-1)//read methoda array lega...array khali h...read methos inputStream se image utha kr copy krega byte array me
                //kitni bytes read kr paya wo aaega bytesRead me..agr image 1000bytes ki h to 1000 aega bytesRead me agr 5000bytes ki h to 4096 aaega //bytes read hui h wo bytesRead me aa jega or wo arraysize se zyada kabhi nhi hoga
                //image jitni badi ho loop chlta rhega har baar 4096 copy hoga fr next 4096 so on
            {
                outputStream.write(buffer, 0, bytesRead);//0 starting index h ,bytesRead last index...bytesRead ki jagah array.length()mhi likh skte qk fr 4096 fixed ho jaega but size zaroori nhi h 4096 hi ho
            }
            imageBytes=outputStream.toByteArray();//bytes me aagyi
            Base64.Encoder en=Base64.getEncoder();
            base64Image=en.encodeToString(imageBytes);//bytes array leta h or return krta h uska Base64 version
            //professional way of converting image og blob to base64 version
            cd.setSymbol(base64Image);
            cd.setCandidateId(candidateId);
            cd.setCandidateName(getUsernameById(rs.getString(3)));
            cd.setParty(rs.getString(2));
            cd.setCity(rs.getString(5));
            cd.setUserId(rs.getString(3));
        }
        return cd;
    }
    public static ArrayList<CandidateInfo> viewCandidate(String adharno) throws Exception{
         ArrayList<CandidateInfo> candidateList=new ArrayList<>();
         ps7.setString(1,adharno);
         ResultSet rs=ps7.executeQuery();
         
        Blob blob;
        InputStream inputStream;
        byte [] buffer;
        byte [] imageBytes;
        int bytesRead;
        String base64Image;
        ByteArrayOutputStream outputStream;
        
        while(rs.next())
        {
            blob=rs.getBlob(4);
            inputStream=blob.getBinaryStream();
            outputStream=new ByteArrayOutputStream();
            buffer=new byte[4096];
            bytesRead=-1;
            while((bytesRead=inputStream.read(buffer))!=-1)
            {
                outputStream.write(buffer, 0, bytesRead);
            }
            imageBytes=outputStream.toByteArray();
            Base64.Encoder en=Base64.getEncoder();
            base64Image=en.encodeToString(imageBytes);
            
            CandidateInfo ci=new CandidateInfo();
            ci.setSymbol(base64Image);
            ci.setCandidateid(rs.getString(1));
            ci.setCandidatename(rs.getString(2));
            ci.setParty(rs.getString(3));
            
            candidateList.add(ci);
        }
        return candidateList;         
    }
    public static boolean updateCandidate(CandidateDTO obj) throws SQLException
    {
        ps8.setString(1,obj.getCity());
        ps8.setString(2, obj.getParty());
        ps8.setBinaryStream(3, obj.getSymbol());
        ps8.setString(4,obj.getCandidateId() );
        ps8.setString(5, obj.getUser_Id());
        return ps8.executeUpdate()==1;
    }
    
    public static boolean removeCandidate(String cid) throws SQLException
    {
        ps9.setString(1,cid);
        return ps9.executeUpdate()==1;
    }
    
    public static ArrayList<String> getAllUnusedUserIds() throws SQLException
    {
        ArrayList<String> uidList=new ArrayList<>();
        ResultSet rs=ps10.executeQuery();
        while(rs.next())
        {
            uidList.add(rs.getString(1));
        }
        return uidList;
    }
    
    public static boolean partyAndCityExist(String party,String city) throws SQLException
    {
        ps11.setString(1,party);
        ps11.setString(2,city);
        ResultSet rs=ps11.executeQuery();
        if(rs.next())
            return true;
        else return false;
    }
}
