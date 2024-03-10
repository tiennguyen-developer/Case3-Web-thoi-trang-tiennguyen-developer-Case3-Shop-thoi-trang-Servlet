
package com.codegym.storage.control;




import com.codegym.storage.dao.DAO;
import com.codegym.storage.entity.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "ChangePassControl", urlPatterns = {"/changepass"})
public class ChangePassControl extends HttpServlet {

	private static final long serialVersionUID = -2991155897139265090L;

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String pass = request.getParameter("newpass");
        String currentpass = request.getParameter("curpass");
        String verpass = request.getParameter("verpass");
        
        
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        int uid = a.getUid();
        String cpass = a.getPass();
        if((cpass.equals(currentpass)) && (pass.equals(verpass)) ) {
        
        
        
	        DAO dao = new DAO();
	        dao.editPassWord(pass, uid);
	        request.setAttribute("messuccess", "Success !");
	        request.getRequestDispatcher("ChangePass.jsp").forward(request, response);
        }
        else {
        	 request.setAttribute("meswrong", "Wrong current password or verify password !");
        	 request.getRequestDispatcher("ChangePass.jsp").forward(request, response);
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
