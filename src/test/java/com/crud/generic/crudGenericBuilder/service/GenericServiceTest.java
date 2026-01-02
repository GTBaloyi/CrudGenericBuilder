package com.crud.generic.crudGenericBuilder.service;

import com.crud.generic.crudGenericBuilder.model.TestEntity;
import com.crud.generic.crudGenericBuilder.repository.TestRepository;
import com.crud.generic.crudGenericBuilder.service.impl.TestServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests for the GenericService interface and GenericServiceImpl class.
 */
@ExtendWith(MockitoExtension.class)
public class GenericServiceTest {

    @Mock
    private TestRepository repository;

    private TestService service;

    @BeforeEach
    public void setUp() {
        service = new TestServiceImpl(repository);
    }

    @Test
    public void testSave() {
        // Create a test entity
        TestEntity entity = new TestEntity(1L, "Test Name", "Test Description");
        
        // Mock the repository save method
        when(repository.save(entity)).thenReturn(entity);
        
        // Call the service save method
        TestEntity savedEntity = service.save(entity);
        
        // Verify the repository save method was called
        verify(repository, times(1)).save(entity);
        
        // Verify the saved entity is the same as the one returned by the repository
        assertNotNull(savedEntity, "The saved entity should not be null");
        assertEquals(entity.getId(), savedEntity.getId(), "The IDs should match");
        assertEquals(entity.getName(), savedEntity.getName(), "The names should match");
        assertEquals(entity.getDescription(), savedEntity.getDescription(), "The descriptions should match");
    }
    
    @Test
    public void testFindById() {
        // Create a test entity
        TestEntity entity = new TestEntity(1L, "Test Name", "Test Description");
        
        // Mock the repository findById method
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        
        // Call the service findById method
        Optional<TestEntity> foundEntity = service.findById(1L);
        
        // Verify the repository findById method was called
        verify(repository, times(1)).findById(1L);
        
        // Verify the found entity is the same as the one returned by the repository
        assertTrue(foundEntity.isPresent(), "The entity should be found");
        assertEquals(entity.getId(), foundEntity.get().getId(), "The IDs should match");
        assertEquals(entity.getName(), foundEntity.get().getName(), "The names should match");
        assertEquals(entity.getDescription(), foundEntity.get().getDescription(), "The descriptions should match");
    }
    
    @Test
    public void testFindAll() {
        // Create test entities
        TestEntity entity1 = new TestEntity(1L, "Test Name 1", "Test Description 1");
        TestEntity entity2 = new TestEntity(2L, "Test Name 2", "Test Description 2");
        List<TestEntity> entities = Arrays.asList(entity1, entity2);
        
        // Mock the repository findAll method
        when(repository.findAll()).thenReturn(entities);
        
        // Call the service findAll method
        List<TestEntity> foundEntities = service.findAll();
        
        // Verify the repository findAll method was called
        verify(repository, times(1)).findAll();
        
        // Verify the found entities are the same as the ones returned by the repository
        assertNotNull(foundEntities, "The found entities should not be null");
        assertEquals(2, foundEntities.size(), "There should be 2 entities");
        assertEquals(entity1.getId(), foundEntities.get(0).getId(), "The IDs should match");
        assertEquals(entity1.getName(), foundEntities.get(0).getName(), "The names should match");
        assertEquals(entity1.getDescription(), foundEntities.get(0).getDescription(), "The descriptions should match");
        assertEquals(entity2.getId(), foundEntities.get(1).getId(), "The IDs should match");
        assertEquals(entity2.getName(), foundEntities.get(1).getName(), "The names should match");
        assertEquals(entity2.getDescription(), foundEntities.get(1).getDescription(), "The descriptions should match");
    }
    
    @Test
    public void testDeleteById() {
        // Call the service deleteById method
        service.deleteById(1L);
        
        // Verify the repository deleteById method was called
        verify(repository, times(1)).deleteById(1L);
    }
    
    @Test
    public void testDelete() {
        // Create a test entity
        TestEntity entity = new TestEntity(1L, "Test Name", "Test Description");
        
        // Call the service delete method
        service.delete(entity);
        
        // Verify the repository delete method was called
        verify(repository, times(1)).delete(entity);
    }
    
    @Test
    public void testCount() {
        // Mock the repository count method
        when(repository.count()).thenReturn(2L);
        
        // Call the service count method
        long count = service.count();
        
        // Verify the repository count method was called
        verify(repository, times(1)).count();
        
        // Verify the count is the same as the one returned by the repository
        assertEquals(2L, count, "The count should be 2");
    }
}