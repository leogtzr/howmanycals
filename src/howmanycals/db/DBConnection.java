package howmanycals.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum DBConnection {
    INSTANCE
    ;
    
    private Connection connection;
    
    public Connection getConnection(final String jdbcURL, final String user, final String password) throws SQLException {
        if (this.connection == null) {
            this.connection = DriverManager.getConnection(jdbcURL, user, password);
        }
        
        return this.connection;
    }
    
}
