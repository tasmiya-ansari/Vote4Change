<%-- 
    Document   : votingresponse
    Created on : 11 Jun, 2021, 11:47:20 PM
    Author     : tasmi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="evoting.dto.CandidateInfo" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <link href="stylesheet/showcandidate.css" rel="stylesheet">
        <title>Vote Details</title>
    </head>
    <body>
        <div class='sticky'>
            <div class='candidate'>VOTE FOR CHANGE</div><br>
            <%
                String userid = (String) session.getAttribute("userid");
                if (userid == null) {
                    response.sendRedirect("accessdenied.html");
                    return;
                }
                CandidateInfo c = (CandidateInfo) session.getAttribute("candidate");
                if (c == null) {
            %>
            <div class='subcandidate'>Sorry!Your vote cannot be casted.</div>
              <div class='logout'><a href="LoginControllerServlet?logout=logout">Logout</a></div>
<!--            <div>
                <h4>
                    <a href="LoginControllerServlet?logout='logout'">Logout</a>
                </h4>
            </div>-->
        </div>
            <%
                }
                else
                {
            %>
            <div class='subcandidate'>Thank you for voting!</div><br><br>
            <div class='subcandidate'>Your vote has been casted successfully!</div>
            <div class='logout'><a href="LoginControllerServlet?logout=logout">Logout</a></div>
            <div class="candidateprofile">
                <p>Candidate Id:<%= c.getCandidateid()%><br><br>
                    <strong>You voted for</strong> <br><image src='data:image/jpg;base64,<%= c.getSymbol()%>' style="height:200px;width:300px;"/><br><br>
                    Candidate Name:<%= c.getCandidatename()%><br>
                    Party:<%= c.getParty()%>
                </p>
            </div>
    </div>
            <%
                }
            %>
    </body>
</html>
