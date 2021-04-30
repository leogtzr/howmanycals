package howmanycals.domain;

public class NutritionalIngredient {
    
    private Integer id;
    private String name;
    private int grams;
    private double calories = -1d;
    private double fat = -1d;
    private double sugar = -1d;
    private double carbohydrates = -1d;
    private double protein = -1d;
    private double cholesterol = -1d;
    private double sodium = -1d;
    private String category = "";
    
    public NutritionalIngredient() {}
    
    private NutritionalIngredient(final FormBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.grams = builder.grams;
        this.calories = builder.calories;
        this.fat = builder.fat;
        this.sugar = builder.sugar;
        this.carbohydrates = builder.carbohydrates;
        this.protein = builder.protein;
        this.cholesterol = builder.cholesterol;
        this.sodium = builder.sodium;
        this.category = builder.category;
    }
    
    public static class FormBuilder {
        private Integer id;
        private final String name;
        private Integer grams;
        private double calories = -1d;
        private double fat = -1d;
        private double sugar = -1d;
        private double carbohydrates = -1d;
        private double protein = -1d;
        private double cholesterol = -1d;
        private double sodium = -1d;
        private String category = "";

        public FormBuilder(final String name, final String grams) {
            if (name == null || name.isBlank()) {
                throw new NumberFormatException("Please enter a name");
            }
            this.name = name;
            this.grams = Integer.parseInt(grams);
        }
        
        public FormBuilder id(final String id) {
            this.id = Integer.parseInt(id);
            return this;
        }
        
        public FormBuilder calories(final String calories) {
            this.calories = Double.parseDouble(calories);
            return this;
        }
        
        public FormBuilder fat(final String fat) {
            if (fat == null || fat.isBlank()) {
                this.fat = -1d;
            } else {
                this.fat = Double.parseDouble(fat);
            }
            return this;
        }
        
        public FormBuilder sugar(final String sugar) {
            if (sugar == null || sugar.isBlank()) {
                this.sugar = -1d;
            } else {
                this.sugar = Double.parseDouble(sugar);
            }
            return this;
        }
        
        public FormBuilder carbohydrates(final String carbohydrates) {
            if (carbohydrates == null || carbohydrates.isBlank()) {
                this.carbohydrates = -1d;
            } else {
                this.carbohydrates = Double.parseDouble(carbohydrates);
            }
            return this;
        }
        
        public FormBuilder protein(final String protein) {
            if (protein == null || protein.isBlank()) {
                this.protein = -1d;
            } else {
                this.protein = Double.parseDouble(protein);
            }
            return this;
        }
        
        public FormBuilder cholesterol(final String cholesterol) {
            if (cholesterol == null || cholesterol.isBlank()) {
                this.cholesterol = -1d;
            } else {
                this.cholesterol = Double.parseDouble(cholesterol);
            }
            return this;
        }
        
        public FormBuilder sodium(final String sodium) {
            if (sodium == null || sodium.isBlank()) {
                this.sodium = -1d;
            } else {
                this.sodium = Double.parseDouble(sodium);
            }
            return this;
        }
        
        public FormBuilder category(final String category) {
            if (category == null || category.isBlank()) {
                throw new NumberFormatException("Please enter a valid category");
            }
            this.category = category;
            return this;
        }
        
        public NutritionalIngredient build() {
            return new NutritionalIngredient(this);
        }
        
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
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

    public void setFat(double fat) {
        this.fat = fat;
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

    public int getGrams() {
        return grams;
    }

    public void setGrams(final int grams) {
        this.grams = grams;
    }

    public double getSugar() {
        return sugar;
    }

    public void setSugar(final double sugar) {
        this.sugar = sugar;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }
    
}