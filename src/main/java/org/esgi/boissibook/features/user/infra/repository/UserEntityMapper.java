package org.esgi.boissibook.features.user.infra.repository;

import org.esgi.boissibook.features.user.domain.User;

final class UserEntityMapper {

    private UserEntityMapper() {
    }

    static User toUser(UserEntity userEntity) {
        return new User(userEntity.id(), userEntity.name(), userEntity.email(), userEntity.password());
    }

    static UserEntity toUserEntity(User user) {
        return new UserEntity(user.id(), user.name(), user.email(), user.password());
    }

}
