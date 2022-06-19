package org.esgi.boissibook.features.user.infra.repository;

import org.esgi.boissibook.features.user.domain.User;
import org.esgi.boissibook.kernel.repository.UserId;

final class UserEntityMapper {

    private UserEntityMapper() {
    }

    static User toUser(UserEntity userEntity) {
        return new User(UserId.of(userEntity.id()), userEntity.name(), userEntity.email(), userEntity.password());
    }

    static UserEntity toUserEntity(User user) {
        return new UserEntity(user.id().value(), user.name(), user.email(), user.password());
    }

}
