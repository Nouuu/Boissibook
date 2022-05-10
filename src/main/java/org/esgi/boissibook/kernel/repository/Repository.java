package org.esgi.boissibook.kernel.repository;


import org.esgi.boissibook.kernel.exception.NotFoundException;

import java.util.List;

public interface Repository<T> {
    String save(T entity);

    long count();

    List<T> findAll();

    T find(String id) throws NotFoundException;

    void delete(T entity) throws NotFoundException;

    void deleteAll();

    String nextId();
}
