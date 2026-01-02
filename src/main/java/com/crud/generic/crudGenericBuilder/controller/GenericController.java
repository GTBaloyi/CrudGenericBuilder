package com.crud.generic.crudGenericBuilder.controller;

import com.crud.generic.crudGenericBuilder.model.BaseEntity;
import com.crud.generic.crudGenericBuilder.service.GenericService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Generic controller for CRUD operations.
 * This is a conceptual implementation that can be extended to create REST controllers
 * once the proper Spring Web dependencies are added to the project.
 * 
 * @param <T> The entity type
 * @param <ID> The type of the entity's ID
 */
public abstract class GenericController<T extends BaseEntity<ID>, ID extends Serializable> {

    protected final GenericService<T, ID> service;

    public GenericController(GenericService<T, ID> service) {
        this.service = service;
    }

    public ResponseEntity<?> create(T entity) {
        T savedEntity = service.save(entity);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }

    public ResponseEntity<?> findById(ID id) {
        Optional<T> entity = service.findById(id);
        if (entity.isPresent()) {
            return new ResponseEntity<>(entity.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Entity with id " + id + " not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> findAll() {
        List<T> entities = service.findAll();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteById(ID id) {
        Optional<T> entity = service.findById(id);
        if (entity.isPresent()) {
            service.deleteById(id);
            return new ResponseEntity<>("Entity with id " + id + " deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Entity with id " + id + " not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> update(ID id, T entity) {
        Optional<T> existingEntity = service.findById(id);
        if (existingEntity.isPresent()) {
            entity.setId(id);
            T updatedEntity = service.save(entity);
            return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
        }
        return new ResponseEntity<>("Entity with id " + id + " not found", HttpStatus.NOT_FOUND);
    }
}
