package com.dev.mongo.controller;

import com.dev.mongo.api.model.EmployeeRequest;
import com.dev.mongo.api.model.EmployeeResponse;
import com.dev.mongo.model.Employee;
import com.dev.mongo.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public ResponseEntity<String> addEmployee(@RequestBody EmployeeRequest employee) {
        employeeService.addNewEmployee(employee);
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/employee/{userId}")
    public ResponseEntity<EmployeeResponse> findEmployee(@PathVariable String employeeId) {
        Employee employee = employeeService.searchEmployeeById(employeeId);
        EmployeeResponse response = toResponse(employee);
        return ResponseEntity.ok(response);
    }

    private EmployeeResponse toResponse(Employee employee) {
        EmployeeResponse response = new EmployeeResponse();
        response.setEmployeeId(employee.getEmployeeId());
        response.setFirstName(employee.getFirstName());
        response.setLastName(employee.getLastName());
        return response;
    }
}
