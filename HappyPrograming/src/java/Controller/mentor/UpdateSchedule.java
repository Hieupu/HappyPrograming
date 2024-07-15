/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.mentor;

import Controller.Login.BaseRequiredAuthenticationController;
import Dal.MentorSlotDBContext;
import Entity.Account;
import Entity.MentorSlot;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class UpdateSchedule extends BaseRequiredAuthenticationController {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            String slotid = req.getParameter("slotid");
            String dateString = req.getParameter("date");
            String status = "Waiting";
            java.sql.Date gettoday = null;
            
            Date today = new Date();
            gettoday = ViewScheduleController.DateService.convertUtilDateToSqlDate(today);
            
            java.sql.Date from = null;
            java.sql.Date to = null;
            Date startofWeek = ViewScheduleController.DateService.getWeekStart(gettoday);
            from = ViewScheduleController.DateService.convertUtilDateToSqlDate(startofWeek);
            to = ViewScheduleController.DateService.convertUtilDateToSqlDate(ViewScheduleController.DateService.
                    addDaysToDate(ViewScheduleController.DateService.getWeekStart(gettoday), 6));
            ArrayList<java.sql.Date> dates = ViewScheduleController.DateService.getDatesBetween(from, to);
            ArrayList<String> dayList = new ArrayList<>();
            for (Date d : dates) {
                String day = ViewScheduleController.DateService.getDayName(d);
                dayList.add(day);
            }
            MentorSlotDBContext db = new MentorSlotDBContext();
            db.insertSlotIntoMentorSlot(id, slotid, dateString,status);
            
            MentorSlotDBContext msdb = new MentorSlotDBContext();
            ArrayList<MentorSlot> mentorslot = msdb.listMentorSchedule(id);
            
            req.setAttribute("mentorslot", mentorslot);
            req.setAttribute("dayList", dayList);
            req.setAttribute("gettoday", gettoday);
            req.setAttribute("dates", dates);
            req.getRequestDispatcher("/mentor/viewschedule.jsp").forward(req, resp);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateSchedule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
