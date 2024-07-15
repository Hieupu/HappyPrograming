package Dal;

import Entity.MentorSkill;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MentorSkillDBContext extends DBContext<MentorSkill> {

    public void insertSkillToMentorSkill(String skillId, int id) throws SQLException {
        String query = "INSERT INTO MentorSkill VALUES(?, ?)";
        try (PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setString(1, skillId.trim());
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void deleteOldMentorSkill(int id) throws SQLException {
        String query = "DELETE FROM MentorSkill Where MentorSkill.mentorid = ?";
        try (PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<MentorSkill> getMentorBySkill3(String skill1, String skill2, String skill3) throws SQLException {
        List<MentorSkill> listMentorId = new ArrayList<>();
        String query = "SELECT mentorid\n"
                + "FROM MentorSkill JOIN Skill\n"
                + "ON Skill.id = MentorSkill.skillid\n"
                + "WHERE Skill.name IN (?, ?, ?)\n"
                + "GROUP BY mentorid\n"
                + "HAVING COUNT(DISTINCT skill.name) = 3;";
        try (java.sql.Connection conn = connection; PreparedStatement stm = conn.prepareStatement(query)) {
            stm.setString(1, skill1);
            stm.setString(2, skill2);
            stm.setString(3, skill3);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    MentorSkill x = new MentorSkill();
                    x.setMentorId(rs.getInt("mentorid"));
                    listMentorId.add(x);
                }
            }
            return listMentorId;
        }
    }
    public List<MentorSkill> getMentorBySkill2(String skill1, String skill2) throws SQLException {
        List<MentorSkill> listMentorId = new ArrayList<>();
        String query = "SELECT mentorid\n"
                + "FROM MentorSkill JOIN Skill\n"
                + "ON Skill.id = MentorSkill.skillid\n"
                + "WHERE Skill.name IN (?, ?)\n"
                + "GROUP BY mentorid\n"
                + "HAVING COUNT(DISTINCT skill.name) = 2;";
        try (java.sql.Connection conn = connection; PreparedStatement stm = conn.prepareStatement(query)) {
            stm.setString(1, skill1);
            stm.setString(2, skill2);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    MentorSkill x = new MentorSkill();
                    x.setMentorId(rs.getInt("mentorid"));
                    listMentorId.add(x);
                }
            }
            return listMentorId;
        }
    }
    public List<MentorSkill> getMentorBySkill1(String skill1) throws SQLException {
        List<MentorSkill> listMentorId = new ArrayList<>();
        String query = "SELECT mentorid\n"
                + "FROM MentorSkill JOIN Skill\n"
                + "ON Skill.id = MentorSkill.skillid\n"
                + "WHERE Skill.name IN (?)\n"
                + "GROUP BY mentorid\n"
                + "HAVING COUNT(DISTINCT skill.name) = 1;";
        try (java.sql.Connection conn = connection; PreparedStatement stm = conn.prepareStatement(query)) {
            stm.setString(1, skill1);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    MentorSkill x = new MentorSkill();
                    x.setMentorId(rs.getInt("mentorid"));
                    listMentorId.add(x);
                }
            }
            return listMentorId;
        }
    }

    public static void main(String[] args) throws SQLException {
        MentorSkillDBContext db = new MentorSkillDBContext();
        List<MentorSkill> listMentorId = db.getMentorBySkill1("C++");
        System.out.println(listMentorId.get(1).getMentorId());
    }
}
