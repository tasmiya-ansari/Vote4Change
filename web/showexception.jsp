<%-- 
    Document   : showexception
    Created on : 1 May, 2021, 9:52:15 AM
    Author     : tasmi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Exception ex=(Exception)request.getAttribute("exception");
    System.out.println("Exception is : " +ex);//server pr msg
    out.println("Some Exception occured "+ ex.getMessage());//client pr msg
    
%>