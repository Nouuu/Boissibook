package org.esgi.boissibook.features.achievement.domain;

public enum AchievementLevel {
    SILVER("Not bad !", 10), GOLD("Book amateur", 100), DIAMOND("Book writer", 1000), PLATINUM("Professional book critic", 10000);

    private final String title;
    private final int neededCount;

    AchievementLevel(String title, int neededCount) {
        this.title = title;
        this.neededCount = neededCount;
    }

    public String title() {
        return title;
    }

    public int neededCount() {
        return neededCount;
    }
}
