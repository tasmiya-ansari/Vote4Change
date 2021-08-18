/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.controller;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import evoting.dao.VoteDAO;
import evoting.dto.CandidateInfo;
import evoting.dto.VoteDTO;
import java.io.IOException;
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
public class AddVoteControllerServlet extends HttpServlet {

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
        try{
            
            String candidateId=request.getParameter("candidateId");
            VoteDTO vote=new VoteDTO(candidateId,userid);
            boolean result=VoteDAO.addVote(vote);
            //if result=flase i.e vote nhi dal paya hoga tb bhi candidate obj mil jaega qk data candidate or user table se aa rha h jisme record h ...qk wo voting table se data mhi la rha jo null aaye
            CandidateInfo candidate=VoteDAO.getVote(candidateId);
            if(result==true)
                {
                    sess.setAttribute("candidate",candidate);//session me add krenge candidate object ko qk yha se result ki value jsp pr jaegi jo js ko response dega fr depending on response
                //js dusre jsp pr request bhejega jo btaega vote dla to kidko dla ya nhi dla to kyu
                }
            
            request.setAttribute("result", result);
            rd=request.getRequestDispatcher("verifyvote.jsp");
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
