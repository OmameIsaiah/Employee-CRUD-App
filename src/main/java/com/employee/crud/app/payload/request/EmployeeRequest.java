package com.employee.crud.app.payload.request;

import com.employee.crud.app.model.Salary;
import com.employee.crud.app.model.WorkHistory;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class EmployeeRequest implements Serializable {

    @NotBlank
    @Size(min = 4)
    private String employeeNumber;

    @NotBlank
    private String surname;

    @NotBlank
    private String firstName;

    private String middleName;

    @NotBlank
    private String gender;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    @Email
    private String email;

    private String country;

    private String state;

    private String lga;

    private String streetName;

    private String houseNumber;

    private String zipCode;

    private String pictureURL;

    private String dateOfBirth;

    private String maritalStatus;

    private Set<WorkHistoryRequest> workHistory = new HashSet<WorkHistoryRequest>();

    private Set<SalaryRequest> salary  = new HashSet<SalaryRequest>();
    public EmployeeRequest(){}

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

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
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

    public Set<WorkHistoryRequest> getWorkHistory() {
        return workHistory;
    }

    public void setWorkHistory(Set<WorkHistoryRequest> workHistory) {
        this.workHistory = workHistory;
    }

    public Set<SalaryRequest> getSalary() {
        return salary;
    }

    public void setSalary(Set<SalaryRequest> salary) {
        this.salary = salary;
    }
}
