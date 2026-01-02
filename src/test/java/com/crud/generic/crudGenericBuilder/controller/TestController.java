package com.crud.generic.crudGenericBuilder.controller;

import com.crud.generic.crudGenericBuilder.model.TestEntity;
import com.crud.generic.crudGenericBuilder.service.TestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Test controller that extends GenericController.
 * This is used for testing the generic CRUD components.
 */
@RestController
@RequestMapping("/api/test")
public class TestController extends GenericController<TestEntity, Long> {

    private final TestService testService;

    /**
     * Constructor that takes a service.
     * 
     * @param testService the service to use
     */
    public TestController(TestService testService) {
        super(testService);
        this.testService = testService;
    }

    @PostMapping
    @Override
    public ResponseEntity<?> create(@RequestBody TestEntity entity) {
        return super.create(entity);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return super.findById(id);
    }

    @GetMapping
    @Override
    public ResponseEntity<?> findAll() {
        return super.findAll();
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return super.deleteById(id);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TestEntity entity) {
        return super.update(id, entity);
    }
}
