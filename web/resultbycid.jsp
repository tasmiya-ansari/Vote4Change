<%-- 
    Document   : electionresult
    Created on : 12 Jun, 2021, 8:18:15 PM
    Author     : tasmi
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap,java.util.Iterator,evoting.dto.CandidateDetails" %>
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
            LinkedHashMap<CandidateDetails, Integer> resultDetails = (LinkedHashMap) request.getAttribute("result");
            int votecount = (int) request.getAttribute("totalvote");
            Iterator it = resultDetails.entrySet().iterator();

        %>
        <table>
            <tr>
                <th>Candidate Id</th>
                <th>Candidate Name</th>
                <th>Party</th>
                <th>Symbol</th>
                <th>Vote Count</th>
                <th>Vote %</th>
            </tr>
            <%                 while (it.hasNext()) {
                    Map.Entry<CandidateDetails, Integer> e = (Map.Entry) it.next();
                    CandidateDetails cd = e.getKey();
                    float voteper = (e.getValue() * 100.0f) / votecount;
            %>
            <tr>
                <td><%= cd.getCandidateId()%></td>
                <td><%= cd.getCandidateName()%></td>
                <td><%= cd.getParty()%></td>
                <td><img src="data:image/jpg;base64,<%= cd.getSymbol()%>" style="height:200px;width:300px;"/></td>
                <td><%= e.getValue()%></td>
                <td><%= voteper%></td>
            </tr>
            <%
                }
            %>
        </table>