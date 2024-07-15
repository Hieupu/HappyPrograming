package Dal;

import Entity.Account;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object for Login functionality.
 */
public class LoginDBContext extends DBContext<Account> {

    private static final Logger LOGGER = Logger.getLogger(LoginDBContext.class.getName());
    
        public Account getAccountbyid(int id) throws SQLException{
            Account a = new Account();
        String sql = "Select [fullname] From Account Where id = ?";
         try (Connection conn = connection; PreparedStatement stm = conn.prepareStatement(sql)){
             stm.setInt(1, id);
             ResultSet rs = stm.executeQuery();
             while(rs.next()){  
                 a.setFullname(rs.getString("fullname"));
             }
         }
         return a;
    }

    /**
     * Checks login credentials against the database.
     *
     * @param user the username
     * @param password the password
     * @return an Account object if credentials are valid, otherwise null
     * @throws SQLException if a database access error occurs
     */
    public Account checkLogin(String user, String password) throws SQLException {
        String sql = "SELECT [user], [pass], [id], [role], [fullname] FROM [Account] WHERE [user] = ? AND [pass] =  ?";

        try (Connection conn = connection; PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, user);
            stm.setString(2, password);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    Account acc = new Account();
                    acc.setUser(rs.getString("user"));
                    acc.setPass(rs.getString("pass"));
                    acc.setId(rs.getInt("id"));
                    acc.setRole(rs.getString("role"));
                    acc.setFullname(rs.getString("fullname"));
                    return acc;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database access error during checkLogin", e);
            throw e;
        }
        return null;
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public Account searchByID(int id) throws SQLException {
        String sql = "SELECT [phone], [address], [fullname], [dob], [gender], [mail] FROM [Account] WHERE [id] = ?";

        try (Connection conn = connection; PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setInt(1, id);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    Account acc = new Account();
                    acc.setPhone(rs.getString("phone"));
                    acc.setFullname(rs.getString("fullname"));
                    acc.setAddress(rs.getString("address"));
                    acc.setDob(rs.getDate("dob"));
                    acc.setGender(rs.getInt("gender"));
                    acc.setEmail(rs.getString("mail"));
                    return acc;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database access error during checkLogin", e);
            throw e;
        }
        return null;
    }

    /**
     *
     * @param email
     * @param fullname
     * @param DoB
     * @param gender
     * @param phone
     * @param address
     * @param id
     * @throws SQLException
     */
    public void updateProfile(String email, String fullname, Date DoB, int gender, String phone, String address, int id) throws SQLException {
        String sql = "UPDATE [dbo].[Account] SET  [mail] = ?,[phone] = ?,[address] = ?,[gender] = ?,[fullname] = ? ,[dob] = ? WHERE id = ?";
        try (Connection conn = connection; PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, email);
            stm.setString(2, phone);
            stm.setString(3, address);
            stm.setInt(4, gender);
            stm.setString(5, fullname);
            stm.setDate(6, DoB);
            stm.setInt(7, id);
            stm.executeUpdate();
        }
    }

    /**
     *
     * @param user
     * @param pass
     * @param role
     * @param email
     * @param name
     * @param DoB
     * @param gender
     * @param phone
     * @param address
     * @throws SQLException
     */
    public void registerUser(String user, String pass, String role, String email, String name, Date DoB, int gender, String phone, String address) throws SQLException {
        String sql = """
                     INSERT INTO [dbo].[Account]([user],[pass],[role] ,[mail] ,[phone],[address] ,[gender],[fullname],[dob])
                      VALUES (?,?,?,?,?,?,?,?,?)""";

        try (Connection conn = connection; PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, user);
            stm.setString(2, pass);
            stm.setString(3, role);
            stm.setString(4, email);
            stm.setString(8, name);
            stm.setDate(9, DoB);
            stm.setInt(7, gender);
            stm.setString(5, phone);
            stm.setString(6, address);
            stm.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database access error during registerUser", e);
            throw e;
        }
    }

    /**
     *
     * @param username
     * @return
     * @throws SQLException
     */
    public boolean isUsernameTaken(String username) throws SQLException {
        String sql = "SELECT COUNT(*) FROM [Account] WHERE [user] = ?";

        try (Connection conn = connection; PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database access error during isUsernameTaken", e);
            throw e;
        }
        return false;
    }

    /**
     *
     * @param id
     * @param newpass
     * @throws SQLException
     */
    public void changePass(int id, String newpass) throws SQLException {
        String sql = "UPDATE [dbo].[Account] SET [pass] = ? WHERE id = ?";
        
        try (Connection conn = connection; PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, newpass);
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database access error during changePass", e);
            throw e;
        }
    }

    /**
     *
     * @param pass
     * @param id
     * @return
     * @throws SQLException
     */
    public boolean checkPass(String pass, int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM [Account] WHERE [id] = ? AND [pass] = ?";

        try (Connection conn = connection; PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setString(2, pass);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database access error during isUsernameTaken", e);
            throw e;
        }
        return false;
    }
}
