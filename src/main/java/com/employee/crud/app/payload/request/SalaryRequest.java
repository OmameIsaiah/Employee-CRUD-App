package com.employee.crud.app.payload.request;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class SalaryRequest implements Serializable {

    private double basicSalary;
    private double allowances;
    private double pensionSavings;
    private double tax;
    private double healthInsurance;
    private double cooperativeDeduction;
    private double otherDeductions;
    public SalaryRequest(){}

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public double getAllowances() {
        return allowances;
    }

    public void setAllowances(double allowances) {
        this.allowances = allowances;
    }

    public double getPensionSavings() {
        return pensionSavings;
    }

    public void setPensionSavings(double pensionSavings) {
        this.pensionSavings = pensionSavings;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getHealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsurance(double healthInsurance) {
        this.healthInsurance = healthInsurance;
    }

    public double getCooperativeDeduction() {
        return cooperativeDeduction;
    }

    public void setCooperativeDeduction(double cooperativeDeduction) {
        this.cooperativeDeduction = cooperativeDeduction;
    }

    public double getOtherDeductions() {
        return otherDeductions;
    }

    public void setOtherDeductions(double otherDeductions) {
        this.otherDeductions = otherDeductions;
    }
}
