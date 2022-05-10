package org.esgi.boissibook.kernel.repository;


import org.esgi.boissibook.kernel.exception.NotfoundException;

import java.util.List;

public interface Repository<T> {
    String save(T entity);

    long count();

    List<T> findAll();

    T find(String id) throws NotfoundException;

    void delete(T entity) throws NotfoundException;

    void deleteAll();

    String nextId();
}
