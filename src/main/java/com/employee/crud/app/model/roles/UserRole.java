package com.employee.crud.app.model.roles;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private UserEnum name;

    public UserRole() {

    }
    public UserRole(UserEnum name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserEnum getName() {
        return name;
    }

    public void setName(UserEnum name) {
        this.name = name;
    }
}
