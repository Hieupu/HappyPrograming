/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.Skill;

import Controller.Login.BaseRequiredAuthenticationController;
import Dal.SkillDBContext;
import Entity.Account;
import Entity.Skill;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * UpdateSkillController is responsible for handling skill update requests.
 */
public class UpdateSkillController extends BaseRequiredAuthenticationController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
        return "UpdateSkillController handles the update process of skills.";
    }
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param req servlet request
     * @param resp servlet response
     * @param account authenticated user account
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account)
    throws ServletException, IOException {
       String id = req.getParameter("id");
        SkillDBContext sdb = new SkillDBContext();
        try {
            Skill skill = sdb.getSkillById(id);
            if (skill != null) {
                req.setAttribute("skill", skill);
                req.getRequestDispatcher("../skill/updateskill.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Skill not found");
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        } 
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param req servlet request
     * @param resp servlet response
     * @param account authenticated user account
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account)
    throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        int status = Integer.parseInt(req.getParameter("status"));
        
        SkillDBContext skill = new SkillDBContext();
        try {
            skill.updateSkill(id, name, status);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateSkillController.class.getName()).log(Level.SEVERE, null, ex);
        }
        req.getRequestDispatcher("listskill").forward(req, resp);
    }

    
}
