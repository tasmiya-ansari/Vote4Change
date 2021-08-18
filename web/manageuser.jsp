<%-- 
    Document   : manageusers
    Created on : 14 Jun, 2021, 5:20:36 PM
    Author     : tasmi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="jsscript/manageuser.js"></script>
<script src="jsscript/jquery.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link href="stylesheet/backgroundimage.css" rel="stylesheet">
<link href="stylesheet/pageheader.css" rel="stylesheet">
<link href="stylesheet/admin.css" rel="stylesheet">
<title>Manage Users</title>
</head>
<body>
<%
            String userid=(String)session.getAttribute("userid");
            if(userid==null)
            {
                response.sendRedirect("accessdenied.html");
                return;
            }
            %>
<div class='sticky'>
    <div class='candidate'>VOTE FOR CHANGE</div><br>
    <div class='subcandidate'>Manage Users Options</div><br><br>
    <div class='logout'><a href='login.html'>logout</a></div>
</div>
<div class='container'>
    <div id='dv1' onclick='showusers()'>
        <img src='images/show.png' height='300px' width='300px'>
        <br><h3>Show Users</h3>
    </div>
    <div id='dv2' onclick='showremoveuser()'>
        <img src='images/delete.jpg' height='300px' width='300px'>
        <br><h3>Remove User</h3>
    </div>
</div>
<br><br>

<div align='center' id='result'></div>
        
</body>
</html>
