/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Entity.ClassSlot;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI
 */
public class ClassSlotDBContext extends DBContext<ClassSlot> {
    public List<ClassSlot> ListSlotByClass(int mentorid) throws SQLException {
        String query = "SELECT classSlot.slotid, classes.name, classes.mentorid FROM ClassSlot JOIN Classes ON classSlot.classid = Classes.id WHERE mentorid= ?";
        List<ClassSlot> ListSlotByClass = new ArrayList<>();
        try (PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, mentorid);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    ClassSlot x = new ClassSlot();
                    x.setSlotid(rs.getString("slotid"));
                    x.setName(rs.getString("name"));
                    ListSlotByClass.add(x);
                }
            }

            return ListSlotByClass;
        }

    }

    public static void main(String[] args) throws SQLException {
        ClassSlotDBContext x = new ClassSlotDBContext();
        List<ClassSlot> slot = x.ListSlotByClass(1123);        
        System.out.println(slot.size());
    }
}
