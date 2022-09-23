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
@Table(name = "salary")
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "employee_id", length = 10, unique = true)
    @Size(min = 4)
    private String employeeNumber;
    @Column(name = "basic_salary")
    private String basicSalary;
    private String allowances;
    @Column(name = "pension_savings")
    private String pensionSavings;
    private String tax;
    @Column(name = "health_insurance")
    private String healthInsurance;
    @Column(name = "cooperative_deduction")
    private String cooperativeDeduction;
    @Column(name = "other_deductions")
    private String otherDeductions;
    @Column(name = "gross_pay")
    private String grossPay;
    @Column(name = "net_pay")
    private String netPay;
    private String status;
    private String remark;

    public Salary() {
    }

    public Salary(Long id, String employeeNumber, String basicSalary, String allowances, String pensionSavings, String tax, String healthInsurance, String cooperativeDeduction, String otherDeductions, String grossPay, String netPay, String status, String remark) {
        this.id = id;
        this.employeeNumber = employeeNumber;
        this.basicSalary = basicSalary;
        this.allowances = allowances;
        this.pensionSavings = pensionSavings;
        this.tax = tax;
        this.healthInsurance = healthInsurance;
        this.cooperativeDeduction = cooperativeDeduction;
        this.otherDeductions = otherDeductions;
        this.grossPay = grossPay;
        this.netPay = netPay;
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

    public String getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(String basicSalary) {
        this.basicSalary = basicSalary;
    }

    public String getAllowances() {
        return allowances;
    }

    public void setAllowances(String allowances) {
        this.allowances = allowances;
    }

    public String getPensionSavings() {
        return pensionSavings;
    }

    public void setPensionSavings(String pensionSavings) {
        this.pensionSavings = pensionSavings;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getHealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsurance(String healthInsurance) {
        this.healthInsurance = healthInsurance;
    }

    public String getCooperativeDeduction() {
        return cooperativeDeduction;
    }

    public void setCooperativeDeduction(String cooperativeDeduction) {
        this.cooperativeDeduction = cooperativeDeduction;
    }

    public String getOtherDeductions() {
        return otherDeductions;
    }

    public void setOtherDeductions(String otherDeductions) {
        this.otherDeductions = otherDeductions;
    }

    public String getGrossPay() {
        return grossPay;
    }

    public void setGrossPay(String grossPay) {
        this.grossPay = grossPay;
    }

    public String getNetPay() {
        return netPay;
    }

    public void setNetPay(String netPay) {
        this.netPay = netPay;
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
