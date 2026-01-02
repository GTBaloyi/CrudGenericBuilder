package com.crud.generic.crudGenericBuilder.service;

import com.crud.generic.crudGenericBuilder.model.TestEntity;

/**
 * Test service interface that extends GenericService.
 * This is used for testing the generic CRUD components.
 */
public interface TestService extends GenericService<TestEntity, Long> {
    // No additional methods needed for basic testing
}