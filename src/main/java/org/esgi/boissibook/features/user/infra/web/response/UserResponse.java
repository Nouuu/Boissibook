package org.esgi.boissibook.features.user.infra.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.esgi.boissibook.features.user.domain.User;

public record UserResponse(
        @JsonProperty String userId,
        @JsonProperty String email,
        @JsonProperty String name) {

    public static UserResponse fromUser(User user) {
        return new UserResponse(
                user.id(),
                user.email(),
                user.name()
        );
    }
}
