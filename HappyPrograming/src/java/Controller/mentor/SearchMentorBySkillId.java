/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.mentor;

import Dal.GetInforMentorDBContext;
import Dal.MentorJoinSkillDBContext;
import Dal.MentorSkillDBContext;
import Entity.MentorAccount;
import Entity.MentorSkill;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI
 */
public class SearchMentorBySkillId extends HttpServlet {

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
        String[] listSkill = request.getParameterValues("skill");
        GetInforMentorDBContext inforMentorDB = new GetInforMentorDBContext();
        if (listSkill.length == 3) {
            String skill1 = listSkill[0];
            String skill2 = listSkill[1];
            String skill3 = listSkill[2];
            MentorSkillDBContext mentorSkill = new MentorSkillDBContext();
            GetInforMentorDBContext getInfor = new GetInforMentorDBContext();
            List<MentorAccount> listInfor = new ArrayList<>();
            List<MentorSkill> list = mentorSkill.getMentorBySkill3(skill1, skill2, skill3);
            for (MentorSkill x : list) {
                MentorAccount ma = new MentorAccount();
                ma = getInfor.getmentorById(x.getMentorId());
                listInfor.add(ma);
            }
            request.setAttribute("list", list);
            request.setAttribute("listInfor", listInfor);
            request.getRequestDispatcher("mentor/SearchMentorBySkill.jsp").forward(request, response);
        } else if (listSkill.length == 2) {
            String skill1 = listSkill[0];
            String skill2 = listSkill[1];
            MentorSkillDBContext mentorSkill = new MentorSkillDBContext();
            GetInforMentorDBContext getInfor = new GetInforMentorDBContext();
            List<MentorAccount> listInfor = new ArrayList<>();
            List<MentorSkill> list = mentorSkill.getMentorBySkill2(skill1, skill2);
            for (MentorSkill x : list) {
                MentorAccount ma = new MentorAccount();
                ma = getInfor.getmentorById(x.getMentorId());
                listInfor.add(ma);
            }
            request.setAttribute("list", list);
            request.setAttribute("listInfor", listInfor);
            request.getRequestDispatcher("mentor/SearchMentorBySkill.jsp").forward(request, response);
        } else if (listSkill.length == 1) {
            String skill1 = listSkill[0];
            MentorSkillDBContext mentorSkill = new MentorSkillDBContext();
            GetInforMentorDBContext getInfor = new GetInforMentorDBContext();
            List<MentorAccount> listInfor = new ArrayList<>();
            List<MentorSkill> list = mentorSkill.getMentorBySkill1(skill1);
            for (MentorSkill x : list) {
                MentorAccount ma = new MentorAccount();
                ma = getInfor.getmentorById(x.getMentorId());
                listInfor.add(ma);
            }
            request.setAttribute("list", list);
            request.setAttribute("listInfor", listInfor);
            request.getRequestDispatcher("mentor/SearchMentorBySkill.jsp").forward(request, response);
        } else {
            List<MentorAccount> listInfor = new ArrayList<>();
            listInfor = inforMentorDB.getAllMentor();
            request.setAttribute("listInfor", listInfor);
            request.getRequestDispatcher("mentor/SearchMentorBySkill.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SearchMentorBySkillId.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SearchMentorBySkillId.class.getName()).log(Level.SEVERE, null, ex);
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
