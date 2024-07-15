/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Entity.MentorSkill;
import Entity.Slot;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI
 */
public class SlotDBContext extends DBContext<Slot> {

    public List<Slot> ListSlotByDay(String day) throws SQLException {
        String query = "SELECT id,value  FROM SLOT WHERE day = ? ORDER BY value ASC";
        List<Slot> listSlotByDay = new ArrayList<>();
        try (PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setString(1, day);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Slot x = new Slot();
                    x.setId(rs.getString("id"));
                    x.setValue(rs.getInt("value"));
                    listSlotByDay.add(x);
                }
            }

            return listSlotByDay;
        }

    }

    public static void main(String[] args) throws SQLException {
        SlotDBContext x = new SlotDBContext();
        List<Slot> slot = x.ListSlotByDay("slot1");        
        System.out.println(slot.size());
    }
}
