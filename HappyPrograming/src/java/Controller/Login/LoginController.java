package Controller.Login;

import Controller.Skill.ListSkillController;
import Dal.GetInforMentorDBContext;
import Dal.LoginDBContext;
import Dal.SkillDBContext;
import Entity.Account;
import Entity.MentorAccount;
import Entity.Skill;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class LoginController extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        SkillDBContext skillDB = new SkillDBContext();
        ArrayList<Skill> skills = skillDB.listActive();
         GetInforMentorDBContext inforMentor = new GetInforMentorDBContext();  
        List<MentorAccount> mentorAccount = inforMentor.getAllMentor();

        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            request.setAttribute("skills", skills);
            request.setAttribute("mentorAccount", mentorAccount);
            LoginDBContext acc = new LoginDBContext();
            Account account;

            account = acc.checkLogin(username, password);

            if (account == null) {
                response.getWriter().println("login failed!");
            } else {
                String role = account.getRole();
                HttpSession session = request.getSession();
                session.setAttribute("account", account);
                session.setAttribute("userRole", role);

                if (null != role) {
                    switch (role) {
                        case "mentor" ->
                            request.getRequestDispatcher("mentorlogedinhomepage.jsp?id=" + account.getId()).forward(request, response);
                        case "mentee" ->
                            request.getRequestDispatcher("menteelogedinhomepage.jsp?id=" + account.getId()).forward(request, response);
                        case "admin" ->
                            request.getRequestDispatcher("adminlogedinhomepage.jsp?id=" + account.getId()).forward(request, response);
                    }
                }

                String remember = request.getParameter("remember");
                if (remember != null) {
                    Cookie cuser = new Cookie("username", username);
                    Cookie cpass = new Cookie("password", password);

                    cuser.setMaxAge(3600 * 24 * 7);
                    cpass.setMaxAge(3600 * 24 * 7);

                    response.addCookie(cpass);
                    response.addCookie(cuser);
                }
            }
        } catch (IOException | SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @return
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
