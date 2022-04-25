package org.esgi.boissibook.kernel.store;

import java.util.List;

public interface Store<T>{
    String save(T entity);

    long count();

    List<T> findAll();

    T find(String id);

    void delete(T entity);

    void deleteAll();
}
