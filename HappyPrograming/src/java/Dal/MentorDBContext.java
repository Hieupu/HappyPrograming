/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Entity.Mentor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author MSI
 */
public class MentorDBContext extends DBContext<Mentor> {

    public void insertCvMentor1(String ava, String job, String intro, String achivement, int accId, String status) throws SQLException {
        String query = "INSERT INTO Mentor VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setString(1, ava);
            stm.setString(2, job);
            stm.setString(3, intro);
            stm.setString(4, achivement);
            stm.setInt(5, accId);
            stm.setString(6, status);
            stm.executeUpdate();
        }
    }

    public int getIdOfNewMentor() throws SQLException {
        int id = 0;
        String query = "SELECT MAX(id) AS max_id FROM Mentor";

        try (PreparedStatement stm = connection.prepareStatement(query)) {
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                id = rs.getInt("max_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return id;
    }

    public void updateMentor(String ava, String job, String intro, String achivement, int accid) throws SQLException {
        String query = "UPDATE Mentor \n"
                + "SET ava =?,\n"
                + "job = ?,\n"
                + "intro= ?,\n"
                + "achivement= ?\n"
                + "Where accid = ?";
        try (java.sql.Connection conn = connection; PreparedStatement stm = conn.prepareStatement(query)) {
            stm.setString(1, ava);
            stm.setString(2, job);
            stm.setString(3, intro);
            stm.setString(4,achivement);
            stm.setInt(5, accid);
            stm.executeUpdate();
        }
    }

    public static void main(String[] args) throws SQLException {
        MentorDBContext db = new MentorDBContext();
       db.updateMentor("a", "a", "a", "a", 6);

    }
}
