package howmanycals.domain;

import static java.lang.Double.parseDouble;

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
    private Category category;
    private String notes = "";
    private String referenceLink = "";
    
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
        this.notes = builder.notes;
        this.referenceLink = builder.referenceLink;
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
        private Category category;
        private String notes = "";
        private String referenceLink = "";

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
            this.calories = parseDouble(calories);
            return this;
        }
        
        public FormBuilder fat(final String fat) {
            this.fat = (fat == null || fat.isBlank()) ? -1d : parseDouble(fat);
            return this;
        }
        
        public FormBuilder sugar(final String sugar) {
            this.sugar = (sugar == null || sugar.isBlank()) ? -1d : parseDouble(sugar);
            return this;
        }
        
        public FormBuilder carbohydrates(final String carbohydrates) {
            this.carbohydrates = (carbohydrates == null || carbohydrates.isBlank()) ? -1d : parseDouble(carbohydrates);
            return this;
        }
        
        public FormBuilder protein(final String protein) {
            this.protein = (protein == null || protein.isBlank()) ? -1d : parseDouble(protein);
            return this;
        }
        
        public FormBuilder cholesterol(final String cholesterol) {
            this.cholesterol = (cholesterol == null || cholesterol.isBlank()) ? -1d : parseDouble(cholesterol);
            return this;
        }
        
        public FormBuilder sodium(final String sodium) {
            this.sodium = (sodium == null || sodium.isBlank()) ? -1d : parseDouble(sodium);
            return this;
        }
        
        public FormBuilder category(final Category category) {
            if (category == null) {
                throw new NumberFormatException("Please enter a valid category");
            }
            this.category = category;
            return this;
        }
        
        public FormBuilder notes(final String notes) {
            this.notes = notes;
            return this;
        }
        
        public FormBuilder referenceLink(final String linkURL) {
            this.referenceLink = linkURL;
            return this;
        }
        
        public NutritionalIngredient build() {
            return new NutritionalIngredient(this);
        }
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(final Category category) {
        this.category = category;
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

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getReferenceLink() {
        return referenceLink;
    }

    public void setReferenceLink(final String referenceLink) {
        this.referenceLink = referenceLink;
    }

    @Override
    public String toString() {
        return "NutritionalIngredient{" + "id=" + id + ", name=" + name + ", grams=" + grams + 
                ", calories=" + calories + ", fat=" + fat + ", sugar=" + sugar + ", carbohydrates=" + carbohydrates + 
                ", protein=" + protein + ", cholesterol=" + cholesterol + ", sodium=" + sodium + ", category=" + 
                category + ", notes=" + notes + ", referenceLink=" + referenceLink + '}';
    }
    
}
