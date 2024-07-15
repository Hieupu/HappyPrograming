/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Request;

import Controller.Login.BaseRequiredAuthenticationController;
import Controller.mentor.ViewScheduleController;
import Dal.LoginDBContext;
import Dal.MentorSlotDBContext;
import Dal.RequestDBContext;
import Dal.SkillDBContext;
import Entity.Account;
import Entity.MentorSlot;
import Entity.Skill;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class UpdateRequestController extends BaseRequiredAuthenticationController {

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res, Account account) throws ServletException, IOException {
        String title = req.getParameter("title");
        int menteeid = Integer.parseInt(req.getParameter("menteeid"));

        String skills = req.getParameter("skills");
        String detail = req.getParameter("detail");

        double fee = Double.parseDouble(req.getParameter("fee"));
        ArrayList<String> schedule = new ArrayList<>();

        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (paramName.startsWith("slot_") && !paramName.endsWith("_slot") && !paramName.endsWith("_day") && !paramName.endsWith("_date")) {
                String[] slotParam = paramName.split("_");
                if (slotParam.length == 3) {
                    String slot = req.getParameter(paramName + "_slot");
                    String date = req.getParameter(paramName + "_date");
                    String day = req.getParameter(paramName + "_day");
                    String slotChar = (char) ('A' + Integer.parseInt(slot) - 1) + day;
                    String che = slotChar + "_" + date;
                    schedule.add(che);
                }
            }
        }

        String scheduleString = String.join(",", schedule);

        RequestDBContext requestDB = new RequestDBContext();
        SkillDBContext skillDB = new SkillDBContext();
        int reqid = Integer.parseInt(req.getParameter("id"));
        try {
            requestDB.updateRequest(title, fee, scheduleString, detail, reqid);
            skillDB.deletetoUpdate(reqid);
            if (skills != null) {
                skillDB.insertSkillRequest(skills, reqid);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UpdateRequestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        req.setAttribute("menteeid", menteeid);
        req.getRequestDispatcher("/mentee/test.jsp").forward(req, res);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res, Account account) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String title = req.getParameter("title");
            String schedule = req.getParameter("schedule");
            String[] scheduleItems = schedule.split(",");

            ArrayList<Integer> listSlot = new ArrayList<>();
            ArrayList<String> listDate = new ArrayList<>();

            for (String item : scheduleItems) {
                String[] parts = item.split("_");
                if (parts.length == 2) {
                    String slotNumber = parts[0].substring(0); 
                    char s = slotNumber.charAt(0);
                    int slotLetter = (s - 'A' + 1); 
                    listSlot.add(slotLetter);
                    listDate.add(parts[1]);
                }
            }
            int mentorid = Integer.parseInt(req.getParameter("mentorid"));
            String detail = req.getParameter("detail");
            SkillDBContext skillDB = new SkillDBContext();
            ArrayList<Skill> skills1;
            ArrayList<Skill> skills2;
            skills1 = skillDB.listActive();
            skills2 = skillDB.listSkillinRequest(id);
            String raw_today = req.getParameter("date");
            java.sql.Date gettoday = (raw_today == null)
                    ? ViewScheduleController.DateService.convertUtilDateToSqlDate(new Date())
                    : java.sql.Date.valueOf(raw_today);

            java.sql.Date from = ViewScheduleController.DateService.convertUtilDateToSqlDate(
                    ViewScheduleController.DateService.getWeekStart(gettoday));
            java.sql.Date to = ViewScheduleController.DateService.convertUtilDateToSqlDate(
                    ViewScheduleController.DateService.addDaysToDate(from, 6));

            ArrayList<java.sql.Date> dates = ViewScheduleController.DateService.getDatesBetween(from, to);
            ArrayList<String> dayList = new ArrayList<>();
            for (Date d : dates) {
                dayList.add(ViewScheduleController.DateService.getDayName(d));
            }

            Account acc = new LoginDBContext().getAccountbyid(mentorid);
            MentorSlotDBContext msdb = new MentorSlotDBContext();
            ArrayList<MentorSlot> mentorslot = msdb.listMentorSchedule(mentorid);

            req.setAttribute("listSlot", listSlot);
            req.setAttribute("listDate", listDate);
            req.setAttribute("detail", detail);
            req.setAttribute("acc", acc);
            req.setAttribute("dayList", dayList);
            req.setAttribute("gettoday", gettoday);
            req.setAttribute("dates", dates);
            req.setAttribute("mentorslot", mentorslot);
            req.setAttribute("skills1", skills1);
            req.setAttribute("skills1", skills1);
            req.setAttribute("skills2", skills2);
            req.setAttribute("title", title);
            req.setAttribute("schedule", schedule);
            req.getRequestDispatcher("/mentee/updaterequest.jsp?id=" + id).forward(req, res);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateRequestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
