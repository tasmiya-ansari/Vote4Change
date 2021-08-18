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
        //image show krne ke liye hm <img src="cd.getSymbol"> nhi likh skte qk browser ko lgega ye url h but ye Base64 image h
        String str="<img src='data:image/jpg;base64,"+cd.getSymbol()+"'style='width:200px;height:200px;'/>";
        sb.append("<table><tr><th>UserId:</th><td>"+cd.getUserId()+"<td></tr>");
        sb.append("<tr><th>Candidate Name:</th><td>"+cd.getCandidateName()+"<td></tr>");
        sb.append("<tr><th>City:</th><td>"+cd.getCity()+"<td></tr>");
        sb.append("<tr><th>Party:</th><td>"+cd.getParty()+"<td></tr>");
        sb.append("<tr><th>Symbol</th><td>"+str+"<td></tr></table>");
        
        json.put("details",sb.toString());
    }
    out.println(json);
%>