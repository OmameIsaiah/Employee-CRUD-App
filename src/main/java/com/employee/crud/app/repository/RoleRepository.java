package com.employee.crud.app.repository;

import com.employee.crud.app.model.User;
import com.employee.crud.app.model.roles.UserEnum;
import com.employee.crud.app.model.roles.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<UserRole, Long> {

    Optional<UserRole> findByName(UserEnum userEnum);
}
