package org.esgi.boissibook.features.user.domain;

import org.esgi.boissibook.kernel.repository.Repository;

public interface UserRepository extends Repository<User> {

    User findByEmail(String email);
}
