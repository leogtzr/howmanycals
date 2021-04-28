package howmanycals.db.dao;

import howmanycals.db.DBConnection;
import howmanycals.domain.NutritionalIngredient;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    
    public Optional<NutritionalIngredient> findById(final int id) throws SQLException {
        final String query = "SELECT * FROM nutrition_ingredient WHERE id = ?";

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (final ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    final NutritionalIngredient nutritionalIngredient = extractIngredient(rs);
                    return Optional.of(nutritionalIngredient);
                }
            }
        }

        return Optional.empty();
    }
    
    public List<String> categories() throws SQLException {
        final List<String> categories = new ArrayList<>();
        final String query = "SELECT DISTINCT(name) FROM category";

        try (final ResultSet rs = connection.createStatement().executeQuery(query)) {
            while (rs.next()) {
                final String category = rs.getString("name");
                categories.add(category);
            }
        }
        
        return categories;
    }
    
    private NutritionalIngredient extractIngredient(final ResultSet rs) throws SQLException {
        final NutritionalIngredient ingredient = new NutritionalIngredient();
        
        ingredient.setId(rs.getInt("id"));
        ingredient.setGrams(rs.getInt("grams"));
        ingredient.setCalories(rs.getFloat("calories"));
        ingredient.setFat(rs.getFloat("fat"));
        ingredient.setSugar(rs.getFloat("sugar"));
        ingredient.setCarbohydrates(rs.getFloat("carbohydrates"));
        ingredient.setProtein(rs.getFloat("protein"));
        ingredient.setCholesterol(rs.getFloat("cholesterol"));
        ingredient.setSodium(rs.getFloat("sodium"));
        ingredient.setCategory(rs.getString("category"));
        
        return ingredient;
    }
    
}
