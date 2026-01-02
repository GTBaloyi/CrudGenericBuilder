package com.crud.generic.crudGenericBuilder.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the BaseEntity interface.
 */
public class BaseEntityTest {

    @Test
    public void testBaseEntityImplementation() {
        // Create a test entity
        TestEntity entity = new TestEntity();
        
        // Test setId and getId
        Long id = 1L;
        entity.setId(id);
        assertEquals(id, entity.getId(), "getId should return the id that was set");
        
        // Test that the entity is serializable
        assertTrue(entity instanceof java.io.Serializable, "Entity should be serializable");
    }
    
    @Test
    public void testBaseEntityWithConstructor() {
        // Create a test entity with constructor
        Long id = 2L;
        String name = "Test Name";
        String description = "Test Description";
        TestEntity entity = new TestEntity(id, name, description);
        
        // Test that the id was set correctly
        assertEquals(id, entity.getId(), "getId should return the id that was set in the constructor");
        assertEquals(name, entity.getName(), "getName should return the name that was set in the constructor");
        assertEquals(description, entity.getDescription(), "getDescription should return the description that was set in the constructor");
    }
}