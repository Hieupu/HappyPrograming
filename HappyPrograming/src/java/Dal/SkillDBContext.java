package Dal;

import Entity.Skill;
import java.sql.*;
import java.util.ArrayList;

/**
 * Data access class for Skill entities.
 */
public class SkillDBContext extends DBContext<Skill> {
    public ArrayList<Skill> listasc() throws SQLException {
        ArrayList<Skill> list = new ArrayList<>();
        String sql = "SELECT [id],[name],[status] FROM [dbo].[Skill] ORDER BY [name] ASC";

        try (PreparedStatement stm = connection.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                Skill s = new Skill();
                s.setId(rs.getString("id").trim());
                s.setName(rs.getString("name"));
                s.setStatus(rs.getInt("status"));
                list.add(s);
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching skills from the database", e);
        }

        return list;
    }
    
    public Skill getSkillById(String id) throws SQLException {
        String sql = "SELECT [name], [status] FROM [dbo].[Skill] WHERE [id] = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                int status = rs.getInt("status");
                return new Skill(id, name, status);
            } else {
                throw new SQLException("Skill not found with ID: " + id);
            }
        }
    }

    /**
     *
     * @param id
     * @throws SQLException
     */
    public void deleteSkill(String id) throws SQLException {
        String sql = "DELETE FROM [dbo].[Skill] WHERE id = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, id);
        stm.executeUpdate();
    }
    
    public void insertSkillRequest(String skillId, int reqid) throws SQLException {
        String query = "INSERT INTO ComboSkill VALUES(?, ?)";
        try (PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setString(1, skillId.trim());
            stm.setInt(2, reqid);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public String getSkillByName(String name) throws SQLException {
        String skillid = null;
        String sql = "SELECT [id] FROM [dbo].[Skill] WHERE [name] = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                skillid = rs.getString("id").trim();
            }
        }
        return skillid;
    }

    public ArrayList<Skill> listSkillinRequest(int id) throws SQLException {
        ArrayList<Skill> list = new ArrayList<>();
        String sql = "SELECT c.[skillid] FROM [dbo].[ComboSkill] c WHERE c.[reqid] = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql);) {
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Skill s = new Skill();
                s.setId(rs.getString("skillid").trim());
                list.add(s);
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching skills from the database", e);
        }

        return list;
    }

    public void deletetoUpdate(int id) throws SQLException {
        String sql = "DELETE FROM [dbo].[ComboSkill] WHERE [reqid] = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql);) {
            stm.setInt(1, id);
            stm.executeUpdate();
        }
    }
    
    /**
     * Retrieves a list of all skills from the database.
     *
     * @return a list of Skill objects
     * @throws SQLException if a database access error occurs
     */
    public ArrayList<Skill> list() throws SQLException {
        ArrayList<Skill> list = new ArrayList<>();
        String sql = "SELECT id, [name], [status] FROM Skill";

        try (PreparedStatement stm = connection.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                Skill s = new Skill();
                s.setId(rs.getString("id").trim());
                s.setName(rs.getString("name"));
                s.setStatus(rs.getInt("status"));
                list.add(s);
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching skills from the database", e);
        }

        return list;
    }
    
    public ArrayList<Skill> listActive() throws SQLException {
        ArrayList<Skill> list = new ArrayList<>();
        String sql = "SELECT id, [name] FROM Skill where [status] = '1'";

        try (PreparedStatement stm = connection.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                Skill s = new Skill();
                s.setId(rs.getString("id").trim());
                s.setName(rs.getString("name"));
                list.add(s);
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching skills from the database", e);
        }

        return list;
    }
    /**
     *
     * @param id
     * @param name
     * @param status
     * @throws SQLException
     */
    public void createSkill(String id, String name, int status) throws SQLException {
        String sql = "INSERT INTO [dbo].[Skill] VALUES (?,?,?)";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, id);
        stm.setString(2, name);
        stm.setInt(3, status);
        stm.executeUpdate();
    }
    
    /**
     *
     * @param id
     * @param name
     * @param status
     * @throws SQLException
     */
    public void updateSkill(String id, String name, int status) throws SQLException {
        String sql = "UPDATE [dbo].[Skill] SET [name] = ?,[status] = ? WHERE id = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(3, id);
        stm.setString(1, name);
        stm.setInt(2, status);
        stm.executeUpdate();
    }
}