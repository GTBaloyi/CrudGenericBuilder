package com.crud.generic.crudGenericBuilder.repository;

import com.crud.generic.crudGenericBuilder.model.TestEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the GenericRepository interface.
 */
@DataJpaTest
public class GenericRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TestRepository repository;

    @Test
    public void testSave() {
        // Create a test entity
        TestEntity entity = new TestEntity(null, "Test Name", "Test Description");
        
        // Save the entity
        TestEntity savedEntity = repository.save(entity);
        
        // Verify the entity was saved
        assertNotNull(savedEntity.getId(), "The saved entity should have an ID");
        
        // Verify the entity can be retrieved
        TestEntity foundEntity = entityManager.find(TestEntity.class, savedEntity.getId());
        assertNotNull(foundEntity, "The entity should be found in the database");
        assertEquals(savedEntity.getId(), foundEntity.getId(), "The IDs should match");
        assertEquals("Test Name", foundEntity.getName(), "The names should match");
        assertEquals("Test Description", foundEntity.getDescription(), "The descriptions should match");
    }
    
    @Test
    public void testFindById() {
        // Create and persist a test entity
        TestEntity entity = new TestEntity(null, "Test Name", "Test Description");
        entityManager.persist(entity);
        entityManager.flush();
        
        // Find the entity by ID
        Optional<TestEntity> foundEntity = repository.findById(entity.getId());
        
        // Verify the entity was found
        assertTrue(foundEntity.isPresent(), "The entity should be found");
        assertEquals(entity.getId(), foundEntity.get().getId(), "The IDs should match");
        assertEquals("Test Name", foundEntity.get().getName(), "The names should match");
        assertEquals("Test Description", foundEntity.get().getDescription(), "The descriptions should match");
    }
    
    @Test
    public void testFindAll() {
        // Create and persist multiple test entities
        TestEntity entity1 = new TestEntity(null, "Test Name 1", "Test Description 1");
        TestEntity entity2 = new TestEntity(null, "Test Name 2", "Test Description 2");
        entityManager.persist(entity1);
        entityManager.persist(entity2);
        entityManager.flush();
        
        // Find all entities
        List<TestEntity> entities = repository.findAll();
        
        // Verify all entities were found
        assertFalse(entities.isEmpty(), "The list of entities should not be empty");
        assertTrue(entities.size() >= 2, "There should be at least 2 entities");
        
        // Verify the entities contain the ones we added
        boolean foundEntity1 = false;
        boolean foundEntity2 = false;
        for (TestEntity entity : entities) {
            if (entity.getId().equals(entity1.getId())) {
                foundEntity1 = true;
                assertEquals("Test Name 1", entity.getName(), "The names should match");
                assertEquals("Test Description 1", entity.getDescription(), "The descriptions should match");
            } else if (entity.getId().equals(entity2.getId())) {
                foundEntity2 = true;
                assertEquals("Test Name 2", entity.getName(), "The names should match");
                assertEquals("Test Description 2", entity.getDescription(), "The descriptions should match");
            }
        }
        assertTrue(foundEntity1, "Entity 1 should be found");
        assertTrue(foundEntity2, "Entity 2 should be found");
    }
    
    @Test
    public void testDeleteById() {
        // Create and persist a test entity
        TestEntity entity = new TestEntity(null, "Test Name", "Test Description");
        entityManager.persist(entity);
        entityManager.flush();
        
        // Delete the entity by ID
        repository.deleteById(entity.getId());
        
        // Verify the entity was deleted
        TestEntity foundEntity = entityManager.find(TestEntity.class, entity.getId());
        assertNull(foundEntity, "The entity should not be found after deletion");
    }
    
    @Test
    public void testDelete() {
        // Create and persist a test entity
        TestEntity entity = new TestEntity(null, "Test Name", "Test Description");
        entityManager.persist(entity);
        entityManager.flush();
        
        // Delete the entity
        repository.delete(entity);
        
        // Verify the entity was deleted
        TestEntity foundEntity = entityManager.find(TestEntity.class, entity.getId());
        assertNull(foundEntity, "The entity should not be found after deletion");
    }
    
    @Test
    public void testCount() {
        // Get the initial count
        long initialCount = repository.count();
        
        // Create and persist multiple test entities
        TestEntity entity1 = new TestEntity(null, "Test Name 1", "Test Description 1");
        TestEntity entity2 = new TestEntity(null, "Test Name 2", "Test Description 2");
        entityManager.persist(entity1);
        entityManager.persist(entity2);
        entityManager.flush();
        
        // Get the new count
        long newCount = repository.count();
        
        // Verify the count increased by 2
        assertEquals(initialCount + 2, newCount, "The count should increase by 2");
    }
}