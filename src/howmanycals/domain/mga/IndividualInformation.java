package howmanycals.domain.mga;

import java.util.Objects;

/**
 *
 * @author leogtzr
 */
public class IndividualInformation {
    
    private double weight;
    private int height;
    private int age;
    private Gender gender;
    private PhysicalActivityLevel physicalActivityLevel;

    public double getWeight() {
        return weight;
    }

    public void setWeight(final double weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(final int height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(final Gender gender) {
        this.gender = gender;
    }

    public PhysicalActivityLevel getPhysicalActivityLevel() {
        return physicalActivityLevel;
    }

    public void setPhysicalActivityLevel(final PhysicalActivityLevel physicalActivityLevel) {
        this.physicalActivityLevel = physicalActivityLevel;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.weight) ^ (Double.doubleToLongBits(this.weight) >>> 32));
        hash = 89 * hash + this.height;
        hash = 89 * hash + this.age;
        hash = 89 * hash + Objects.hashCode(this.gender);
        hash = 89 * hash + Objects.hashCode(this.physicalActivityLevel);
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
  
        final IndividualInformation other = (IndividualInformation) obj;
        if (Double.doubleToLongBits(this.weight) != Double.doubleToLongBits(other.weight)) {
            return false;
        }
        if (this.height != other.height) {
            return false;
        }
        if (this.age != other.age) {
            return false;
        }
        if (this.gender != other.gender) {
            return false;
        }
        if (this.physicalActivityLevel != other.physicalActivityLevel) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final String info = String.format("%d kg, age: %d (%s) (%s)", 
                (int) this.weight
                , this.age
                , this.gender
                , this.physicalActivityLevel.description()
        );
        
        return info;
    }
    
    
}
