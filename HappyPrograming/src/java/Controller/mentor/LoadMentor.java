/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.mentor;

import Dal.GetInforMentorDBContext;
import Dal.LoginDBContext;
import Dal.MentorJoinSkillDBContext;
import Dal.MentorSkillDBContext;
import Dal.SkillDBContext;
import Entity.Account;
import Entity.MentorAccount;
import Entity.MentorJoinSkill;
import Entity.Skill;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI
 */
public class LoadMentor extends HttpServlet {

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
            throws ServletException, IOException, SQLException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        LoginDBContext loginDB = new LoginDBContext();
        GetInforMentorDBContext mentor = new GetInforMentorDBContext();
        SkillDBContext Skill = new SkillDBContext();
        MentorJoinSkillDBContext mentorJoinSkill = new MentorJoinSkillDBContext();
        List<Skill> listSkill = Skill.list();
        Account account = loginDB.searchByID(id);
        MentorAccount mentorAccount = mentor.getmentorByAccId(id);
        List<MentorJoinSkill> skillOfMentor = mentorJoinSkill.getSkillByMentorId(mentorAccount.getId());  
        request.setAttribute("id", id);
        request.setAttribute("skills", listSkill);
        request.setAttribute("account", account);
        request.setAttribute("mentor", mentorAccount);
        request.setAttribute("skillOfMentor", skillOfMentor);    
        request.getRequestDispatcher("mentor/UpdateCvMentor.jsp").forward(request, response);

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
            Logger.getLogger(LoadMentor.class.getName()).log(Level.SEVERE, null, ex);
       
    }   catch (ParseException ex) {
            Logger.getLogger(LoadMentor.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LoadMentor.class.getName()).log(Level.SEVERE, null, ex);
       
    }   catch (ParseException ex) {
            Logger.getLogger(LoadMentor.class.getName()).log(Level.SEVERE, null, ex);
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
