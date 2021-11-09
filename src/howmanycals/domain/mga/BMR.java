package howmanycals.domain.mga;

import java.util.Objects;

/**
 *
 * @author leogtzr
 */
public class BMR {
    private final IndividualInformation individualInformation;

    public BMR(final IndividualInformation individualInformation) {
        Objects.nonNull(individualInformation);
        this.individualInformation = individualInformation;
    }
    
    public double calculate() {
        return switch (this.individualInformation.getGender()) {
            // For women: BMR = 10 x weight (kg) + 6.25 x height (cm) – 5 x age (years) – 161
            case FEMALE -> 10 * this.individualInformation.getWeight() + 6.25 * this.individualInformation.getHeight() - 
                5 * this.individualInformation.getAge() - 161;
            
            // For men: BMR = 10 x weight (kg) + 6.25 x height (cm) – 5 x age (years) + 5
            case MALE -> 10 * this.individualInformation.getWeight() + 6.25 * this.individualInformation.getHeight() - 
                5 * this.individualInformation.getAge() + 5;
        };
    }
}
