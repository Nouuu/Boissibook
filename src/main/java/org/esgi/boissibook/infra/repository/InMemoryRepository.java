package org.esgi.boissibook.infra.repository;

import org.esgi.boissibook.kernel.exception.ConflictException;
import org.esgi.boissibook.kernel.exception.NotFoundException;
import org.esgi.boissibook.kernel.repository.DomainEntity;
import org.esgi.boissibook.kernel.repository.DomainId;
import org.esgi.boissibook.kernel.repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryRepository<T extends DomainEntity, U extends DomainId<String>> implements Repository<T, U> {

    protected final Map<String, T> data = new ConcurrentHashMap<>();

    @Override
    public U save(T entity) throws ConflictException {
        data.put(Objects.requireNonNull(entity.id(), "Id can't be null").value(), entity);
        return (U) entity.id();
    }

    @Override
    public long count() {
        return data.size();
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public T find(U id) throws NotFoundException {
        var result = data.get(Objects.requireNonNull(id, "Id can't be null").value());
        if (result == null) {
            throw new NotFoundException("No entity for " + id.value());
        }
        return result;
    }

    @Override
    public void delete(T entity) throws NotFoundException {
        if (data.remove(Objects.requireNonNull(entity.id(), "Id can't be null").value()) == null) {
            throw new NotFoundException("No entity for " + entity.id().value());
        }
    }

    @Override
    public void deleteAll() {
        data.clear();
    }

    @Override
    public U nextId() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
