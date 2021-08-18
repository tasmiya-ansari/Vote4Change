<%-- 
    Document   : adminshowusers
    Created on : 14 Jun, 2021, 7:02:41 PM
    Author     : tasmi
--%>

<%@page import="evoting.dto.UserDetails"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.json.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    
    if(result!=null && result.equals("userIdList"))
    {
        ArrayList<String> userIdList=(ArrayList<String>)request.getAttribute("userIdList");
        sb.append("<option value=' '>Select user id</option>");
        for(String u:userIdList)
        {
            sb.append("<option value='"+u+"'>"+u+"</option>");
        }
        json.put("userIdList",sb.toString());
    }
    else if (result!=null && result.equals("details"))
    {
        UserDetails ud=(UserDetails)request.getAttribute("user");
        sb.append("<table><tr><th>User Name:</th><td>"+ud.getUsername()+"<td></tr>");
        sb.append("<tr><th>Email:</th><td>"+ud.getEmail()+"<td></tr>");
        sb.append("<tr><th>Mobile No:</th><td>"+ud.getMobile_no()+"<td></tr>");
        sb.append("<tr><th>Adress:</th><td>"+ud.getAddress()+"<td></tr>");
        sb.append("<tr><th>City</th><td>"+ud.getCity()+"<td></tr></table>");
        
        json.put("details",sb.toString());
    }
    out.println(json);
%>
