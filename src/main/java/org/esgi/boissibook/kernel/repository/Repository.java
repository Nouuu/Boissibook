package org.esgi.boissibook.kernel.repository;


import org.esgi.boissibook.kernel.exception.ConflictException;
import org.esgi.boissibook.kernel.exception.NotFoundException;

import java.util.List;

public interface Repository<T extends DomainEntity, U extends DomainId<String>> {
    U save(T entity) throws ConflictException;

    long count();

    List<T> findAll();

    T find(U id) throws NotFoundException;

    void delete(T entity) throws NotFoundException;

    void deleteAll();

    U nextId();
}
