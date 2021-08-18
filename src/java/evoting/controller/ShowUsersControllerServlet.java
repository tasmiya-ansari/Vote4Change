/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.controller;

import evoting.dao.UserDAO;
import evoting.dto.UserDetails;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class ShowUsersControllerServlet extends HttpServlet {

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
        PrintWriter out=response.getWriter();
        HttpSession sess=request.getSession();
        String userid=(String)sess.getAttribute("userid");
        if(userid==null)//agr direct page access krliya to usrid null hogi
        {
            sess.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
            try
            {
                ArrayList<UserDetails> userDetailsList=UserDAO.getVoterDetails();
                String str="<table style='border:1px solid; cellspacing:1px;margin: 0 auto;border-collapse: collapse;'><tr><th>User Id</th><th>User Name</th><th>Address</th><th>City</th><th>Email</th><th>Mobile No.</th></tr>";
                for(UserDetails u:userDetailsList)
                {
                    str+="<tr><th>"+u.getAdahar_no()+"</th><th>"+u.getUsername()+"</th><th>"+u.getAddress()+"</th><th>"+u.getCity()+"</th><th>"+u.getEmail()+"</th><th>"+u.getMobile_no()+"</th></tr>";
                }
                str+="</table>";
                out.println(str);//srf ek id ke liye jsp bnana padta simple value h isliye yhi se return krwadiya
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                RequestDispatcher rd=request.getRequestDispatcher("showexception.jsp");
                request.setAttribute("exception", ex);
                rd.forward(request,response);
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
