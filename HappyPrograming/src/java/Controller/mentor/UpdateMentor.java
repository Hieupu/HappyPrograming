/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.mentor;

import Dal.LoginDBContext;
import Dal.MentorDBContext;
import Dal.MentorSkillDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class UpdateMentor extends HttpServlet {

    private static final String SAVE_DIRECTORY = "uploads";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        //initialize objects
        LoginDBContext db = new LoginDBContext();
        MentorDBContext mentor = new MentorDBContext();
        MentorSkillDBContext mentorSkillDB = new MentorSkillDBContext();
        //get information from JSP
        String oldAva = request.getParameter("oldAva");
        String phone = request.getParameter("phone");
        int mentorid = Integer.parseInt(request.getParameter("mentorid"));
        int id = Integer.parseInt(request.getParameter("id"));
        int gender = Integer.parseInt(request.getParameter("gender"));
        String dobString = request.getParameter("dob");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = dateFormat.parse(dobString);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        String address = request.getParameter("address");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String job = request.getParameter("job");
        String intro = request.getParameter("intro");
        String achivement = request.getParameter("achivement");
        //solve save new avatar 
        Part part = request.getPart("ava");
        String filename = part.getSubmittedFileName();
        if (filename != null && !filename.isEmpty()) {
            String realPath = request.getServletContext().getRealPath("/") + SAVE_DIRECTORY;
            Path pathToFile = Paths.get(realPath, filename);
            if (!Files.exists(pathToFile.getParent())) {
                Files.createDirectories(pathToFile.getParent());
            }
            String filePath = realPath + "/" + filename;
            part.write(filePath);
            mentor.updateMentor(SAVE_DIRECTORY + "/" + filename, job, intro, achivement, id);
            String[] listSkill = request.getParameterValues("skill");
            if (listSkill != null && listSkill.length > 0) {
                mentorSkillDB.deleteOldMentorSkill(mentorid);
                for (String skill : listSkill) {
                    skill.trim();
                    mentorSkillDB.insertSkillToMentorSkill(skill, mentorid);
                }
            }
            db.updateProfile(email, fullname, sqlDate, gender, phone, address, id);
            request.getRequestDispatcher("loadMentor?id=" + id).forward(request, response);

        }
        String[] listSkill = request.getParameterValues("skill");
        if (listSkill != null && listSkill.length > 0) {
            mentorSkillDB.deleteOldMentorSkill(mentorid);
            for (String skill : listSkill) {
                skill.trim();
                mentorSkillDB.insertSkillToMentorSkill(skill, mentorid);
            }
        }
        mentor.updateMentor(oldAva, job, intro, achivement, id);
        db.updateProfile(email, fullname, sqlDate, gender, phone, address, id);
        request.getRequestDispatcher("loadMentor?id=" + id).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(UpdateMentor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateMentor.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException ex) {
            Logger.getLogger(UpdateMentor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateMentor.class.getName()).log(Level.SEVERE, null, ex);
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
