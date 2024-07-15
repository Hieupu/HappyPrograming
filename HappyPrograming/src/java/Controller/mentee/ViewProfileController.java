/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.mentee;

import Controller.Login.BaseRequiredAuthenticationController;
import Dal.InsertMenteeProfileDBContext;
import Dal.LoginDBContext;
import Entity.Account;
import Entity.Mentee;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ViewProfileController extends BaseRequiredAuthenticationController {

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

    /**
     *
     * @param req
     * @param resp
     * @param account
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        processRequest(req, resp);
    }

    /**
     *
     * @param req
     * @param resp
     * @param account
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            LoginDBContext db = new LoginDBContext();
            InsertMenteeProfileDBContext MenteeDB = new InsertMenteeProfileDBContext();
            Mentee menteeAcc = MenteeDB.getInforMentee(id);
            String ava = menteeAcc.getAva();
            Account acc = db.searchByID(id);
            int i = acc.getGender();
            req.setAttribute("acc", acc);
            req.getRequestDispatcher("viewmenteeprofile.jsp").forward(req, resp);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
