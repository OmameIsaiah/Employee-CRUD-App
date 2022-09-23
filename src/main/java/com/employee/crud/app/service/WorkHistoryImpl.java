package com.employee.crud.app.service;

import com.employee.crud.app.model.User;
import com.employee.crud.app.model.WorkHistory;
import com.employee.crud.app.repository.WorkHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkHistoryImpl implements WorkHistoryService{

    @Autowired
    private WorkHistoryRepository repository;
    @Override
    public WorkHistory saveWorkHistory(WorkHistory workHistory) {
        return repository.save(workHistory);
    }

    @Override
    public WorkHistory updateWorkHistory(WorkHistory workHistory) {
        return repository.save(workHistory);
    }

    @Override
    public WorkHistory findWorkHistoryByID(Long id) {
        WorkHistory history= null;
        Optional<WorkHistory> workHistory= repository.findById(id);
        if(workHistory.isPresent()){
            history= workHistory.get();
        }
        return history;
    }

    @Override
    public void deleteWorkHistoryByID(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<WorkHistory> findAllWorkHistories() {
        return repository.findAll();
    }

    @Override
    public List<WorkHistory> findAllWorkHistoriesByEmployeeNumber(String employeeNumber) {
        List<WorkHistory> workHistory= null;
        List<WorkHistory> all = repository.findAll();
        for (int i = 0; i < all.size(); i++) {
            if(all.get(i).getEmployeeNumber().equals(employeeNumber)){
                workHistory.add(all.get(i));
            }
        }
        return workHistory;
    }
}
