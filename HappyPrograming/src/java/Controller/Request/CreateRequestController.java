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
 * CreateRequestController handles the creation of requests.
 *
 */
public class CreateRequestController extends BaseRequiredAuthenticationController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account) throws ServletException, IOException {
        String title = request.getParameter("title");
        int mentorid = Integer.parseInt(request.getParameter("mentorid"));
        int menteeid = Integer.parseInt(request.getParameter("menteeid"));
        String skills = request.getParameter("skills");
        double fee = Double.parseDouble(request.getParameter("fee"));
        String status = "Open";
        String deadline = request.getParameter("localtime");
        String detail = request.getParameter("detail");

        ArrayList<String> schedule = new ArrayList<>();

        // Iterate over all parameter names to find the checked checkboxes
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (paramName.startsWith("slot_") && !paramName.endsWith("_slot") && !paramName.endsWith("_day") && !paramName.endsWith("_date")) {
                String[] slotParam = paramName.split("_");
                if (slotParam.length == 3) {
                    String slot = request.getParameter(paramName + "_slot");
                    String date = request.getParameter(paramName + "_date");
                    String day = request.getParameter(paramName + "_day");
                    String slotChar = (char) ('A' + Integer.parseInt(slot) - 1) + day;
                    String che = slotChar + "_" + date;
                    schedule.add(che);
                }
            }
        }

        String scheduleString = String.join(",", schedule);

        RequestDBContext requestDB = new RequestDBContext();
        SkillDBContext skillDB = new SkillDBContext();
        try {
            requestDB.createRequest(title, status, mentorid, fee, menteeid, deadline, scheduleString, detail);

            int reqid = requestDB.getRequestID(mentorid, menteeid, deadline);
            if (skills != null) {
                String skillid = skillDB.getSkillByName(skills);
                skillDB.insertSkillRequest(skillid, reqid);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreateRequestController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("menteeid", menteeid);
        request.getRequestDispatcher("/mentee/test.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account) throws ServletException, IOException {
        try {
            String mentorname = request.getParameter("fullname");
            String id = request.getParameter("id");
            SkillDBContext skillDB = new SkillDBContext();
            ArrayList<Skill> skills1 = skillDB.listActive();
            int mentorid = Integer.parseInt(request.getParameter("mentorid"));
            String raw_today = request.getParameter("date");
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

            request.setAttribute("acc", acc);
            request.setAttribute("dayList", dayList);
            request.setAttribute("gettoday", gettoday);
            request.setAttribute("dates", dates);
            request.setAttribute("mentorslot", mentorslot);
            request.setAttribute("skills1", skills1);
            request.setAttribute("mentorname", mentorname);
            request.getRequestDispatcher("/mentee/createrequest.jsp?id=" + id).forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CreateRequestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
