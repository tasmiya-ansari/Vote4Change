<%-- 
    Document   : resultbygender
    Created on : 16 Jun, 2021, 7:30:37 PM
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
            LinkedHashMap<String, Integer> resultDetails = (LinkedHashMap) request.getAttribute("result");
            int votecount = (int) request.getAttribute("totalvote");
            Iterator it = resultDetails.entrySet().iterator();

        %>
        <table>
            <tr>
                <th>Gender</th>
                <th>Vote Count</th>
                <th>Vote %</th>
            </tr>
            <%      while (it.hasNext()) {
                    Map.Entry<String, Integer> e = (Map.Entry) it.next();
            %>
            <tr>
                <td><%= e.getKey() %></td>
                <td><%= e.getValue() %></td>
                <td><%= (e.getValue() * 100.0f) / votecount%></td>
            </tr>
            <%
                }
            %>
        </table>
