/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Entity.MentorAccount;
import Entity.MentorJoinMentorSkill;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI
 */
public class SearchMentorBySkillIdDBContext extends DBContext<MentorJoinMentorSkill> {

    public List<MentorJoinMentorSkill> SearchMentorBySkill(String skillId) throws SQLException {

        List<MentorJoinMentorSkill> list = new ArrayList<>();
        String query = "SELECT Account.fullname, Mentor.id, Mentor.ava, Mentor.job, Mentor.intro, Mentor.achivement, MentorSkill.skillid\n"
                + "FROM Mentor \n"
                + "join MentorSkill\n"
                + "ON MentorSkill.mentorid = Mentor.id\n"
                + "join Account \n"
                + "ON Mentor.accid = Account.id\n"
                + "Where MentorSkill.skillid = ?";
        try (java.sql.Connection conn = connection; PreparedStatement stm = conn.prepareStatement(query)) {
            stm.setString(1, skillId);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    MentorJoinMentorSkill x = new MentorJoinMentorSkill(
                            rs.getInt("id"),
                            rs.getString("fullname"),
                            rs.getString("ava"),
                            rs.getString("job"),
                            rs.getString("intro"),
                            rs.getString("achivement"),
                            rs.getString("skillId")
                    );
                    list.add(x);
                }
            }
            return list;
        }
    }

    public static void main(String[] args) throws SQLException {
        SearchMentorBySkillIdDBContext x = new SearchMentorBySkillIdDBContext();
        List<MentorJoinMentorSkill> list = x.SearchMentorBySkill("S01");
        System.out.println(list.get(1).getAva());
    }
}
