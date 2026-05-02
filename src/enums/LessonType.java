package enums;

import java.util.Arrays;

public enum LessonType {
    LECTURE("Lecture", 90),
    PRACTICE("Practice", 60);

    private final String displayName;
    private final int defaultDuration;

    LessonType(String displayName, int defaultDuration) {
        this.displayName = displayName;
        this.defaultDuration = defaultDuration;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getDefaultDuration() {
        return defaultDuration;
    }

    public boolean isLecture() {
        return this == LECTURE;
    }

    public boolean isPractice() {
        return this == PRACTICE;
    }

    public static LessonType fromString(String value) {
        if (value == null) {
            throw new IllegalArgumentException("LessonType cannot be null");
        }

        return Arrays.stream(values())
                .filter(t -> t.name().equalsIgnoreCase(value)
                        || t.displayName.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid LessonType: " + value));
    }

    @Override
    public String toString() {
        return displayName;
    }
}
