package com.employee.crud.app.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Builder
@Table(name = "work_history")
public class WorkHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "employee_id", length = 10, unique = true)
    @Size(min = 4)
    private String employeeNumber;
    private String organisation;
    private String level;
    private String step;
    private String position;
    private String department;
    private String qualification;
    private String experience;
    @Column(name = "work_hours")
    private String workHours;
    @Column(name = "over_time")
    private String overTime;
    @Column(name = "extra_days")
    private String extraDays;
    @Column(name = "start_date")
    private String startDate;
    @Column(name = "end_date")
    private String endDate;
    private String status;
    private String remark;

    public WorkHistory() {
    }

    public WorkHistory(Long id, String employeeNumber, String organisation, String level, String step, String position, String department, String qualification, String experience, String workHours, String overTime, String extraDays, String startDate, String endDate, String status, String remark) {
        this.id = id;
        this.employeeNumber = employeeNumber;
        this.organisation = organisation;
        this.level = level;
        this.step = step;
        this.position = position;
        this.department = department;
        this.qualification = qualification;
        this.experience = experience;
        this.workHours = workHours;
        this.overTime = overTime;
        this.extraDays = extraDays;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.remark = remark;
    }

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

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getWorkHours() {
        return workHours;
    }

    public void setWorkHours(String workHours) {
        this.workHours = workHours;
    }

    public String getOverTime() {
        return overTime;
    }

    public void setOverTime(String overTime) {
        this.overTime = overTime;
    }

    public String getExtraDays() {
        return extraDays;
    }

    public void setExtraDays(String extraDays) {
        this.extraDays = extraDays;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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
