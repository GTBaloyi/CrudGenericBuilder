package com.crud.generic.crudGenericBuilder.service.impl;

import com.crud.generic.crudGenericBuilder.model.TestEntity;
import com.crud.generic.crudGenericBuilder.repository.TestRepository;
import com.crud.generic.crudGenericBuilder.service.TestService;
import org.springframework.stereotype.Service;

/**
 * Test service implementation that extends GenericServiceImpl.
 * This is used for testing the generic CRUD components.
 */
@Service
public class TestServiceImpl extends GenericServiceImpl<TestEntity, Long> implements TestService {
    
    private final TestRepository testRepository;
    
    /**
     * Constructor that takes a repository.
     * 
     * @param testRepository the repository to use
     */
    public TestServiceImpl(TestRepository testRepository) {
        super(testRepository);
        this.testRepository = testRepository;
    }
}