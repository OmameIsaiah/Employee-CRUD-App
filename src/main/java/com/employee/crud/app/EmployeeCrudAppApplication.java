package com.employee.crud.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication

@PropertySource(value = "file:C:\\SwipeTech\\Projects\\DEMOS\\PROPERTIES\\application.properties", ignoreResourceNotFound = true)
@PropertySource(value = "file:C:\\SwipeTech\\Projects\\DEMOS\\PROPERTIES\\swagger.properties", ignoreResourceNotFound = true)
public class EmployeeCrudAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeCrudAppApplication.class, args);
    }

}
