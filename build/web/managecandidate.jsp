<%-- 
    Document   : managecandidate
    Created on : 17 May, 2021, 6:28:30 PM
    Author     : tasmi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="jsscript/adminoptions.js"></script>
<script src="jsscript/jquery.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link href="stylesheet/backgroundimage.css" rel="stylesheet">
<link href="stylesheet/pageheader.css" rel="stylesheet">
<link href="stylesheet/admin.css" rel="stylesheet">
<title>Manage Candidate</title>
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
    <div class='subcandidate'>Manage Candidate Options</div><br><br>
    <div class='logout'><a href='login.html'>logout</a></div>
</div>
<div class='container'>
    <div id='dv1' onclick='showaddcandidateform()'>
        <img src='images/addcandidate.png' height='300px' width='300px'>
        <br><h3>Add Candidate</h3>
    </div>
    <div id='dv2' onclick='showupdatecandidateform()'>
        <img src='images/update1.jpg' height='300px' width='300px'>
        <br><h3>Update Candidate</h3>
    </div>
    <div id='dv3' onclick='showcandidate()'>
        <img src='images/candidate.jpg' height='300px' width='300px'>
        <br><h3>Show Candidate</h3>
    </div>
    <div id='dv4' onclick='showremovecandidate()'>
        <img src='images/update3.jpg' height='300px' width='300px'>
        <br><h3>Delete Candidate</h3>
    </div>
</div>
<br><br>

<div align='center' id='result'></div>
        
</body>
</html>

