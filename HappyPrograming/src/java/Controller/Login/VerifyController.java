package Controller.Login;

import Dal.InsertMenteeProfileDBContext;
import Dal.LoginDBContext;
import java.io.IOException;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.SimpleDateFormat;

/**
 *
 * @author Admin
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 15)// 15MB
public class VerifyController extends HttpServlet {
    private Map<String, String> verificationCodes;

    /**
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        // Initialize the verification codes map
        verificationCodes = (HashMap<String, String>) getServletContext().getAttribute("verificationCodes");
        if (verificationCodes == null) {
            verificationCodes = new HashMap<>();
            getServletContext().setAttribute("verificationCodes", verificationCodes);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve the user input
            String email = request.getParameter("email");
            String code = request.getParameter("code");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String role = request.getParameter("role");
            String phone = request.getParameter("phone");
            int gender = Integer.parseInt(request.getParameter("gender"));

            String dobString = request.getParameter("dob");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            
            java.util.Date utilDate = dateFormat.parse(dobString);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            
            String address = request.getParameter("address");
            String fullname = request.getParameter("fullname");
            // Validate the verification code
            String expectedCode = verificationCodes.get(email);
            if (expectedCode != null && expectedCode.equals(code)) {
                // Verification successful
                request.setAttribute("message", "Verification successful. You can now log in.");
                verificationCodes.remove(email);

                // Perform user registration
                LoginDBContext acc = new LoginDBContext();
                try {
                    acc.registerUser(username, password, role, email,fullname, sqlDate, gender, phone, address);
                } catch (SQLException ex) {
                    Logger.getLogger(VerifyController.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("message", "Registration failed due to a database error. Please try again.");
                    return;
                }
            } else {
                // Verification failed
                request.setAttribute("message", "Invalid verification code. Please try again.");
            }

            // Forward to the result page
            request.getRequestDispatcher("./auth/signin.jsp").forward(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(VerifyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
