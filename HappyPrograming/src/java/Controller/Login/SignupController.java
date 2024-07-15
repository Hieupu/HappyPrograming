package Controller.Login;

import Dal.LoginDBContext;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.EmailService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Admin
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 15)// 15MB

public class SignupController extends HttpServlet {

    private static final String SAVE_DIRECTORY = "mentee/uploadsMentee";
    private final EmailService emailService = new EmailService();
    private Map<String, String> verificationCodes = new HashMap<>();

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user input
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String code = generateVerificationCode();
        String phone = request.getParameter("phone");
        int gender = Integer.parseInt(request.getParameter("gender"));
        String dobString = request.getParameter("dob");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateOfBirth = LocalDate.parse(dobString, formatter);
        String address = request.getParameter("address");
        String fullname = request.getParameter("fullname");
        verificationCodes.put(email, code);
        LoginDBContext acc = new LoginDBContext();
        boolean isUsernameTaken;
        try {
            isUsernameTaken = acc.isUsernameTaken(username);
        } catch (SQLException ex) {
            Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
            return;
        }

        if (isUsernameTaken) {
            request.setAttribute("errorMessage", "Username already exists");
            if (role.equals("mentee")) {
                request.getRequestDispatcher("./auth/menteesignup.jsp").forward(request, response);
            } else if (role.equals("mentor")) {
                request.getRequestDispatcher("./auth/mentorsignup.jsp").forward(request, response);
            }
            return;
        }

        // Send verification email
        try {
            emailService.sendEmail(email, "Account Verification",
                    """
                            Dear user,
                        Thank you for registering. Here is your verify code:
                                    
                                    """ + code + "\n");
           
                request.setAttribute("email", email);
                request.setAttribute("username", username);
                request.setAttribute("password", password);
                request.setAttribute("role", role);
                request.setAttribute("phone", phone);
                request.setAttribute("address", address);
                request.setAttribute("dateOfBirth", dateOfBirth);
                request.setAttribute("fullname", fullname);
                request.setAttribute("gender", gender);
                request.getRequestDispatcher("./auth/verify.jsp").forward(request, response);
            
        } catch (RuntimeException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to send verification email");
        }
    }

    private String generateVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(999999);
        return String.format("%06d", code);
    }
}
