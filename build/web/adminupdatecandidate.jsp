<%-- 
    Document   : adminupdatecandidate
    Created on : 13 Jun, 2021, 6:22:45 PM
    Author     : tasmi
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.json.JSONObject" %>
<%@page import="evoting.dto.CandidateDetails" %>
<%@page import="java.util.ArrayList" %>
        
<% 
    String userid=(String)session.getAttribute("userid");
    if(userid==null)
    {
        response.sendRedirect("accessdenied.html");
        return;
    }
    String result=(String)request.getAttribute("result");
    JSONObject json=new JSONObject();
    StringBuffer sb=new StringBuffer();
    
    if(result!=null && result.equals("candidateList"))
    {
        ArrayList<String> cidList=(ArrayList<String>)request.getAttribute("cidList");
        sb.append("<option value=' '>Select candidate id</option>");
        for(String c:cidList)
        {
            sb.append("<option value='"+c+"'>"+c+"</option>");
        }
        json.put("cidList",sb.toString());
    }
    else if (result!=null && result.equals("details"))
    {
        CandidateDetails cd=(CandidateDetails)request.getAttribute("candidate");
        
        sb.append("<form method='POST' enctype='multipart/form-data' id='fileUploadForm'>"+
                "<table><tr><th>User Id:</th><td><input type='text' id='uid' value='"+cd.getUserId()+"' disabled></td></tr>"+
                 "<tr><th>Candidate Name:</th><td><input type='text' id='cname' value='"+cd.getCandidateName()+"' disabled></td></tr>"+
                "<tr><th>City:</th><td><input type='text' id='city' value='"+cd.getCity()+"'></td></tr>"+
                "<tr><th>Party:</th><td><input type='text' id='party' value='"+cd.getParty()+"'></td></tr>"+
                "<tr><td colspan='2'><input type='file' name='files' value='Select Image' id='file'></td></tr>"+
                "<tr><th><input type='button' value='Update Candidate' onclick='updatecandidate()' id='updatecnd'></th>"+
                "<th><input type='reset' value='Clear' onclick='clearText()'></th></tr></table></form>");
//        
        json.put("details",sb.toString());
    }
    out.println(json);
%>
