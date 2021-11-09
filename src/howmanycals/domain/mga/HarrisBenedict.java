package howmanycals.domain.mga;

import java.util.Objects;

/**
 *
 * @author leogtzr
 */
public class HarrisBenedict {
    private final IndividualInformation individualInformation;

    public HarrisBenedict(final IndividualInformation individualInformation) {
        Objects.nonNull(individualInformation);
        this.individualInformation = individualInformation;
    }
    
    public double calculate() {
        final BMR bmr = new BMR(this.individualInformation);
        
        return bmr.calculate() * this.individualInformation.getPhysicalActivityLevel().factor();
    }
}
