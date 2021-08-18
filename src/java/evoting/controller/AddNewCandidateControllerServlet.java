/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.controller;

import evoting.dao.CandidateDAO;
import evoting.dto.CandidateDTO;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

/**
 *
 * @author tasmi
 */
public class AddNewCandidateControllerServlet extends HttpServlet {

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
        
        try
        {
            //data ajax send kr rha h FormData class ke through..ye class string style me data send nhi krti..wo bgejti h as object of FileItem class..jitna data utne fileitems
            //parameters ko retrive krne me HttpServletRequest koi help nhi kr skti...ek or class h ServletFileUpload
            DiskFileItemFactory df=new DiskFileItemFactory();
            ServletFileUpload sfu=new ServletFileUpload(df);//iska cons leta obj of class DiskFileItemFactory
            ServletRequestContext src=new ServletRequestContext(request);//ye  arg leta h HttpServletRequest i.e request
            List<FileItem> multiList=sfu.parseRequest(src);//ye method arg leta h ServletRequestContext type ka
            //ajax se jo data aa rha h wo aa rha h FileItem via DataForm..usko retrieve krne ke liye method h parseRequest of ServletFileUpload class which return ArrayList of FileItem 
            //ServletRequestContext leta h HttpRequest obj as parameter  qk jo data aa rha h usme fileh ..agr file bht badi h to usi wo temp disk me save krega kch filehandling lgegi jiska 
            //jiska logic DiskFileItemFactory class me h
            //parseRequest ko data dena padhta h ..data request ke pas h but use hm likh nhi skte phle use hm src se connect krenge
            
            ArrayList<String> objList=new ArrayList<>();
            InputStream ins=null;
            
            for(FileItem fit:multiList)//fit me textual data bhi ho skta h ya fr image
                //isliye ek methos h isFormFileld which returns true if obj me textual data h or false if obj me image h
            {
                if(fit.isFormField())//textual data
                {
                    String value=fit.getString();//filled info
                    String fname=fit.getFieldName();//key aayegi like cid n all
                    objList.add(value);
//                    System.out.println("Inside if");
//                    System.out.println(fname+":"+value);
                }
                else//image
                {
                    ins=fit.getInputStream();
                    String key=fit.getFieldName();//image wali field ka naam form me jo h...files h hmare me
                    String fname=fit.getName();//image ka name
//                    System.out.println("Inside else");
//                    System.out.println(key+":"+fname);
                }
            } //data ek hi order me jaega hmesha chahe jitni baar bhi run krlo
            //Inside else..files:aapLogo.jpg..Inside if..cid:C101..Inside if..uid:1234..Inside if..cname:Fatima Ansari..Inside if..party:aap..Inside if..city: Bhopal
            CandidateDTO c =new CandidateDTO(objList.get(0),objList.get(3),objList.get(1),ins,objList.get(4));
            boolean result=CandidateDAO.addCandidate(c);
//            System.out.println(result);
            if(result)
            {
                rd=request.getRequestDispatcher("success.jsp");
            }
            else
                rd=request.getRequestDispatcher("failure.jsp");
        }
        catch(Exception ex)
        {
            System.out.println("Exception in AddNewCandiddateController");
            ex.printStackTrace();
        }
        finally
        {
            if(rd!=null)
                rd.forward(request, response);
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
