package enums;

import java.util.EnumSet;

public enum TeacherPosition {
    TUTOR("Tutor", 1, false),
    LECTURER("Lecturer", 2, false),
    SENIOR_LECTURER("Senior Lecturer", 3, false),
    ASSOCIATE_PROFESSOR("Associate Professor", 4, true),
    PROFESSOR("Professor", 5, true);

    private final String title;
    private final int level;
    private final boolean researcherRequired;

    TeacherPosition(String title, int level, boolean researcherRequired) {
        this.title = title;
        this.level = level;
        this.researcherRequired = researcherRequired;
    }

    public String getTitle() {
        return title;
    }

    public int getLevel() {
        return level;
    }

    public boolean requiresResearcher() {
        return researcherRequired;
    }

    public boolean isProfessor() {
        return this == PROFESSOR;
    }

    public boolean isSenior() {
        return level >= 3;
    }

    public boolean canPromoteTo(TeacherPosition next) {
        if (next == null)
            return false;
        return next.level > this.level;
    }

    public static EnumSet<TeacherPosition> professorLevels() {
        return EnumSet.of(ASSOCIATE_PROFESSOR, PROFESSOR);
    }

    @Override
    public String toString() {
        return title;
    }
}
