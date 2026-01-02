package com.crud.generic.crudGenericBuilder.model;

import java.io.Serializable;

/**
 * Base interface for all entities.
 * All domain models should implement this interface to be compatible with the generic CRUD operations.
 * 
 * @param <ID> The type of the entity's ID
 */
public interface BaseEntity<ID extends Serializable> extends Serializable {

    ID getId();
    void setId(ID id);
}