/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Entity.MentorJoinSkill;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author MSI
 */
public class MentorJoinSkillDBContext extends DBContext<MentorJoinSkill> {
     public List<MentorJoinSkill> getSkillByMentorId(int id) throws SQLException {
         List<MentorJoinSkill> list = new ArrayList<>();
        String query = "Select MentorSkill.mentorid, MentorSkill.skillid, Skill.name FROM MentorSkill join Skill ON MentorSkill.skillid = skill.id where MentorSkill.mentorid = ?";
        try (java.sql.Connection conn = connection; PreparedStatement stm = conn.prepareStatement(query)) {
            stm.setInt(1, id);
              try (ResultSet rs = stm.executeQuery()) {
            while(rs.next()) {
                MentorJoinSkill x = new MentorJoinSkill();
                x.setMentorid(rs.getInt("mentorid"));
                x.setSkillid(rs.getString("skillid"));
                x.setName(rs.getString("name"));
                list.add(x);                   
            }
        }
        return list;
        }   
        }
     public static void main(String[] args) throws SQLException {
        MentorJoinSkillDBContext db = new MentorJoinSkillDBContext();
        List<MentorJoinSkill> list = new ArrayList<>();
        list = db.getSkillByMentorId(1123);
         System.out.println(list.get(0).getName());
    }
}
