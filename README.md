# CrudGenericBuilder

A generic CRUD (Create, Read, Update, Delete) implementation for Spring Boot applications. This project provides reusable components that can be extended to quickly implement CRUD operations for any entity.

## Components

### 1. BaseEntity Interface
The `BaseEntity` interface defines the common properties that all entities should have, specifically an ID field with getter and setter methods.

```java
public interface BaseEntity<ID extends Serializable> extends Serializable {
    ID getId();
    void setId(ID id);
}
```

### 2. GenericRepository Interface
The `GenericRepository` interface extends Spring Data JPA's `JpaRepository` to provide basic CRUD operations for entities.

```java
@NoRepositoryBean
public interface GenericRepository<T extends BaseEntity<ID>, ID extends Serializable> extends JpaRepository<T, ID> {
    // JpaRepository already provides basic CRUD methods
}
```

### 3. GenericService Interface
The `GenericService` interface defines the contract for CRUD operations at the service layer.

```java
public interface GenericService<T extends BaseEntity<ID>, ID extends Serializable> {
    T save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    void deleteById(ID id);
    void delete(T entity);
    long count();
}
```

### 4. GenericServiceImpl Abstract Class
The `GenericServiceImpl` abstract class provides the implementation for the `GenericService` interface.

```java
public abstract class GenericServiceImpl<T extends BaseEntity<ID>, ID extends Serializable> implements GenericService<T, ID> {
    protected final GenericRepository<T, ID> repository;

    public GenericServiceImpl(GenericRepository<T, ID> repository) {
        this.repository = repository;
    }

    // Implementation of GenericService methods
}
```

### 5. GenericController Abstract Class
The `GenericController` abstract class provides the implementation for basic CRUD operations at the controller layer.

```java
public abstract class GenericController<T extends BaseEntity<ID>, ID extends Serializable> {
    protected final GenericService<T, ID> service;

    public GenericController(GenericService<T, ID> service) {
        this.service = service;
    }

    // Implementation of CRUD operations
}
```

## How to Use

### 1. Create an Entity
Create a class that implements the `BaseEntity` interface:

```java
@Entity
public class User implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    // Getters and setters
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    // Other getters and setters
}
```

### 2. Create a Repository
Create a repository interface that extends the `GenericRepository` interface:

```java
@Repository
public interface UserRepository extends GenericRepository<User, Long> {
    // Add custom query methods if needed
}
```

### 3. Create a Service
Create a service interface that extends the `GenericService` interface:

```java
public interface UserService extends GenericService<User, Long> {
    // Add custom service methods if needed
}
```

Create a service implementation that extends the `GenericServiceImpl` class:

```java
@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {
    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    // Implement custom service methods if needed
}
```

### 4. Create a Controller
Create a controller class that extends the `GenericController` class:

```java
@RestController
@RequestMapping("/api/users")
public class UserController extends GenericController<User, Long> {
    public UserController(UserService service) {
        super(service);
    }

    // Add custom controller methods if needed
}
```

## Dependencies
To use this generic CRUD implementation, you need to add the following dependencies to your project:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

## Note
This implementation is designed to be extended. You can add custom methods to the repository, service, and controller layers as needed for your specific use case.

## Unit Tests
Comprehensive unit tests are provided to verify the functionality of the generic CRUD components:

- `BaseEntityTest` - Tests for the BaseEntity interface
- `GenericRepositoryTest` - Tests for the GenericRepository interface
- `GenericServiceTest` - Tests for the GenericService interface and GenericServiceImpl class
- `GenericControllerTest` - Tests for the GenericController class

These tests ensure that all the basic CRUD operations work correctly and can be used as a reference for implementing your own components.

## Getting Started

### Using as a Maven Dependency

To use this library as a Maven dependency in your Spring Boot application, add the following to your `pom.xml`:

```xml
<dependency>
    <groupId>com.crud.generic</groupId>
    <artifactId>crudGenericBuilder</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

If you're using Gradle, add the following to your `build.gradle`:

```groovy
implementation 'com.crud.generic:crudGenericBuilder:0.0.1-SNAPSHOT'
```

### Required Dependencies

Make sure your project has the following dependencies:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

### Implementation Steps

1. Create your entity classes that implement the `BaseEntity` interface
2. Create your repository interfaces that extend the `GenericRepository` interface
3. Create your service interfaces that extend the `GenericService` interface
4. Create your service implementations that extend the `GenericServiceImpl` class
5. Create your controller classes that extend the `GenericController` class
6. Add custom methods to your components as needed

### Example Implementation

Here's a quick example of how to use the library:

1. Create an entity:

```java
@Entity
public class User implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    // Getters and setters
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    // Other getters and setters
}
```

2. Create a repository:

```java
@Repository
public interface UserRepository extends GenericRepository<User, Long> {
    // Add custom query methods if needed
}
```

3. Create a service interface and implementation:

```java
public interface UserService extends GenericService<User, Long> {
    // Add custom service methods if needed
}

@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {
    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    // Implement custom service methods if needed
}
```

4. Create a controller:

```java
@RestController
@RequestMapping("/api/users")
public class UserController extends GenericController<User, Long> {
    public UserController(UserService service) {
        super(service);
    }

    // Add custom controller methods if needed
}
```

## Building from Source

If you want to build the library from source:

1. Clone the repository
2. Run `mvn clean install`
3. The library will be available in your local Maven repository

## Conclusion

By using this generic CRUD library, you can significantly reduce boilerplate code in your Spring Boot applications and focus on implementing the specific business logic for your domain. The library provides a solid foundation for building RESTful APIs with standardized CRUD operations.
