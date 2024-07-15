/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.mentee;

import Controller.Login.BaseRequiredAuthenticationController;
import Dal.LoginDBContext;
import Entity.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class UpdateProfileController extends BaseRequiredAuthenticationController {

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
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String phone = req.getParameter("phone");
            int gender = Integer.parseInt(req.getParameter("gender"));
            String dobString = req.getParameter("dob");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            java.util.Date utilDate = dateFormat.parse(dobString);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            String address = req.getParameter("address");
            String fullname = req.getParameter("fullname");
            String email = req.getParameter("email");
            LoginDBContext db = new LoginDBContext();
            db.updateProfile(email, fullname, sqlDate, gender, phone, address, id);
            resp.sendRedirect("/HappyPrograming/mentee/viewmentee?id="+id);
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(UpdateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            Account acc = db.searchByID(id);
            req.setAttribute("acc", acc);
            req.getRequestDispatcher("updatementeeprofile.jsp").forward(req, resp);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
