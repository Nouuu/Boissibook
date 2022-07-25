package org.esgi.boissibook.features.achievement.kernel.exception;

import org.esgi.boissibook.kernel.exception.NotFoundException;

public class AchievementNotFoundException extends NotFoundException {
    public AchievementNotFoundException(String message) {
        super(message);
    }
}
