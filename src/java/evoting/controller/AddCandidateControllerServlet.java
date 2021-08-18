/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.controller;

import evoting.dao.CandidateDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

/**
 *
 * @author tasmi
 */
public class AddCandidateControllerServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out=response.getWriter();
        HttpSession sess=request.getSession();
        String userid=(String)sess.getAttribute("userid");
        if(userid==null)//agr direct page access krliya to usrid null hogi
        {
            sess.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
        JSONObject json=new JSONObject();
        StringBuffer sb=new StringBuffer();
        
        String id=(String)request.getParameter("id");
        
        try
        {
            if(id !=null && id.equals("getid"))
            {
                String cid=CandidateDAO.getNewId();
                ArrayList<String> uidList=CandidateDAO.getAllUnusedUserIds();
                sb.append("<option value=' '>Select user id</option>");
                for(String uid:uidList)
                {
                    sb.append("<option value='"+uid+"'>"+uid+"</option>");
                }
                json.put("cid", cid);
                json.put("uid",sb.toString());
                out.println(json);
            }
            else if(id !=null && id.equals("getcity"))
            {
                String usid=(String)request.getParameter("uid");
                String username=CandidateDAO.getUsernameById(usid);
                ArrayList<String> city=CandidateDAO.getCity();
                for(String c:city)
                {
                                    //arraylist ke liye 2 methods h phla ki ek ek city ko json array me bheje ya fr sari cities ko as option tag bhej de taki js me na krna pade ye
                    sb.append("<option value='"+c+"'>"+c+"</option>");//<option value='Bhopal'>Bhopal</option>...value wala server pr jaega or bich wala show hoga html pr
                }
                
                json.put("username",username);
                json.put("city",sb.toString());
                out.println(json);
            }
            else if(id !=null && id.equals("checkfor party"))
            {
                String party=(String)request.getParameter("party");
                String city=(String)request.getParameter("city");
                boolean result=CandidateDAO.partyAndCityExist(party, city);
                if(result)
                    out.println("failure");
                else
                    out.println("sucess");
            }
            
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
