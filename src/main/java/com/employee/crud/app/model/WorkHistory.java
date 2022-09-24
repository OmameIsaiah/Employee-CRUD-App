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
@XmlRootElement
@Table(name = "work_history")
@Setter
@Getter
public class WorkHistory implements Serializable {

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
    @JoinColumn(name = "employeeid", referencedColumnName = "id")
    @ManyToOne
    private Employee employeeid;


}
