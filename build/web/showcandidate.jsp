<%-- 
    Document   : showcandidate
    Created on : 5 Jun, 2021, 9:05:43 PM
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
        <title>Show Candidate</title>
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
            <div class='subcandidate'>Whom do you want to vote?</div>
            <div class='logout'><a href='login.html'>Logout</a></div>
        </div>
        <div class='buttons'>
            <%
                ArrayList<CandidateInfo> candidateList = (ArrayList<CandidateInfo>) request.getAttribute("candidateList");
                for (CandidateInfo c : candidateList) {
            %>
            <input type='radio' name='flat' id='<%= c.getCandidateid()%>' value='<%= c.getCandidateid()%>' onclick='addVote()'>
            <label for='<%= c.getCandidateid()%>'>
                <image src='data:image/jpg;base64,<%= c.getSymbol()%>' style="height:200px;width:300px;"/>
            </label>
            <br><div class="candidateprofile"><p>Candidate Id:<%= c.getCandidateid()%><br>
                    Candidate Name:<%= c.getCandidatename()%><br>
                    Party:<%= c.getParty()%></p>
            </div>
                    <%
                        }
                    %>  
        </div>
    </body>
</html>


