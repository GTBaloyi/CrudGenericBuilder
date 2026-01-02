package com.crud.generic.crudGenericBuilder.repository;

import com.crud.generic.crudGenericBuilder.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Generic repository interface that extends JpaRepository.
 * This provides basic CRUD operations for entities that implement BaseEntity.
 * 
 * @param <T> The entity type
 * @param <ID> The type of the entity's ID
 */
@NoRepositoryBean
public interface GenericRepository<T extends BaseEntity<ID>, ID extends Serializable> extends JpaRepository<T, ID> {

}