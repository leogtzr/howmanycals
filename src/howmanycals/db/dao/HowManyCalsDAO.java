package howmanycals.db.dao;

import howmanycals.db.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class HowManyCalsDAO {
    
    private Connection connection;

    private void validateEnvironmentVariables(final String... envVars) {
        for (final String envVar : envVars) {
            final String env = System.getenv(envVar);
            if (env == null || env.isBlank()) {
                throw new RuntimeException(String.format("'%s' env var is empty or null", envVar));
            }
        }
    }
    
    public void init() {
        validateEnvironmentVariables("HOWMANYCALS_DB_USER", "HOWMANYCALS_DB_PASSWORD", "HOWMANYCALS_DB", "HOWMANYCALS_JDBC_URL");
        
        final String dbUserName = System.getenv("HOWMANYCALS_DB_USER");
        final String dbPassword = System.getenv("HOWMANYCALS_DB_PASSWORD");
        final String dbJDBCURL = System.getenv("HOWMANYCALS_JDBC_URL");
        
        try {
            this.connection = DBConnection.INSTANCE.getConnection(dbJDBCURL, dbUserName, dbPassword);
        } catch (final SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage(), ex);
        }
        
    }
    
}
