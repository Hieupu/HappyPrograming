/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Entity.MentorSlot;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author MSI
 */
public class MentorSlotDBContext extends DBContext<MentorSlot> {

    public void insertSlotIntoMentorSlot(int mentorid, String slotid, String date, String status) throws SQLException {
        String query = "INSERT INTO [dbo].[MentorSlot]\n"
                + "           ([mentorid]\n"
                + "           ,[slotid]\n"
                + "           ,[date]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?,?,?,?)";
        try (PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, mentorid);
            stm.setString(2, slotid);
            stm.setString(3, date);
            stm.setString(4, status);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void deleteMentorSlot(int id, String slotid) throws SQLException {
        String sql = "DELETE FROM [dbo].[MentorSlot] WHERE mentorid = ? and slotid = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, id);
        stm.setString(2, slotid);
        stm.executeUpdate();
    }

    public ArrayList<MentorSlot> listMentorSchedule(int mentorid) throws SQLException {
        ArrayList<MentorSlot> list = new ArrayList<>();
        String sql = " SELECT [slotid], [date], [status] FROM [dbo].[MentorSlot] WHERE [mentorid] = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, mentorid);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            MentorSlot m = new MentorSlot();
            String a;
            int b = (rs.getString("slotid").charAt(0) - 'A' + 1);
            a = String.valueOf(b);
            m.setSlotid(a);
            m.setDate(rs.getString("date"));
            m.setStatus(rs.getString("status"));
            list.add(m);
        }
        return list;
    }
}
