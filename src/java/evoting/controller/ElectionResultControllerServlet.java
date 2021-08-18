/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.controller;

import evoting.dao.CandidateDAO;
import evoting.dao.VoteDAO;
import evoting.dto.CandidateDetails;
import evoting.dto.CandidateInfo;
import evoting.dto.VoteDTO;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tasmi
 */
public class ElectionResultControllerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd=null;
        HttpSession sess=request.getSession();
        String userid=(String)sess.getAttribute("userid");
        if(userid==null)
        {
            sess.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
        String id=(String)request.getParameter("id");
        try{
            if(id!=null && id.equals("getresultbycid"))
            {
                Map<String, Integer> result = VoteDAO.getResultByCandidateId();
                Set s = result.entrySet();
                Iterator it = s.iterator();
                LinkedHashMap<CandidateDetails, Integer> resultDetails = new LinkedHashMap<>();//jb class map me as key jati h hme hashcode or equals override krna pdta h
                while (it.hasNext()) {
                    Map.Entry<String, Integer> e = (Map.Entry) it.next();
                    CandidateDetails cd = CandidateDAO.getDetailsById(e.getKey());
                    resultDetails.put(cd, e.getValue());
                }

                request.setAttribute("totalvote", VoteDAO.getTotalVote());
                request.setAttribute("result", resultDetails);

                rd = request.getRequestDispatcher("resultbycid.jsp");
            }
            else if(id!=null && id.equals("getresultbycity"))
            {
                Map<String, Integer> result = VoteDAO.getResultByCity();
                Set s = result.entrySet();
                Iterator it = s.iterator();
                LinkedHashMap<CandidateDetails, Integer> resultDetails = new LinkedHashMap<>();//jb class map me as key jati h hme hashcode or equals override krna pdta h
                while (it.hasNext()) {
                    Map.Entry<String, Integer> e = (Map.Entry) it.next();
                    CandidateDetails cd = CandidateDAO.getDetailsById(e.getKey());
                    resultDetails.put(cd, e.getValue());
                }

                request.setAttribute("totalvote", VoteDAO.getTotalVote());
                request.setAttribute("result", resultDetails);

                rd = request.getRequestDispatcher("resultbycity.jsp");
            }
             else if(id!=null && id.equals("getresultbygender"))
            {
                Map<String, Integer> result = VoteDAO.getResultByGender();
                request.setAttribute("totalvote", VoteDAO.getTotalVote());
                request.setAttribute("result", result);

                rd = request.getRequestDispatcher("resultbygender.jsp");
            }
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            rd=request.getRequestDispatcher("showexception.jsp");
            request.setAttribute("exception", ex);
        }
        finally
        {
            if(rd!=null)
            {
                rd.forward(request,response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
