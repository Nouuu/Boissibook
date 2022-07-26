package org.esgi.boissibook.features.achievement.kernel.exception;

import org.esgi.boissibook.kernel.exception.NotFoundException;

public class UserStatisticsNotFoundException extends NotFoundException {
    public UserStatisticsNotFoundException(String message) {
        super(message);
    }
}
