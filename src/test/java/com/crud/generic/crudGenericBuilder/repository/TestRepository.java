package com.crud.generic.crudGenericBuilder.repository;

import com.crud.generic.crudGenericBuilder.model.TestEntity;
import org.springframework.stereotype.Repository;

/**
 * Test repository interface that extends GenericRepository.
 * This is used for testing the generic CRUD components.
 */
@Repository
public interface TestRepository extends GenericRepository<TestEntity, Long> {
    // No additional methods needed for basic testing
}