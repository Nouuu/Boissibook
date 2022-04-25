package org.esgi.boissibook.features.user.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAUserRepository extends JpaRepository<UserEntity, String> {
}
