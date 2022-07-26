package org.esgi.boissibook.features.achievement.kernel.exception;

public enum AchievementExceptionMessage {
    USER_STATS_NOT_FOUND("User statistics not found"), ACHIEVEMENT_NOT_FOUND("Achievement not found");

    private final String message;

    AchievementExceptionMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
