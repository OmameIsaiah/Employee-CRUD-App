package com.employee.crud.app.model;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
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
@Entity
@Table(name = "employee", uniqueConstraints = {@UniqueConstraint(name = "EmployeeUniqueConstraint", columnNames = {"employee_number", "email", "phone_number"})})
public class Employee {

    public Employee(Long id, String employeeNumber, String surname, String firstName, String middleName, String gender, String phoneNumber, String email, String country, String state, String lga, String streetName, String houseNumber, String zipCode, String picture, String dateOfBirth, String maritalStatus, Set<WorkHistory> workHistory, Set<Salary> salary, String createdAt, String updatedAt, String status, String remark) {
        this.id = id;
        this.employeeNumber = employeeNumber;
        this.surname = surname;
        this.firstName = firstName;
        this.middleName = middleName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.country = country;
        this.state = state;
        this.lga = lga;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.picture = picture;
        this.dateOfBirth = dateOfBirth;
        this.maritalStatus = maritalStatus;
        this.workHistory = workHistory;
        this.salary = salary;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.remark = remark;
    }

    public Employee(){

    }
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 4)
    @Column(name = "employee_number", unique = true, length = 10)
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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "employee_work_history",
            joinColumns = @JoinColumn(name = "employee_number"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private Set<WorkHistory> workHistory= new HashSet<WorkHistory>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable( name = "employee_salary",
            joinColumns = @JoinColumn(name = "employee_number"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private Set<Salary> salary= new HashSet<Salary>();

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLga() {
        return lga;
    }

    public void setLga(String lga) {
        this.lga = lga;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Set<WorkHistory> getWorkHistory() {
        return workHistory;
    }

    public void setWorkHistory(Set<WorkHistory> workHistory) {
        this.workHistory = workHistory;
    }

    public Set<Salary> getSalary() {
        return salary;
    }

    public void setSalary(Set<Salary> salary) {
        this.salary = salary;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
