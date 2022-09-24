package com.employee.crud.app.model;


import lombok.*;
import org.hibernate.annotations.Type;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@TypeDef(name = "json", typeClass = JsonStringType.class)
@Table(name = "employee", uniqueConstraints = {@UniqueConstraint(name = "EmployeeUniqueConstraint", columnNames = {"employee_number", "email", "phone_number"})})
public class Employee {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 4)
    //@Column(name = "employee_number", unique = true, length = 10)
    @Column(name = "employee_number", length = 10)
    private String employeeNumber;

    @NotNull
    @Column(name = "surname")
    private String surname;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @NotNull
    @Column(name = "gender")
    private String gender;

    @NotNull
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    @Column(name = "email")
    @Email
    private String email;


    private String country;
    private String state;
    private String lga;
    @Column(name = "street_name")
    private String streetName;
    @Column(name = "house_number")
    private String houseNumber;
    @Column(name = "zip_code")
    private String zipCode;
    private String picture;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "marital_status")
    private String maritalStatus;

    /*
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "employee_work_history",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeid")
    private Set<WorkHistory> workHistory = new HashSet<WorkHistory>();


    /*
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "employee_salary",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeid")
    private Set<Salary> salary = new HashSet<Salary>();

    @CreatedDate
    @Column(name = "created_date")
    @DateTimeFormat
    private String createdAt;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    @DateTimeFormat
    private String updatedAt;
    private String status;
    private String remark;


}
