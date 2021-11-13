package howmanycals.utils;

import howmanycals.domain.NutritionalIngredient;
import java.util.List;

public final class InformationMissingAnalysisUtil {
        
    public MissingPercentageRates rates(final List<NutritionalIngredient> ingredients) {
        final MissingPercentageRates rates = new MissingPercentageRates(ingredients);
        rates.analyze();
        
        return rates;
    }
        
    public static final class MissingPercentageRates {
        
        private final List<NutritionalIngredient> ingredients;
        private double fat = 0.0;
        private double sugar = 0.0;
        private double carbs = 0.0;
        private double cholesterol = 0.0;
        private double sodium = 0.0;
        private double protein = 0.0;
        
        private MissingPercentageRates(final List<NutritionalIngredient> ingredients) {
            this.ingredients = ingredients;
        }

        private void analyze() {
            final int size = this.ingredients.size();
            
            int fatCount = 0;
            int sugarCount = 0;
            int carbsCount = 0;
            int cholesterolCount = 0;
            int sodiumCount = 0;
            int proteinCount = 0;
            
            for (final NutritionalIngredient ingredient : ingredients) {
                fatCount += (ingredient.getFat() == -1d) ? 1 : 0;
                sugarCount += (ingredient.getSugar() == -1d) ? 1 : 0;
                carbsCount += (ingredient.getCarbohydrates() == -1d) ? 1 : 0;
                cholesterolCount += (ingredient.getCholesterol() == -1d) ? 1 : 0;
                sodiumCount += (ingredient.getSodium() == -1d) ? 1 : 0;
                proteinCount += (ingredient.getProtein() == -1d) ? 1 : 0;
            }
            
            this.fat = (double) ((fatCount * 100) / ((double) size));
            this.sugar = (double) ((sugarCount * 100) / ((double) size));
            this.carbs = (double) ((carbsCount * 100) / ((double) size));
            this.cholesterol = (double) ((cholesterolCount * 100) / ((double) size));
            this.sodium = (double) ((sodiumCount * 100) / ((double) size));
            this.protein = (double) ((proteinCount * 100) / ((double) size));
        }
        
        public double fat() {
            return this.fat;
        }
        
        public double sugar() {
            return this.sugar;
        }
        
        public double carbs() {
            return this.carbs;
        }
        
        public double cholesterol() {
            return this.cholesterol;
        }
        
        public double sodium() {
            return this.sodium;
        }
        
        public double protein() {
            return this.protein;
        }

        @Override
        public String toString() {
            return "MissingPercentageRates{" + "fat=" + fat + ", sugar=" + sugar + ", carbs=" + carbs + 
                    ", cholesterol=" + cholesterol + ", sodium=" + sodium + ", protein=" + protein + '}';
        }
    }
    
}
