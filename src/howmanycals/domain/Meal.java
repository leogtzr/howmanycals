package howmanycals.domain;

import java.util.List;

public class Meal {
    
    private String name;
    private List<NutritionalIngredient> ingredients;
    private String notes;

    public Meal(final String name, final List<NutritionalIngredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<NutritionalIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(final List<NutritionalIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(final String notes) {
        this.notes = notes;
    }
    
}
