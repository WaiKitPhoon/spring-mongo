package com.dev.mongo.api.model;

import lombok.Data;

@Data
public class EmployeeRequest {

    private String employeeId;
    private String firstName;
    private String lastName;
}
