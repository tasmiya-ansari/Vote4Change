<%-- 
    Document   : registrationresponse
    Created on : 1 May, 2021, 9:45:28 AM
    Author     : tasmi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <%
            boolean result=(boolean)request.getAttribute("result");
            boolean userfound=(boolean)request.getAttribute("userfound");
            
            if(userfound==true)
                out.println("uap");
            else if(result==true)
                out.println("success");
            else
                out.println("error");
        %>
        
