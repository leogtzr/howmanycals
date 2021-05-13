package howmanycals.utils;

import howmanycals.domain.MealNutritionInformation;
import howmanycals.domain.NutritionalIngredient;
import java.util.List;
import java.util.Optional;

public final class SummaryUtil {
    
    private SummaryUtil() {}
    
    public static Optional<MealNutritionInformation> calculateSummaryFromIngredients(
            final List<NutritionalIngredient> nutIngredients) {
        if (nutIngredients.isEmpty()) {
            return Optional.empty();
        }
        
        double calories = 0d;
        double protein = 0d;
        double sugar = 0d;
        double carbs = 0d;
        double fat = 0d;
        double cholesterol = 0d;
        
        for (final NutritionalIngredient ingredient : nutIngredients) {
            calories += (ingredient.getCalories() == -1d) ? 0d : ingredient.getCalories();
            protein += (ingredient.getProtein() == -1d) ? 0d : ingredient.getProtein();
            sugar += (ingredient.getSugar() == -1d) ? 0d : ingredient.getSugar();
            carbs += (ingredient.getCarbohydrates() == -1d) ? 0d : ingredient.getCarbohydrates();
            fat += (ingredient.getFat() == -1d) ? 0d : ingredient.getFat();
            cholesterol += (ingredient.getCholesterol() == -1d) ? 0d : ingredient.getCholesterol();
        }
        
        final MealNutritionInformation mealNutritionInformation = new MealNutritionInformation();
        mealNutritionInformation.setCalories(calories);
        mealNutritionInformation.setProtein(protein);
        mealNutritionInformation.setSugar(sugar);
        mealNutritionInformation.setCarbohydrates(carbs);
        mealNutritionInformation.setFat(fat);
        mealNutritionInformation.setCholesterol(cholesterol);
        
        return Optional.of(mealNutritionInformation);
    }
    
}
