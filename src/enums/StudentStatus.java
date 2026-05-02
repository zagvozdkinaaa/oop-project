package enums;

import java.util.EnumSet;

public enum StudentStatus {
    ACTIVE("Active", true),
    ON_LEAVE("On Leave", true),
    EXPELLED("Expelled", false),
    GRADUATED("Graduated", false);

    private final String description;
    private final boolean canStudy;

    StudentStatus(String description, boolean canStudy) {
        this.description = description;
        this.canStudy = canStudy;
    }

    public String getDescription() {
        return description;
    }

    public boolean canStudy() {
        return canStudy;
    }

    public boolean isActive() {
        return this == ACTIVE;
    }

    public boolean isFinalStatus() {
        return EnumSet.of(EXPELLED, GRADUATED).contains(this);
    }

    public boolean canTransitionTo(StudentStatus next) {
        return switch (this) {
            case ACTIVE -> next != ACTIVE;
            case ON_LEAVE -> next == ACTIVE;
            case EXPELLED, GRADUATED -> false;
        };
    }

    @Override
    public String toString() {
        return description;
    }
}
