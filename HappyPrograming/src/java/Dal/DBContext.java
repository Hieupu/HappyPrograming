package Dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Base class for database context.
 * @param <T>
 */
public abstract class DBContext<T> {

    /**
     *
     */
    protected Connection connection;

    /**
     *
     */
    public DBContext() {
        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=HappyPrograming;encrypt=true;trustServerCertificate=true;";
            String user = "sa";
            String pass = "1234";
            Class.forName("com.micro"
                    + "soft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
            Logger.getLogger(DBContext.class.getName()).log(Level.INFO, "Connection established successfully.");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, "Connection failed.", ex);
        }
    }

    /**
     *
     * @return
     */
    public Connection getConnection() {
        return connection;
    }

}
