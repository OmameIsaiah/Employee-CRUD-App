package com.employee.crud.app.service;

import com.employee.crud.app.model.WorkHistory;

import java.util.List;

public interface WorkHistoryService {
    WorkHistory saveWorkHistory(WorkHistory workHistory);
    WorkHistory updateWorkHistory(WorkHistory workHistory);
    WorkHistory findWorkHistoryByID(Long id);
    void deleteWorkHistoryByID(Long id);
    List<WorkHistory> findAllWorkHistories();
    List<WorkHistory> findAllWorkHistoriesByEmployeeNumber(String employeeNumber);

}
