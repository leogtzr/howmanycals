package howmanycals.domain.mga;

/**
 *
 * @author leogtzr
 */
public enum PhysicalActivityLevel {
    SEDENTARY("(little or no exercise) : BMR x 1.2", 1.2)
    , LIGHTLY_ACTIVE("(light exercise/sports 1-3 days/week) : BMR x 1.375", 1.375)
    , MODERATELY_ACTIVE("(moderate exercise/sports 3-5 days/week) : BMR x 1.55", 1.55)
    , VERY_ACTIVE("(hard exercise/sports 6-7 days a week) : BMR x 1.725", 1.725)
    , EXTRA_ACTIVE(" (very hard exercise/sports & a physical job) : BMR x 1.9", 1.9)
    ;
    
    private final String description;
    private final double factor;

    private PhysicalActivityLevel(final String description, final double factor) {
        this.description = description;
        this.factor = factor;
    }
    
    public String description() {
        return this.description;
    }
    
    public double factor() {
        return this.factor;
    }
}
