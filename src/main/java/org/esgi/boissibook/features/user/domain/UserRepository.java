package org.esgi.boissibook.features.user.domain;

import org.esgi.boissibook.kernel.repository.Repository;
import org.esgi.boissibook.kernel.repository.UserId;

public interface UserRepository extends Repository<User, UserId> {

    User findByEmail(String email);
}
