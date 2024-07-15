package Controller.Request;

import Controller.Login.BaseRequiredAuthenticationController;
import Dal.RequestDBContext;
import Entity.Account;
import Entity.Request;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles the listing of requests for the currently logged-in mentee.
 */
public class MenteeListRequestController extends BaseRequiredAuthenticationController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    /**
     *
     * @param req
     * @param res
     * @param account
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res, Account account) throws ServletException, IOException {

        SimpleDateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        String id = req.getParameter("id");
        int menteeid = Integer.parseInt(id);
        
        RequestDBContext requestDB = new RequestDBContext();
        ArrayList<Request> requests = new ArrayList<>();
        try {
            
            requests = requestDB.getRequestsByMenteeId(menteeid);

            for (Request r : requests) {
            int reqid = r.getId();
            String dateString = r.getTime();
            Date date = inputFormatter.parse(dateString);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            date = calendar.getTime();

            r.setTime(outputFormatter.format(date));
            Date currentDate = new Date();

            if (!currentDate.before(date) && (!r.getStatus().equals("Accepted") && !r.getStatus().equals("Rejected"))) {
                requestDB.outofDealine(reqid);
            }
                }
        } catch (SQLException e) {
            Logger.getLogger(MenteeListRequestController.class.getName()).log(Level.SEVERE, "Database error while listing requests", e);
            throw new ServletException("Database error while listing requests", e);
        } catch (ParseException ex) {
            Logger.getLogger(MenteeListRequestController.class.getName()).log(Level.SEVERE, "Date parse error", ex);
        }

        req.setAttribute("requests", requests);
        req.getRequestDispatcher("listrequest.jsp?id=" + id).forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        // Implement the doPost method if needed
    }
}
