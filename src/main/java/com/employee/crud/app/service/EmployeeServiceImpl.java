package com.employee.crud.app.service;

import com.employee.crud.app.exception.EmailAlreadyExistsException;
import com.employee.crud.app.exception.PhoneNoAlreadyExistsException;
import com.employee.crud.app.exception.UsernameAlreadyExistsException;
import com.employee.crud.app.model.Employee;
import com.employee.crud.app.model.Salary;
import com.employee.crud.app.model.User;
import com.employee.crud.app.model.WorkHistory;
import com.employee.crud.app.payload.request.WorkHistoryRequest;
import com.employee.crud.app.repository.EmployeeRepository;
import com.employee.crud.app.repository.SalaryRepository;
import com.employee.crud.app.repository.WorkHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@Transactional
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private WorkHistoryService historyService;
    @Autowired
    private SalaryService salaryService;

    @Override
    public Employee saveEmployee(Employee e) {
        log.info("registering employee {} {}", e.getFirstName(), e.getSurname());

        Boolean employeeNumber= false;
        Boolean employeeEmail= false;
        Boolean employeeTel= false;
        List<Employee> allEmployees = repository.findAll();
        for (int i = 0; i < allEmployees.size(); i++) {
            if(allEmployees.get(i).getEmployeeNumber().equals(e.getEmployeeNumber())){
                //employee= allEmployees.get(i);
                employeeNumber= true;
                //break;
            }
            if(allEmployees.get(i).getEmail().equals(e.getEmail())){
                employeeEmail= true;
            }
            if(allEmployees.get(i).getPhoneNumber().equals(e.getPhoneNumber())){
                employeeTel= true;
            }
        }
        if(employeeNumber) {
            log.warn("employee number {} already exists.", e.getEmployeeNumber());
            throw new  RuntimeException(String.format("employee number %s already exists",e.getEmployeeNumber()));
        }
        if(employeeEmail) {
            log.warn("employee email {} already exists.", e.getEmail());
            throw new  RuntimeException(String.format("employee email %s already exists",e.getEmail()));
        }
        if(employeeTel) {
            log.warn("employee phone number {} already exists.", e.getPhoneNumber());
            throw new  RuntimeException(String.format("employee phone number %s already exists",e.getPhoneNumber()));
        }



        Set<WorkHistory> history = e.getWorkHistory();
        WorkHistory[] historyArray = history.toArray(new WorkHistory[history.size()]);

        for (int i= 0; i<historyArray.length;i++){
            historyService.saveWorkHistory(historyArray[i]);
        }
        Set<Salary> salary= e.getSalary();
        Salary[] salaryArray = salary.toArray(new Salary[salary.size()]);
        for (int i= 0; i<salaryArray.length;i++){
            salaryService.saveSalary(salaryArray[i]);
        }
        log.info("employee {} {} was registered successfully!", e.getFirstName(), e.getSurname());
        return repository.save(e);
    }

    @Override
    public Employee findEmployeeByID(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Employee findEmployeeBySurname(String surname) {
        log.info("retrieving employee surname {}", surname);
        Employee e= null;
        List<Employee> employees = repository.findAll();
        for (int i = 0; i < employees.size(); i++) {
            if(employees.get(i).getSurname().equals(surname)){
                e= employees.get(i);
                break;
            }
        }
        return e;
    }

    @Override
    public Employee findEmployeeByFirstName(String firstName) {
        log.info("retrieving employee first name {}", firstName);
        Employee e= null;
        List<Employee> employees = repository.findAll();
        for (int i = 0; i < employees.size(); i++) {
            if(employees.get(i).getFirstName().equals(firstName)){
                e= employees.get(i);
                break;
            }
        }
        return e;
    }

    @Override
    public Employee findEmployeeByEmail(String email) {
        log.info("retrieving employee email {}", email);
        Employee e= null;
        List<Employee> employees = repository.findAll();
        for (int i = 0; i < employees.size(); i++) {
            if(employees.get(i).getEmail().equals(email)){
                e= employees.get(i);
                break;
            }
        }
        return e;
    }

    @Override
    public Employee findEmployeeByPhoneNumber(String phoneNumber) {
        log.info("retrieving employee phone number {}", phoneNumber);
        Employee e= null;
        List<Employee> employees = repository.findAll();
        for (int i = 0; i < employees.size(); i++) {
            if(employees.get(i).getPhoneNumber().equals(phoneNumber)){
                e= employees.get(i);
                break;
            }
        }
        return e;
    }

    @Override
    public Employee findEmployeeByNumber(String employeeNumber) {
        log.info("retrieving employee number {}", employeeNumber);
        Employee e= null;
        List<Employee> employees = repository.findAll();
        for (int i = 0; i < employees.size(); i++) {
            if(employees.get(i).getEmployeeNumber().equals(employeeNumber)){
                e= employees.get(i);
                break;
            }
        }
        return e;
    }

    @Override
    public void deleteEmployeeByID(Long id) {
        Optional<Employee> searchedEmployee = repository.findById(id);

        if(searchedEmployee.isPresent()) {
            repository.deleteById(id);
        }
    }

    @Override
    public List<Employee> findAllEmployee() {
        return repository.findAll();
    }

    @Override
    public Employee update(Employee employee) {
        return repository.save(employee);
    }
}
