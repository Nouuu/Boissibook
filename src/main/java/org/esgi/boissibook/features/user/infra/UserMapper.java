package org.esgi.boissibook.features.user.infra;

import org.esgi.boissibook.features.user.domain.User;
import org.esgi.boissibook.features.user.infra.repository.UserEntity;
import org.esgi.boissibook.features.user.infra.web.request.CreateUserRequest;
import org.esgi.boissibook.features.user.infra.web.request.UpdateUserRequest;
import org.esgi.boissibook.features.user.infra.web.response.UserResponse;
import org.esgi.boissibook.kernel.repository.UserId;

public final class UserMapper {

    private UserMapper() {
    }

    public static User toUser(CreateUserRequest createUserRequest) {
        return new User(null, createUserRequest.name(), createUserRequest.email(), createUserRequest.password());
    }

    public static User toUser(String id, UpdateUserRequest updateUserRequest) {
        return new User(UserId.of(id), updateUserRequest.name(), updateUserRequest.email(), updateUserRequest.password());
    }

    public static UserResponse toUserResponse(User user) {
        return new UserResponse(user.id().value(), user.name(), user.email());
    }

    public static User toUser(UserEntity userEntity) {
        return new User(UserId.of(userEntity.id()), userEntity.name(), userEntity.email(), userEntity.password());
    }

    public static UserEntity toUserEntity(User user) {
        return new UserEntity(user.id().value(), user.name(), user.email(), user.password());
    }

}
