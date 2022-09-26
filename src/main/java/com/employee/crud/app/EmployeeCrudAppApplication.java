package com.employee.crud.app;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dozer.DozerBeanMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

//@SpringBootApplication
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
//@PropertySource(value = "file:C:\\SwipeTech\\Projects\\DEMOS\\PROPERTIES\\application.properties", ignoreResourceNotFound = true)
//@PropertySource(value = "file:C:\\SwipeTech\\Projects\\DEMOS\\PROPERTIES\\swagger.properties", ignoreResourceNotFound = true)

public class EmployeeCrudAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeCrudAppApplication.class, args);
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    @Bean
    public DozerBeanMapper dozerBeanMapper() {
        return new DozerBeanMapper();
    }

}
