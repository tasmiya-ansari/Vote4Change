<%-- 
    Document   : loginresponse
    Created on : 6 May, 2021, 12:58:25 AM
    Author     : tasmi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String userid=(String)request.getAttribute("userid");
    String result=(String)request.getAttribute("result");
    
if(userid!=null && result!=null)//i.e user login kr gya h...servlet se aa rha h login krke...agr direct aaya hoga to both null agr servlet se aaya to amybe possible result null ho i.e login successfull na ho
{
    HttpSession sess= request.getSession();//arg me true ya false nhi qk hme session chahiye hi chahiye
    sess.setAttribute("userid", userid);//for other pages to decide whether they are geing accessed directly or after login.
    String url="";
    
    if(result.equalsIgnoreCase("Admin"))
    {
       url="AdminControllerServlet;jsessionid="+sess.getId();//jb session bnta h to ek cookie bnta h wo cookie browser ko milta h jo wo sendback krta h server ko lekin is baar request browser nhi ajax send krega or ajax cookie nhi bhejega //jsessionid bhi bhejenge qk ajax se redirect krenge 
        
    }
    else 
    {
       url="VoterControllerServlet;jsessionid="+sess.getId(); 
    }
    out.println(url);
}
else
    out.println("error");

%>