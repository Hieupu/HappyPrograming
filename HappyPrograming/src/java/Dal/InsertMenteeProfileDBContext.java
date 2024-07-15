/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Entity.Mentee;
import java.lang.reflect.InaccessibleObjectException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author MSI
 */
public class InsertMenteeProfileDBContext extends DBContext<Mentee> {

    public void insertProfileMentee(String ava, int accid) throws SQLException {
        String query = "INSERT INTO Mentee VALUES(?, ?);";
        try (PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setString(1, ava);
            stm.setInt(2, accid);
            stm.executeUpdate();
        }
    }

    public int getIdOfNewMentor() throws SQLException {
        int id = 0;
        String query = "SELECT MAX(id) AS max_id FROM account";

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

    public Mentee getInforMentee(int accid) throws SQLException {
        String query = "SELECT ava, accid FROM Mentee WHERE accid = ?";
        try (java.sql.Connection conn = connection; PreparedStatement stm = conn.prepareStatement(query)) {
            stm.setInt(1, accid);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return new Mentee(
                            rs.getString("ava"),
                            rs.getInt("accid"));
                }
            }
            return null;
        }
    }

    public static void main(String[] args) throws SQLException {
        InsertMenteeProfileDBContext x = new InsertMenteeProfileDBContext();
        Mentee n = x.getInforMentee(1012);
        System.out.println(n.getAva());

    }

}
