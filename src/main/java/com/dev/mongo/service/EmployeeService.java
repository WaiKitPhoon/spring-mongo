package com.dev.mongo.service;

import com.dev.mongo.api.model.EmployeeRequest;
import com.dev.mongo.model.Employee;
import com.dev.mongo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void addNewEmployee(EmployeeRequest request) {
        Employee employee = toEmployee(request);
        employeeRepository.save(employee);
    }

    public Employee searchEmployeeById(String employeeId) {
        return employeeRepository.findById(employeeId).orElse(new Employee());
    }

    public Employee updateEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employeeRepository.findById(employee.getEmployeeId()).orElse(new Employee());
    }

    public void deleteEmployeeById(String employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    private Employee toEmployee(EmployeeRequest request) {
        Employee employee = new Employee();

        employee.setEmployeeId(request.getEmployeeId());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());

        return employee;
    }
}
