package howmanycals.domain;

public class MealNutritionInformation {
    
    private double grams;
    private double calories = -1d;
    private double fat = -1d;
    private double sugar = -1d;
    private double carbohydrates = -1d;
    private double protein = -1d;
    private double cholesterol = -1d;
    private double sodium = -1d;

    public double getGrams() {
        return grams;
    }

    public void setGrams(final double grams) {
        this.grams = grams;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(final double calories) {
        this.calories = calories;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(final double fat) {
        this.fat = fat;
    }

    public double getSugar() {
        return sugar;
    }

    public void setSugar(final double sugar) {
        this.sugar = sugar;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(final double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(final double protein) {
        this.protein = protein;
    }

    public double getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(final double cholesterol) {
        this.cholesterol = cholesterol;
    }

    public double getSodium() {
        return sodium;
    }

    public void setSodium(final double sodium) {
        this.sodium = sodium;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.grams) ^ (Double.doubleToLongBits(this.grams) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.calories) ^ (Double.doubleToLongBits(this.calories) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.fat) ^ (Double.doubleToLongBits(this.fat) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.sugar) ^ (Double.doubleToLongBits(this.sugar) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.carbohydrates) ^ (Double.doubleToLongBits(this.carbohydrates) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.protein) ^ (Double.doubleToLongBits(this.protein) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.cholesterol) ^ (Double.doubleToLongBits(this.cholesterol) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.sodium) ^ (Double.doubleToLongBits(this.sodium) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MealNutritionInformation other = (MealNutritionInformation) obj;
        if (this.grams != other.grams) {
            return false;
        }
        if (Double.doubleToLongBits(this.calories) != Double.doubleToLongBits(other.calories)) {
            return false;
        }
        if (Double.doubleToLongBits(this.fat) != Double.doubleToLongBits(other.fat)) {
            return false;
        }
        if (Double.doubleToLongBits(this.sugar) != Double.doubleToLongBits(other.sugar)) {
            return false;
        }
        if (Double.doubleToLongBits(this.carbohydrates) != Double.doubleToLongBits(other.carbohydrates)) {
            return false;
        }
        if (Double.doubleToLongBits(this.protein) != Double.doubleToLongBits(other.protein)) {
            return false;
        }
        if (Double.doubleToLongBits(this.cholesterol) != Double.doubleToLongBits(other.cholesterol)) {
            return false;
        }
        if (Double.doubleToLongBits(this.sodium) != Double.doubleToLongBits(other.sodium)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MealNutritionInformation{" + "grams=" + grams + ", calories=" + calories + ", fat=" + fat + ", sugar=" + 
                sugar + ", carbohydrates=" + carbohydrates + ", protein=" + protein + ", cholesterol=" + 
                cholesterol + ", sodium=" + sodium + '}';
    }

}
