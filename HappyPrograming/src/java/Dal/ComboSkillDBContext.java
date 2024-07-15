/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Entity.ComboSkill;
import Entity.Request;
import com.sun.jdi.connect.spi.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.SQLException;
import java.util.List;

public class ComboSkillDBContext extends DBContext<ComboSkill> {
    public List<ComboSkill> getSkillByReqid(int reqid) throws SQLException {
        List<ComboSkill> listComboSkill = new ArrayList<>();
        String query = "Select * From ComboSkill where comboskill.reqid = ?";
        try (java.sql.Connection conn = connection; PreparedStatement stm = conn.prepareStatement(query)) {
            stm.setInt(1, reqid);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    ComboSkill s = new ComboSkill();
                    s.setSkillid(rs.getString("skillid"));
                    s.setReqid(rs.getInt("reqid"));                 
                    listComboSkill.add(s);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching requests from the database", e);
        }
            return listComboSkill;
        }
    
    public static void main(String[] args) throws SQLException{
        ComboSkillDBContext x = new ComboSkillDBContext();
        List<ComboSkill> listComboSkill = new ArrayList<>();
        listComboSkill = x.getSkillByReqid(67);
        System.out.println(listComboSkill.size());      
    }
    

}
