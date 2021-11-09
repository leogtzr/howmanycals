package howmanycals.utils;

import howmanycals.domain.mga.Gender;
import howmanycals.domain.mga.IndividualInformation;
import howmanycals.domain.mga.PhysicalActivityLevel;
import java.util.Objects;
import java.util.Properties;

/**
 *
 * @author leogtzr
 */
public final class IndividualInformationUtils {
    
    private IndividualInformationUtils() {
        throw new UnsupportedOperationException();
    }
    
    public static IndividualInformation fromProperties(final Properties properties) {
        Objects.nonNull(properties);
        
        final IndividualInformation individualInformation = new IndividualInformation();
        
        final double weight = Double.valueOf((String) properties.get("weight"));
        final int age = Integer.valueOf((String) properties.get("age"));
        final int height = Integer.valueOf((String) properties.get("height"));
        final String genderType = (String) properties.getOrDefault("gender", "male");
        final String physicalActivityLevelType = (String) properties.get("pal");
        final Gender gender = Gender.valueOf(genderType.toUpperCase());
        final PhysicalActivityLevel physicalActivityLevel = PhysicalActivityLevel.valueOf(physicalActivityLevelType.toUpperCase());
        
        individualInformation.setWeight(weight);
        individualInformation.setAge(age);
        individualInformation.setHeight(height);
        individualInformation.setGender(gender);
        individualInformation.setPhysicalActivityLevel(physicalActivityLevel);
        
        return individualInformation;
    }
    
}
