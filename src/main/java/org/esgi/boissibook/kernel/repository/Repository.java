package org.esgi.boissibook.kernel.repository;

import java.util.List;

public interface Repository<T>{
    String save(T entity);

    long count();

    List<T> findAll();

    T find(String id);

    void delete(T entity);

    void deleteAll();
}
