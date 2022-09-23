package com.employee.crud.app.controller;

import com.employee.crud.app.model.Employee;
import com.employee.crud.app.model.Salary;
import com.employee.crud.app.model.WorkHistory;
import com.employee.crud.app.payload.request.EmployeeRequest;
import com.employee.crud.app.payload.request.SalaryRequest;
import com.employee.crud.app.payload.request.WorkHistoryRequest;
import com.employee.crud.app.payload.response.EmployeeResponse;
import com.employee.crud.app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/save")
    public ResponseEntity<?> saveEmployee(@Valid @RequestBody EmployeeRequest request){

        //WorkHistoryRequest[] historyArray = (WorkHistoryRequest[]) request.getWorkHistory().stream().toArray();
        WorkHistoryRequest[] historyArray = request.getWorkHistory().toArray(new WorkHistoryRequest[request.getWorkHistory().size()]);
        WorkHistoryRequest workHistorySet = historyArray[0];
        WorkHistory workHistory= WorkHistory.builder()
                .employeeNumber(request.getEmployeeNumber())
                .organisation(workHistorySet.getOrganisation())
                .level(workHistorySet.getLevel())
                .step(workHistorySet.getStep())
                .position(workHistorySet.getPosition())
                .department(workHistorySet.getDepartment())
                .qualification(workHistorySet.getQualification())
                .experience(workHistorySet.getExperience())
                .department(workHistorySet.getDepartment())
                .workHours(workHistorySet.getWorkHours())
                .overTime(workHistorySet.getOverTime())
                .extraDays(workHistorySet.getExtraDays())
                .startDate(workHistorySet.getStartDate())
                .endDate(workHistorySet.getEndDate())
                .status("Good")
                .remark("")
                .build();
        Set<WorkHistory> historySet = new HashSet<>();
        historySet.add(workHistory);

        //SalaryRequest[] salaryRequests = (SalaryRequest[]) request.getSalary().stream().toArray();
        SalaryRequest[] salaryRequests = request.getSalary().toArray(new SalaryRequest[request.getSalary().size()]);
        SalaryRequest salaryRequestSet = salaryRequests[0];

        //SalaryRequest salaryRequestSet = request.getSalary();
        double basicSalary= salaryRequestSet.getBasicSalary();
        double allowances= salaryRequestSet.getAllowances();
        double pensionSavings= salaryRequestSet.getPensionSavings();
        double tax= salaryRequestSet.getTax();
        double healthInsurance= salaryRequestSet.getHealthInsurance();
        double cooperativeDeduction= salaryRequestSet.getCooperativeDeduction();
        double otherDeductions= salaryRequestSet.getOtherDeductions();
        double grossPay= basicSalary+allowances;
        double totalDeductions = pensionSavings+tax+healthInsurance+cooperativeDeduction+otherDeductions;
        double netPay= grossPay-totalDeductions;

        Salary salary = Salary.builder()
                .employeeNumber(request.getEmployeeNumber())
                .basicSalary(""+basicSalary+"")
                .allowances(""+allowances+"")
                .pensionSavings(""+pensionSavings+"")
                .tax(""+tax+"")
                .healthInsurance(""+healthInsurance+"")
                .cooperativeDeduction(""+cooperativeDeduction+"")
                .otherDeductions(""+otherDeductions+"")
                .grossPay(""+grossPay+"")
                .netPay(""+netPay+"")
                .status("Good!")
                .remark("")
                .build();
        Set<Salary> salarySet = new HashSet<>();
        salarySet.add(salary);

        Employee emp = Employee.builder()
                .employeeNumber(request.getEmployeeNumber())
                .surname(request.getSurname())
                .firstName(request.getFirstName())
                .middleName(request.getMiddleName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .gender(request.getGender())
                .picture(request.getPictureURL())
                .country(request.getCountry())
                .state(request.getState())
                .lga(request.getLga())
                .streetName(request.getStreetName())
                .houseNumber(request.getHouseNumber())
                .zipCode(request.getZipCode())
                .dateOfBirth(request.getDateOfBirth())
                .maritalStatus(request.getMaritalStatus())
                .workHistory(historySet)
                .salary(salarySet)
                .build();
        Employee employee= employeeService.saveEmployee(emp);
        EmployeeResponse response= new EmployeeResponse();
        response.setSuccess(true);
        response.setMessage(String.format("Saving employee %s %s",employee.getFirstName(),employee.getSurname()));
        response.setData(employee);

        return  ResponseEntity.ok().body(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findEmployeeByID(@PathVariable("id") Long id){
        Employee e = employeeService.findEmployeeByID(id);
        EmployeeResponse response= new EmployeeResponse();
        response.setSuccess(true);
        response.setMessage(String.format("Retrieving employee %s %s",e.getFirstName(),e.getSurname()));
        response.setData(e);

        return  ResponseEntity.ok().body(response);
    }
    @GetMapping("/employeeNumber/{employeeNumber}")
    public ResponseEntity<?> findByEmployeeNumber(@PathVariable("employeeNumber") String employeeNumber){
        Employee e = employeeService.findEmployeeByNumber(employeeNumber);
        EmployeeResponse response= new EmployeeResponse();
        response.setSuccess(true);
        response.setMessage(String.format("Retrieving employee %s %s",e.getFirstName(),e.getSurname()));
        response.setData(e);

        return  ResponseEntity.ok().body(response);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> findEmployeeByEmail(@PathVariable("email") String email){
        Employee e = employeeService.findEmployeeByEmail(email);
        EmployeeResponse response= new EmployeeResponse();
        response.setSuccess(true);
        response.setMessage(String.format("Retrieving employee %s %s",e.getFirstName(),e.getSurname()));
        response.setData(e);

        return  ResponseEntity.ok().body(response);
    }


    @GetMapping("/tel/{phoneNumber}")
    public ResponseEntity<?> findEmployeeByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber){
        Employee e = employeeService.findEmployeeByPhoneNumber(phoneNumber);
        EmployeeResponse response= new EmployeeResponse();
        response.setSuccess(true);
        response.setMessage(String.format("Retrieving employee %s %s",e.getFirstName(),e.getSurname()));
        response.setData(e);

        return  ResponseEntity.ok().body(response);
    }

    @GetMapping("/list")
    public List<Employee> findAllEmployee(){
        return employeeService.findAllEmployee();
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeByID(@PathVariable("id") Long id){
        employeeService.deleteEmployeeByID(id);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEmployee(@RequestBody @Valid EmployeeRequest request){
        Employee e = Employee.builder()
                .employeeNumber(request.getEmployeeNumber())
                .surname(request.getSurname())
                .firstName(request.getFirstName())
                .middleName(request.getMiddleName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .picture(request.getPictureURL())
                .country(request.getCountry())
                .state(request.getState())
                .lga(request.getLga())
                .streetName(request.getStreetName())
                .houseNumber(request.getHouseNumber())
                .zipCode(request.getZipCode())
                .dateOfBirth(request.getDateOfBirth())
                .maritalStatus(request.getMaritalStatus())
                .build();
        Employee employee= employeeService.update(e);
        EmployeeResponse response= new EmployeeResponse();
        response.setSuccess(true);
        response.setMessage(String.format("Updating employee %s %s",employee.getFirstName(),employee.getSurname()));
        response.setData(employee);

        return  ResponseEntity.ok().body(response);
    }
}
