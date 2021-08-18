/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.CandidateInfo;
import evoting.dto.VoteDTO;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author tasmi
 */
public class VoteDAO {
    private static PreparedStatement ps1,ps2,ps3,ps4,ps5,ps6,ps7;
    static
    {
        try{
        ps1=DBConnection.getConnection().prepareStatement("select candidate_id from voting where voter_id=?");
        ps2=DBConnection.getConnection().prepareStatement("select c.candidate_id,u.username,c.party,c.symbol from candidate c,user_details u where c.user_id=u.adhar_no and c.candidate_id=?");
        ps3=DBConnection.getConnection().prepareStatement("insert into voting values(?,?)");
        ps4=DBConnection.getConnection().prepareStatement("select candidate_id,count(*) as vote_obtained from voting group by candidate_id order by vote_obtained desc");
        ps5=DBConnection.getConnection().prepareStatement("select count(*) from voting");
        ps6=DBConnection.getConnection().prepareStatement("select c.candidate_id,count(v.candidate_id) as vote from voting v inner join candidate c on v.candidate_id=c.candidate_id group by v.candidate_id order by vote desc");
        ps7=DBConnection.getConnection().prepareStatement("select u.gender,count(v.candidate_id) as votes from voting v inner join user_details u  on u.adhar_no=v.voter_id group by u.gender order by votes desc");
        }
        catch(SQLException sqle)
        {
            sqle.printStackTrace();
        }
    }
    
    public static String getCandidateId(String adharno)throws SQLException
    {
        ps1.setString(1,adharno);
        ResultSet rs=ps1.executeQuery();//rs next return null if nothing found it returns empty result set
        if(rs.next())
            return rs.getString(1);
        return null;
    }
    
 public static CandidateInfo getVote(String candidateid)throws Exception
    {
        ps2.setString(1,candidateid);
        ResultSet rs= ps2.executeQuery();
        CandidateInfo ci=new CandidateInfo();
        Blob blob;
        InputStream inputStream;
        byte [] buffer;
        byte [] imageBytes;
        int bytesRead;
        String base64Image;
        ByteArrayOutputStream outputStream;
        if(rs.next())
        {   blob=rs.getBlob(4);
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
            ci.setSymbol(base64Image);
            ci.setCandidateid(candidateid);
            ci.setCandidatename(rs.getString(2));
            ci.setParty(rs.getString(3));
        }
        return ci;
    }
 
 public static boolean addVote(VoteDTO vote)throws SQLException
 {
     ps3.setString(1, vote.getCandidateid());
     ps3.setString(2,vote.getVoterid());
     return ps3.executeUpdate()==1;
 }
 public static Map<String,Integer> getResultByCandidateId()throws SQLException
 {
     //jo data aaega voting table ka cid or vote ordered desc wo key value pair me jaega thus we'll make use of map but since it's an interface we'll make use of its deried class HashMap
     //but one problem with it is that insertion order is not preserved and we'll be adding data in desc order ...agr TreeMap liya to asc order me sort kr ke dega wo ...wo bhi key i.e candidate_id ke basis pe
     //pr hm data votes_obtained ke basis pr sort krke bhej rhe h
     //Thus we'll make use of LinkedHashMap in which insertion order is preserved
     Map<String,Integer> result=new LinkedHashMap<>();
     ResultSet rs=ps4.executeQuery();
     while(rs.next())
     {
         result.put(rs.getString(1),rs.getInt(2));
     }
     return result;
 }
 public static int getTotalVote()throws SQLException
 {
     ResultSet rs=ps5.executeQuery();
     if(rs.next())
            return rs.getInt(1);
        return 0;
 }
 
 public static Map<String,Integer> getResultByCity()throws SQLException
 {
     Map<String,Integer> result=new LinkedHashMap<>();
     ResultSet rs=ps6.executeQuery();
     while(rs.next())
     {
         result.put(rs.getString(1),rs.getInt(2));
     }
     return result;
 }
 public static Map<String,Integer> getResultByGender()throws SQLException
 {
     Map<String,Integer> result=new LinkedHashMap<>();
     ResultSet rs=ps7.executeQuery();
     while(rs.next())
     {
         result.put(rs.getString(1),rs.getInt(2));
     }
     return result;
 }
}



   
