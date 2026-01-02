package com.crud.generic.crudGenericBuilder.controller;

import com.crud.generic.crudGenericBuilder.model.TestEntity;
import com.crud.generic.crudGenericBuilder.service.TestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests for the GenericController class.
 */
@ExtendWith(MockitoExtension.class)
public class GenericControllerTest {

    @Mock
    private TestService service;

    @InjectMocks
    private TestController controller;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreate() throws Exception {
        // Create a test entity
        TestEntity entity = new TestEntity(null, "Test Name", "Test Description");
        TestEntity savedEntity = new TestEntity(1L, "Test Name", "Test Description");
        
        // Mock the service save method
        when(service.save(any(TestEntity.class))).thenReturn(savedEntity);
        
        // Perform the request
        mockMvc.perform(post("/api/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(entity)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Name"))
                .andExpect(jsonPath("$.description").value("Test Description"));
        
        // Verify the service save method was called
        verify(service, times(1)).save(any(TestEntity.class));
    }
    
    @Test
    public void testFindById_Found() throws Exception {
        // Create a test entity
        TestEntity entity = new TestEntity(1L, "Test Name", "Test Description");
        
        // Mock the service findById method
        when(service.findById(1L)).thenReturn(Optional.of(entity));
        
        // Perform the request
        mockMvc.perform(get("/api/test/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Name"))
                .andExpect(jsonPath("$.description").value("Test Description"));
        
        // Verify the service findById method was called
        verify(service, times(1)).findById(1L);
    }
    
    @Test
    public void testFindById_NotFound() throws Exception {
        // Mock the service findById method
        when(service.findById(1L)).thenReturn(Optional.empty());
        
        // Perform the request
        mockMvc.perform(get("/api/test/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Entity with id 1 not found"));
        
        // Verify the service findById method was called
        verify(service, times(1)).findById(1L);
    }
    
    @Test
    public void testFindAll() throws Exception {
        // Create test entities
        TestEntity entity1 = new TestEntity(1L, "Test Name 1", "Test Description 1");
        TestEntity entity2 = new TestEntity(2L, "Test Name 2", "Test Description 2");
        List<TestEntity> entities = Arrays.asList(entity1, entity2);
        
        when(service.findAll()).thenReturn(entities);
        
        // Perform the request
        mockMvc.perform(get("/api/test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Test Name 1"))
                .andExpect(jsonPath("$[0].description").value("Test Description 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Test Name 2"))
                .andExpect(jsonPath("$[1].description").value("Test Description 2"));
        
        // Verify the service findAll method was called
        verify(service, times(1)).findAll();
    }
    
    @Test
    public void testDeleteById_Found() throws Exception {
        // Create a test entity
        TestEntity entity = new TestEntity(1L, "Test Name", "Test Description");
        
        // Mock the service findById method
        when(service.findById(1L)).thenReturn(Optional.of(entity));
        
        // Perform the request
        mockMvc.perform(delete("/api/test/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Entity with id 1 deleted successfully"));
        
        // Verify the service findById and deleteById methods were called
        verify(service, times(1)).findById(1L);
        verify(service, times(1)).deleteById(1L);
    }
    
    @Test
    public void testDeleteById_NotFound() throws Exception {
        // Mock the service findById method
        when(service.findById(1L)).thenReturn(Optional.empty());
        
        // Perform the request
        mockMvc.perform(delete("/api/test/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Entity with id 1 not found"));
        
        // Verify the service findById method was called and deleteById was not
        verify(service, times(1)).findById(1L);
        verify(service, never()).deleteById(1L);
    }
    
    @Test
    public void testUpdate_Found() throws Exception {
        // Create test entities
        TestEntity entity = new TestEntity(null, "Updated Name", "Updated Description");
        TestEntity existingEntity = new TestEntity(1L, "Test Name", "Test Description");
        TestEntity updatedEntity = new TestEntity(1L, "Updated Name", "Updated Description");
        
        // Mock the service findById and save methods
        when(service.findById(1L)).thenReturn(Optional.of(existingEntity));
        when(service.save(any(TestEntity.class))).thenReturn(updatedEntity);
        
        // Perform the request
        mockMvc.perform(put("/api/test/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(entity)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Name"))
                .andExpect(jsonPath("$.description").value("Updated Description"));
        
        // Verify the service findById and save methods were called
        verify(service, times(1)).findById(1L);
        verify(service, times(1)).save(any(TestEntity.class));
    }
    
    @Test
    public void testUpdate_NotFound() throws Exception {
        // Create a test entity
        TestEntity entity = new TestEntity(null, "Updated Name", "Updated Description");
        
        // Mock the service findById method
        when(service.findById(1L)).thenReturn(Optional.empty());
        
        // Perform the request
        mockMvc.perform(put("/api/test/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(entity)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Entity with id 1 not found"));
        
        // Verify the service findById method was called and save was not
        verify(service, times(1)).findById(1L);
        verify(service, never()).save(any(TestEntity.class));
    }
}