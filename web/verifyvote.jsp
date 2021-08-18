<%-- 
    Document   : verifyvote
    Created on : 11 Jun, 2021, 11:41:28 PM
    Author     : tasmi
--%>
<% 
String userid=(String)session.getAttribute("userid");
if(userid==null)
{
    session.invalidate();
    response.sendRedirect("accessdenied.html");
    return;
}
boolean result=(boolean)request.getAttribute("result");
if(result==true)
    out.println("success");
else
   out.println("failure");
%>