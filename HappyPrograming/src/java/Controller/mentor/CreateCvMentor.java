/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.mentor;
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
import java.util.logging.Level;
import java.util.logging.Logger;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class CreateCvMentor extends HttpServlet {

    private static final String SAVE_DIRECTORY = "uploads";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        MentorDBContext mentorDB = new MentorDBContext();
        MentorSkillDBContext mentorSkillDB = new MentorSkillDBContext();
               
            int accid = Integer.parseInt(request.getParameter("id"));
            String job = request.getParameter("job");
            String intro = request.getParameter("intro");
            String achievement = request.getParameter("achivement");
            String status = "Waiting";
          
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

                mentorDB.insertCvMentor1(SAVE_DIRECTORY + "/" + filename, job, intro, achievement, accid, status);
                int id = mentorDB.getIdOfNewMentor();
                String[] listSkill = request.getParameterValues("skill");
                if (listSkill != null && listSkill.length > 0) {
                    for (String skill : listSkill) {
                        skill.trim();
                        mentorSkillDB.insertSkillToMentorSkill(skill, id);
                    }
                }
            }
            request.getRequestDispatcher("mentorlogedinhomepage.jsp").forward(request, response);
    
        
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
            Logger.getLogger(CreateCvMentor.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CreateCvMentor.class.getName()).log(Level.SEVERE, null, ex);
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
