<%-- 
    Document   : results
    Created on : 15 Jun, 2021, 1:36:39 AM
    Author     : tasmi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="jsscript/results.js"></script>
<script src="jsscript/jquery.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link href="stylesheet/backgroundimage.css" rel="stylesheet">
<link href="stylesheet/pageheader.css" rel="stylesheet">
<link href="stylesheet/admin.css" rel="stylesheet">
<link href="stylesheet/result.css" rel="stylesheet">
<title>View Results</title>
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
    <div class='subcandidate'>View Results Options</div><br><br>
    <div class='logout'><a href='login.html'>logout</a></div>
</div>
<div class='container'>
    <div id='dv1' onclick='resultbycandidates()'>
        <img src='images/result1.png' height='300px' width='300px'>
        <br><h3>On the basis of candidate</h3>
    </div>
    <div id='dv2' onclick='resultbyparty()'>
        <img src='images/result2.jpg' height='300px' width='300px'>
        <br><h3>On the basis of party</h3>
    </div>
    <div id='dv3' onclick='resultbygender()'>
        <img src='images/result3.jpg' height='300px' width='300px'>
        <br><h3>On the basis of gender</h3>
    </div>
    
</div>
<br><br>

<div align='center' id='result'></div>
        
</body>
</html>
