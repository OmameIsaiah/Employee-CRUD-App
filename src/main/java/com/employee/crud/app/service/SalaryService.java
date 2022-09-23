package com.employee.crud.app.service;

import com.employee.crud.app.model.Salary;
import com.employee.crud.app.model.WorkHistory;

import java.util.List;

public interface SalaryService {
    Salary saveSalary(Salary salary);
    Salary updateSalary(Salary salary);
    Salary findSalaryByID(Long id);
    void deleteSalaryByID(Long id);
    List<Salary> findAllSalaries();
    List<Salary> findAllSalariesByEmployeeNumber(String employeeNumber);

}
