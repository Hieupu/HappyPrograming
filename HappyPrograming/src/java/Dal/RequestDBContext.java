/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Entity.Request;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RequestDBContext extends DBContext<Request> {

    public void createRequest(String title, String status, int mentorid, double fee, int menteeid, String deadline, String schedule, String detail) throws SQLException {
        String sql = "INSERT INTO [dbo].[Request] ([title], [fee], [mentorid], [time], [menteeid], [status], [sche], [detail]) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        try (PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, title);
            stm.setDouble(2, fee);
            stm.setInt(3, mentorid);
            stm.setString(4, deadline);
            stm.setInt(5, menteeid);
            stm.setString(6, status);
            stm.setString(7, schedule);
            stm.setString(8, detail);
            stm.executeUpdate();
        }
    }

    public void outofDealine(int id) throws SQLException {
        String sql = "UPDATE [dbo].[Request] SET [status] = 'Expired' WHERE id= ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, id);
            stm.executeUpdate();
        }
    }

    /**
     * Retrieves the ID of the most recent request based on mentor ID, mentee
     * ID, and deadline.
     *
     * @param mentorid The ID of the mentor.
     * @param menteeid The ID of the mentee.
     * @param deadline The deadline of the request.
     * @return The ID of the request.
     * @throws SQLException If a database access error occurs.
     */
    public int getRequestID(int mentorid, int menteeid, String deadline) throws SQLException {
        String sql = "SELECT id FROM [dbo].[Request] WHERE [mentorid] = ? AND [menteeid] = ? AND [time] = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, mentorid);
            stm.setInt(2, menteeid);
            stm.setString(3, deadline);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }
        return 0; // Return 0 if no ID found (you may want to handle this case appropriately)
    }

    /**
     * Retrieves all requests for a given mentor ID.
     *
     * @param menteeid The ID of the mentor.
     * @return A list of requests for the given mentor ID.
     * @throws SQLException If a database access error occurs.
     */
    public ArrayList<Request> getRequestsByMenteeId(int menteeid) throws SQLException {
        ArrayList<Request> requests = new ArrayList<>();
        String sql = """
                     SELECT [id],[title],[fee],[mentorid],[time],[status],[sche], [detail]
                       FROM [dbo].[Request] WHERE [menteeid] = ?""";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, menteeid);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Request request = new Request();
                    request.setId(rs.getInt("id"));
                    request.setTitle(rs.getString("title"));
                    request.setFee(rs.getInt("fee"));
                    request.setMentorid(rs.getInt("mentorid"));
                    request.setTime(rs.getString("time"));
                    request.setStatus(rs.getString("status"));
                    request.setSche(rs.getString("sche"));
                    request.setDetail(rs.getString("detail"));
                    requests.add(request);
                }
            }
        }
        return requests;
    }

    public void updateRequest(String title, double fee, String schedule, String detail, int id) throws SQLException {
        String sql = """
                 UPDATE [dbo].[Request]
                 SET [title] = ?, [fee] = ?, [sche] = ?, [detail] = ? 
                 WHERE [id] = ?""";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, title);
            stm.setDouble(2, fee);
            stm.setString(3, schedule);
            stm.setString(4, detail);
            stm.setInt(5, id);
            stm.executeUpdate();
        }
    }

    public void deleteRequest(int requestId) throws SQLException {
        String sql = "DELETE FROM [dbo].[Request] WHERE [id] = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, requestId);
            stm.executeUpdate();
        }
    }

    public List<Request> getAllRequestByMenteeId(int menteeid) throws SQLException {
        ArrayList<Request> listRequest = new ArrayList<>();
        String query = "SELECT * FROM Request JOIN ComboSkill ON Request.id = ComboSkill.reqid WHERE  menteeid = ?";
        try (PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, menteeid); // Set the mentorid parameter
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Request s = new Request();
                    s.setId(rs.getInt("id"));
                    s.setTitle(rs.getString("title"));
                    s.setFee(rs.getInt("fee"));
                    s.setMentorid(rs.getInt("mentorid"));
                    s.setTime(rs.getString("time")); 
                    s.setMenteeid(rs.getInt("menteeid"));
                    s.setStatus(rs.getString("status"));
                    s.setSche(rs.getString("sche"));
                    s.setSkillid(rs.getString("skillid"));
                    listRequest.add(s);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching requests from the database", e);
        }
        return listRequest;
    }
    
    public List<Request> getAllRequestByMentorId(int mentorid) throws SQLException {
        ArrayList<Request> listRequest = new ArrayList<>();
        String query = "SELECT * FROM Request JOIN ComboSkill ON Request.id = ComboSkill.reqid WHERE status = 'open' AND mentorid = ? ";
        try (PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, mentorid); 
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Request s = new Request();
                    s.setId(rs.getInt("id"));
                    s.setTitle(rs.getString("title"));
                    s.setFee(rs.getInt("fee"));
                    s.setMentorid(rs.getInt("mentorid"));
                    s.setTime(rs.getString("time")); 
                    s.setMenteeid(rs.getInt("menteeid"));
                    s.setStatus(rs.getString("status"));
                    s.setSche(rs.getString("sche"));
                    s.setSkillid(rs.getString("skillid"));
                    listRequest.add(s);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching requests from the database", e);
        }
        return listRequest;
    }

    public void rejectRequestById(int id) throws SQLException {
        String query = "UPDATE Request SET status = 'Rejected' WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, id);
            int rowsAffected = stm.executeUpdate();
            System.out.println("Number of rows updated: " + rowsAffected);
        }
    }

    public void AcceptRequestById(int id) throws SQLException {
        String query = "UPDATE Request SET status = 'Accepted' WHERE id = ?";

        try (PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, id);
            int rowsAffected = stm.executeUpdate();
            System.out.println("Number of rows updated: " + rowsAffected);
        }
    }

    public int getMentoridByReqid(int reqid) throws SQLException {
        String query = "SELECT mentorid FROM request WHERE request.id = ?";
        try (PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, reqid);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("mentorid");
                } else {
                    throw new SQLException("No mentor found with the given request ID");
                }
            }
        }
    }
}
