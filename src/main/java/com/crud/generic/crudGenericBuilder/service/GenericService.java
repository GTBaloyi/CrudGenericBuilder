package com.crud.generic.crudGenericBuilder.service;

import com.crud.generic.crudGenericBuilder.model.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Generic service interface for CRUD operations.
 * 
 * @param <T> The entity type
 * @param <ID> The type of the entity's ID
 */
public interface GenericService<T extends BaseEntity<ID>, ID extends Serializable> {

    T save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    void deleteById(ID id);
    void delete(T entity);
    long count();
}