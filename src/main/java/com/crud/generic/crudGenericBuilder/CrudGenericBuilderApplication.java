package com.crud.generic.crudGenericBuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * Main application class for the CrudGenericBuilder.
 * This class is only activated when the property "crud.generic.builder.standalone" is set to "true".
 * When using this library as a dependency in another Spring Boot application, this class will not be activated.
 */
@SpringBootApplication
@ConditionalOnProperty(name = "crud.generic.builder.standalone", havingValue = "true", matchIfMissing = false)
public class CrudGenericBuilderApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudGenericBuilderApplication.class, args);
	}

}
