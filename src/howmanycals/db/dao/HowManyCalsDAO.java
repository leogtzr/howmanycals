package howmanycals.db.dao;

import howmanycals.db.DBConnection;
import howmanycals.domain.Category;
import howmanycals.domain.Meal;
import howmanycals.domain.Note;
import howmanycals.domain.NutritionalIngredient;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HowManyCalsDAO {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(HowManyCalsDAO.class.getSimpleName());
    
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

        try (final PreparedStatement stmt = this.connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (final ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    final NutritionalIngredient nutritionalIngredient = extractIngredient(rs);
                    return Optional.of(nutritionalIngredient);
                }
            }
        }

        return Optional.empty();
    }
    
    public List<Category> categories() throws SQLException {
        final List<Category> categories = new ArrayList<>();
        final String query = "SELECT DISTINCT(name), id FROM category ORDER BY id";

        try (final ResultSet rs = connection.createStatement().executeQuery(query)) {
            while (rs.next()) {
                categories.add(extractCategory(rs));
            }
        }
        
        return categories;
    }
    
    public List<NutritionalIngredient> ingredients() throws SQLException {
        final List<NutritionalIngredient> ingredients = new ArrayList<>();
        final String query = "SELECT nut.*, cat.id AS cat_id, cat.name AS cat_name\n" +
            "FROM nutrition_ingredient nut\n" +
            "INNER JOIN category cat\n" +
            "ON cat.id = nut.id_category";

        try (final ResultSet rs = this.connection.createStatement().executeQuery(query)) {
            while (rs.next()) {
                ingredients.add(extractIngredient(rs));
            }
        }
        
        return ingredients;
    }
    
    private Category extractCategory(final ResultSet rs) throws SQLException {
        return new Category(rs.getInt("id"), rs.getString("name"));
    }
    
    private void setCommonIngredientFields(final NutritionalIngredient ingredient, final ResultSet rs) throws SQLException {
        ingredient.setName(rs.getString("name"));
        ingredient.setId(rs.getInt("id"));
        ingredient.setGrams(rs.getInt("grams"));
        ingredient.setCalories(rs.getFloat("calories"));
        ingredient.setFat(rs.getFloat("fat"));
        ingredient.setSugar(rs.getFloat("sugar"));
        ingredient.setCarbohydrates(rs.getFloat("carbohydrates"));
        ingredient.setProtein(rs.getFloat("protein"));
        ingredient.setCholesterol(rs.getFloat("cholesterol"));
        ingredient.setSodium(rs.getFloat("sodium"));
    }
    
    private NutritionalIngredient extractCreatedIngredient(final ResultSet rs) throws SQLException {
        final NutritionalIngredient ingredient = new NutritionalIngredient();
        
        this.setCommonIngredientFields(ingredient, rs);
        
        final int catID = rs.getInt("id_category");
        final Category category = new Category();
        category.setId(rs.getInt("id_category"));
        
        this.findCategoryByCategoryID(catID).ifPresent(cat -> {
            category.setName(cat.getName());
        });
                
        ingredient.setCategory(category);
        
        return ingredient;
    }
    
    private NutritionalIngredient extractIngredient(final ResultSet rs) throws SQLException {
        final NutritionalIngredient ingredient = new NutritionalIngredient();
        
        this.setCommonIngredientFields(ingredient, rs);
        
        final Category category = new Category();
        category.setId(rs.getInt("cat_id"));
        category.setName(rs.getString("cat_name"));
        
        ingredient.setCategory(category);
        
        return ingredient;
    }
    
    private Meal extractMeal(final ResultSet rs) throws SQLException {
        final Meal meal = new Meal();
        
        meal.setId(rs.getInt("id"));
        meal.setName(rs.getString("name"));
        meal.setName(rs.getString("notes"));
        meal.setCreationDate(rs.getTimestamp("creation_date").toLocalDateTime());
        
        return meal;
    }
    
    public Optional<NutritionalIngredient> createNutritionIngredient(final NutritionalIngredient ingredient) throws SQLException {
        final String query = "INSERT INTO nutrition_ingredient" +
                "(name, grams, calories, fat, sugar, carbohydrates, protein, cholesterol, sodium, id_category, notes) " + 
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (final PreparedStatement stmt = this.connection.prepareStatement(query, RETURN_GENERATED_KEYS)) {
            stmt.setString(1, ingredient.getName());
            stmt.setInt(2, ingredient.getGrams());
            stmt.setDouble(3, ingredient.getCalories());
            stmt.setDouble(4, ingredient.getFat());
            stmt.setDouble(5, ingredient.getSugar());
            stmt.setDouble(6, ingredient.getCarbohydrates());
            stmt.setDouble(7, ingredient.getProtein());
            stmt.setDouble(8, ingredient.getCholesterol());
            stmt.setDouble(9, ingredient.getSodium());
            stmt.setInt(10, ingredient.getCategory().getId());
            stmt.setString(11, ingredient.getNotes());
            
            LOGGER.debug(stmt.toString());
            
            final int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Data insert failed, no rows affected.");
            }

            try (final ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return Optional.of(extractCreatedIngredient(rs));
                } else {
                    throw new SQLException("Data insert failed, no ID obtained.");
                }
            }
        }        
    }
    
    private Optional<Meal> createMeal(final String name, final String notes) throws SQLException {
        final String query = "INSERT INTO meal (name, notes) VALUES(?, ?)";
        
        try (final PreparedStatement stmt = this.connection.prepareStatement(query, RETURN_GENERATED_KEYS)) {
            stmt.setString(1, name);
            stmt.setString(2, notes);
            
            LOGGER.debug(stmt.toString());
            
            final int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Data insert failed, no rows affected.");
            }

            try (final ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    final Meal createdMeal = extractMeal(rs);
                    return Optional.of(createdMeal);
                } else {
                    throw new SQLException("unable to create meal");
                }
            }
        }        
    }
    
    private Optional<Integer> insertIngredient(final Integer mealID, final Integer nutritionIngredientID) throws SQLException {
        final String query = "INSERT INTO ingredients (id_meal, id_nutrition_ingredient) VALUES(?, ?)";
        
        try (final PreparedStatement stmt = this.connection.prepareStatement(query, RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, mealID);
            stmt.setInt(2, nutritionIngredientID);
            
            LOGGER.debug(stmt.toString());
            
            final int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Data insert failed, no rows affected.");
            }

            try (final ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return Optional.of(rs.getInt("id"));
                } else {
                    throw new SQLException("unable to create meal");
                }
            }
        }
    }
    
    public Optional<Meal> saveMeal(final String mealName, final String notes, final int[] indexes) throws SQLException {
        if (indexes == null || indexes.length == 0) {
            throw new SQLException("indexes for ingredients are missing");
        }
        
        final Optional<Meal> insertedMeal = this.createMeal(mealName, notes);
        if (insertedMeal.isPresent()) {
            final Meal mealDB = insertedMeal.get();
            boolean ok = true;
            for (int idx = 0; idx < indexes.length; idx++) {
                final Optional<Integer> insertedNutritionIngredient = insertIngredient(mealDB.getId(), indexes[idx]);
                if (insertedNutritionIngredient.isEmpty()) {
                    ok = false;
                    break;
                }
            }
            
            return ok ? insertedMeal : Optional.empty();
        }
        
        return Optional.empty();
    }
    
    public List<Meal> containsByName(final String name) throws SQLException {
        final String query = "SELECT * FROM meal WHERE LOWER(name) LIKE ?";
        final List<Meal> meals = new ArrayList<>();

        try (final PreparedStatement stmt = this.connection.prepareStatement(query)) {
            stmt.setString(1, String.format("%%%s%%", name));
            
            try (final ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    meals.add(extractMeal(rs));
                }
            }
        }

        return meals;
    }
    
    public List<Meal> meals() throws SQLException {
        final String query = "SELECT * FROM meal";
        final List<Meal> meals = new ArrayList<>();
        
        try (final ResultSet rs = connection.createStatement().executeQuery(query)) {
            while (rs.next()) {
                meals.add(extractMeal(rs));
            }
        }

        return meals;
    }
    
    public Optional<Meal> findMealByName(final String name) throws SQLException {
        final String query = "SELECT * FROM meal WHERE LOWER(name) = ?";

        try (final PreparedStatement preparedStatement = this.connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            
            try (final ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    final Meal meal = extractMeal(rs);
                    return Optional.of(meal);
                }
            }
        }

        return Optional.empty();
    }
    
    public List<NutritionalIngredient> findIngredientsByMealID(final int id) throws SQLException {
        final String query = "SELECT \n" +
"    nut.*, cat.id AS cat_id, cat.name AS cat_name\n" +
"    FROM meal m\n" +
"    INNER JOIN ingredients ing \n" +
"        ON m.id = ing.id_meal\n" +
"    INNER JOIN nutrition_ingredient nut\n" +
"        ON nut.id = ing.id_nutrition_ingredient\n" +
"    INNER JOIN category cat\n" +
"        ON cat.id = nut.id_category\n" +
"    WHERE m.id = ?";
        
        final List<NutritionalIngredient> ingredients = new ArrayList<>();

        try (final PreparedStatement stmt = this.connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            
            try (final ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    final NutritionalIngredient ingredient = extractIngredient(rs);
                    ingredients.add(ingredient);
                }
            }
        }
        
        return ingredients;
    }
    
    public Optional<Category> findCategoryByName(final String categoryName) throws SQLException {
        final String query = "SELECT * FROM category WHERE name = ?";
        
        final List<NutritionalIngredient> ingredients = new ArrayList<>();

        try (final PreparedStatement stmt = this.connection.prepareStatement(query)) {
            stmt.setString(1, categoryName);
            
            try (final ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    final Category category = extractCategory(rs);
                    return Optional.of(category);
                }
            }
        }
        
        return Optional.empty();
    }
    
    public Optional<Category> findCategoryByCategoryID(final Integer id) throws SQLException {
        final String query = "SELECT * FROM category WHERE id = ?";
        
        final List<NutritionalIngredient> ingredients = new ArrayList<>();

        try (final PreparedStatement stmt = this.connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            
            try (final ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    final Category category = extractCategory(rs);
                    return Optional.of(category);
                }
            }
        }
        
        return Optional.empty();
    }
    
    public List<NutritionalIngredient> findIngredientsByCategoryID(final int id) throws SQLException {
        final String query = "SELECT nut.*, cat.id AS cat_id, cat.name AS cat_name\n"
                + "FROM nutrition_ingredient nut\n"
                + "INNER JOIN category cat\n"
                + "ON cat.id = nut.id_category\n"
                + "WHERE nut.id_category = ?";
        
        final List<NutritionalIngredient> ingredients = new ArrayList<>();

        try (final PreparedStatement stmt = this.connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            
            try (final ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    final NutritionalIngredient ingredient = extractIngredient(rs);
                    
                    final Category category = new Category();
                    category.setId(rs.getInt("cat_id"));
                    category.setName(rs.getString("cat_name"));
                    ingredient.setCategory(category);
                    
                    ingredients.add(ingredient);
                }
            }
        }
        
        return ingredients;
    }
    
    public List<Note> notes() throws SQLException {
        final String query = "SELECT * FROM note ORDER BY creation_date";
        final List<Note> notes = new ArrayList<>();
        
        try (final ResultSet rs = connection.createStatement().executeQuery(query)) {
            while (rs.next()) {
                notes.add(extractNote(rs));
            }
        }

        return notes;
    }
    
    public Optional<Note> findNoteById(final int id) throws SQLException {
        final String query = "SELECT * FROM note WHERE id = ?";

        try (final PreparedStatement stmt = this.connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (final ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    final Note note = extractNote(rs);
                    return Optional.of(note);
                }
            }
        }

        return Optional.empty();
    }

    private Note extractNote(final ResultSet rs) throws SQLException {
        final Note note = new Note();
        note.setId(rs.getInt("id"));
        note.setNote(rs.getString("note"));
        note.setCreationDate(rs.getTimestamp("creation_date").toLocalDateTime());
        
        return note;
    }
    
    public Optional<Note> createNote(final String noteText) throws SQLException {
        final String QUERY = "INSERT INTO note (note) VALUES(?)";
        
        try (final PreparedStatement stmt = this.connection.prepareStatement(QUERY, RETURN_GENERATED_KEYS)) {
            stmt.setString(1, noteText);
            
            LOGGER.debug(stmt.toString());
            
            final int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Data insert failed, no rows affected.");
            }

            try (final ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    final Note createdNote = extractNote(rs);
                    return Optional.of(createdNote);
                } else {
                    throw new SQLException("unable to create note");
                }
            }
        }   
    }
    
    public void tearDown() throws SQLException {
        if (this.connection != null) {
            this.connection.close();
        }
    }
    
}
