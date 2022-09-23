package com.employee.crud.app.service;

import com.employee.crud.app.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(Employee e);

    Employee findEmployeeByID(Long id);

    Employee findEmployeeBySurname(String surname);

    Employee findEmployeeByFirstName(String firstName);

    Employee findEmployeeByEmail(String email);

    Employee findEmployeeByPhoneNumber(String phoneNumber);

    Employee findEmployeeByNumber(String employeeNumber);

    void deleteEmployeeByID(Long id);

    List<Employee> findAllEmployee();

    Employee update(Employee employee);
}
