package howmanycals.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Meal {
    private Integer id;
    private String name;
    private String notes;
    private LocalDateTime creationDate;
    private List<NutritionalIngredient> ingredients;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(final LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Meal{" + "id=" + id + ", name=" + name + ", notes=" + notes + 
                ", creationDate=" + creationDate + ", ingredients=" + ingredients + '}';
    }
    
}
