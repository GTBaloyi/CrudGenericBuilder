package com.crud.generic.crudGenericBuilder.service.impl;

import com.crud.generic.crudGenericBuilder.model.BaseEntity;
import com.crud.generic.crudGenericBuilder.repository.GenericRepository;
import com.crud.generic.crudGenericBuilder.service.GenericService;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Generic implementation of the GenericService interface.
 * This class provides the implementation for basic CRUD operations.
 * 
 * @param <T> The entity type
 * @param <ID> The type of the entity's ID
 */
public abstract class GenericServiceImpl<T extends BaseEntity<ID>, ID extends Serializable> implements GenericService<T, ID> {

    protected final GenericRepository<T, ID> repository;

    /**
     * Constructor that takes a repository.
     * 
     * @param repository the repository to use
     */
    public GenericServiceImpl(GenericRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Override
    public long count() {
        return repository.count();
    }
}
