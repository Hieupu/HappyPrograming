/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.mentor;

import Controller.Login.BaseRequiredAuthenticationController;
import Dal.LoginDBContext;
import Dal.MentorSlotDBContext;
import Entity.Account;
import Entity.MentorSlot;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ViewScheduleController extends BaseRequiredAuthenticationController {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String raw_today = req.getParameter("date");
            java.sql.Date gettoday = null;
            if (raw_today == null) {
                Date today = new Date();
                gettoday = DateService.convertUtilDateToSqlDate(today);
            } else {
                gettoday = java.sql.Date.valueOf(raw_today);
            }
            java.sql.Date from = null;
            java.sql.Date to = null;
            Date startofWeek = DateService.getWeekStart(gettoday);
            from = DateService.convertUtilDateToSqlDate(startofWeek);

            to = DateService.convertUtilDateToSqlDate(DateService.
                    addDaysToDate(DateService.getWeekStart(gettoday), 6));

            ArrayList<java.sql.Date> dates = DateService.getDatesBetween(from, to);
            ArrayList<String> dayList = new ArrayList<>();
            for (Date d : dates) {
                String day = DateService.getDayName(d);
                dayList.add(day);
            }

            Account acc = new Account();
            LoginDBContext db = new LoginDBContext();
            acc = db.getAccountbyid(id);
            
            MentorSlotDBContext msdb = new MentorSlotDBContext();
            ArrayList<MentorSlot> mentorslot = msdb.listMentorSchedule(id);
               
            req.setAttribute("acc", acc);
            req.setAttribute("dayList", dayList);
            req.setAttribute("gettoday", gettoday);
            req.setAttribute("dates", dates);
            req.setAttribute("mentorslot", mentorslot);
            req.getRequestDispatcher("/mentor/viewschedule.jsp").forward(req, resp);
            
        } catch (SQLException ex) {
            Logger.getLogger(ViewScheduleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public class DateService {

        public static Date getWeekStart(Date date) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - calendar.getFirstDayOfWeek();
            if (dayOfWeek == 0) {
                calendar.add(Calendar.DAY_OF_MONTH, -6);
            } else {
                calendar.add(Calendar.DAY_OF_MONTH, -dayOfWeek + 1);
            }
            return calendar.getTime();
        }

        public static java.sql.Date convertUtilDateToSqlDate(java.util.Date utilDate) {
            if (utilDate != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(utilDate);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                return new java.sql.Date(calendar.getTimeInMillis());
            } else {
                return null;
            }
        }

        public static Date addDaysToDate(Date date, int days) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_YEAR, days);
            return calendar.getTime();
        }

        public static ArrayList<java.sql.Date> getDatesBetween(java.sql.Date fromDate, java.sql.Date toDate) {
            if (fromDate == null || toDate == null) {
                throw new IllegalArgumentException("Both fromDate and toDate cannot be null.");
            }
            if (fromDate.after(toDate)) {
                throw new IllegalArgumentException("fromDate cannot be after toDate.");
            }

            Date from = new Date(fromDate.getTime());
            Date to = new Date(toDate.getTime());

            Date temp = from;

            ArrayList<java.sql.Date> dates = new ArrayList<>();
            while (!temp.after(to)) {
                dates.add(convertUtilDateToSqlDate(temp));
                temp = addDaysToDate(temp, 1);
            }
            return dates;
        }

        public static String getDayName(Date date) {
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
            return sdf.format(date);
        }
    }
}
