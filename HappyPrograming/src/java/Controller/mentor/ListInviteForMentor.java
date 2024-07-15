/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.mentor;

import Dal.RequestDBContext;
import Dal.SkillDBContext;
import Entity.Request;
import Entity.Skill;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI
 */
public class ListInviteForMentor extends HttpServlet {

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
        throws ServletException, IOException, SQLException {
    response.setContentType("text/html;charset=UTF-8");
    int accountid = Integer.parseInt(request.getParameter("id"));
    RequestDBContext requestDB = new RequestDBContext();
    SkillDBContext skillDB = new SkillDBContext();
    List<Request> listRequest = requestDB.getAllRequestByMentorId(accountid);
    List<Skill> listSkill = new ArrayList<>();

    int mentorid = -1; 
    if (!listRequest.isEmpty()) {
        mentorid = listRequest.get(0).getMentorid();
        for (Request x : listRequest) {
            Skill skill = skillDB.getSkillById(x.getSkillid());
            listSkill.add(skill);
        }
    }

    request.setAttribute("mentorid", mentorid);
    request.setAttribute("listSkill", listSkill);
    request.setAttribute("listRequest", listRequest);
    request.getRequestDispatcher("mentor/ListInviteForMentor.jsp").forward(request, response);
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
try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ListInviteForMentor.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ListInviteForMentor.class.getName()).log(Level.SEVERE, null, ex);
        }
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