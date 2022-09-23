package com.employee.crud.app.repository;

import com.employee.crud.app.model.WorkHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkHistoryRepository extends JpaRepository<WorkHistory, Long> {

}
