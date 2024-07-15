package Dal;

import Dal.DBContext;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class TestDBConnection {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        new TestDBConnection().testConnection();
    }

    /**
     *
     */
    public void testConnection() {
        DBContext dbContext = new DBContext() {};
        Connection connection = dbContext.getConnection();

        if (connection != null) {
            System.out.println("Database connected successfully!");
        } else {
            System.out.println("Failed to connect to the database.");
        }

        // Clean up the connection if it was successful
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(TestDBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
