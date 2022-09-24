package com.employee.crud.app.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "salary")
@XmlRootElement
@Setter
@Getter
public class Salary implements Serializable {
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
    @JoinColumn(name = "employeeid", referencedColumnName = "id")
    @ManyToOne
    private Employee employeeid;


}
