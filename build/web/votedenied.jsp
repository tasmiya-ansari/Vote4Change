<%-- 
    Document   : votedenied
    Created on : 11 Jun, 2021, 7:43:12 PM
    Author     : tasmi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList,evoting.dto.CandidateInfo" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="jsscript/vote.js"></script>
        <script src="jsscript/jquery.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <link href="stylesheet/showcandidate.css" rel="stylesheet">
        <title>Vote Denied</title>
    </head>
    <body>
        <%
            String userid = (String) session.getAttribute("userid");
            if (userid == null) {
                response.sendRedirect("accessdenied.html");
                return;
            }
        %>
        <div class='sticky'>
            <div class='candidate'>VOTE FOR CHANGE</div><br>
            <div class='subcandidate'>You have already voted!</div>
            <div class='logout'><a href="LoginControllerServlet?logout=logout">Logout</a></div>
        </div>
        <div class='buttons'>
            <%
                CandidateInfo c = (CandidateInfo) request.getAttribute("candidate");
            %>
            <div class="candidateprofile"><p>Candidate Id:<%= c.getCandidateid()%><br>
                    Candidate Name:<%= c.getCandidatename()%><br>
                    Party:<%= c.getParty()%><br>
                    Symbol:<image src='data:image/jpg;base64,<%= c.getSymbol()%>' style="height:200px;width:300px;"/></p>
            </div>
        </div>
    </body>
</html>
