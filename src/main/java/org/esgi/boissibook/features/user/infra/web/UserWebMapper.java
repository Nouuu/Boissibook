package org.esgi.boissibook.features.user.infra.web;

import org.esgi.boissibook.features.user.domain.User;
import org.esgi.boissibook.features.user.infra.web.request.CreateUserRequest;
import org.esgi.boissibook.features.user.infra.web.request.UpdateUserRequest;
import org.esgi.boissibook.features.user.infra.web.response.UserResponse;
import org.esgi.boissibook.kernel.repository.UserId;

final class UserWebMapper {

    private UserWebMapper() {
    }

    static User toUser(CreateUserRequest createUserRequest) {
        return new User(null, createUserRequest.name(), createUserRequest.email(), createUserRequest.password());
    }

    static User toUser(String id, UpdateUserRequest updateUserRequest) {
        return new User(UserId.of(id), updateUserRequest.name(), updateUserRequest.email(), updateUserRequest.password());
    }

    static UserResponse toUserResponse(User user) {
        return new UserResponse(user.id().value(), user.name(), user.email());
    }
}
