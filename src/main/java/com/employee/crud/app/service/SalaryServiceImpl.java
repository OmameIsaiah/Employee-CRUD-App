package com.employee.crud.app.service;

import com.employee.crud.app.model.Salary;
import com.employee.crud.app.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaryServiceImpl implements SalaryService{
    @Autowired
    private SalaryRepository repository;
    @Override
    public Salary saveSalary(Salary salary) {
        return repository.save(salary);
    }

    @Override
    public Salary updateSalary(Salary salary) {
        return repository.save(salary);
    }

    @Override
    public Salary findSalaryByID(Long id) {
        Salary sl= null;
        Optional<Salary> salary= repository.findById(id);
        if(salary.isPresent()){
            sl= salary.get();
        }
        return sl;
    }

    @Override
    public void deleteSalaryByID(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Salary> findAllSalaries() {
        return repository.findAll();
    }

    @Override
    public List<Salary> findAllSalariesByEmployeeNumber(String employeeNumber) {
        List<Salary> salaries= null;
        List<Salary> all = repository.findAll();
        for (int i = 0; i < all.size(); i++) {
            if(all.get(i).getEmployeeNumber().equals(employeeNumber)){
                salaries.add(all.get(i));
            }
        }
        return salaries;
    }
}
