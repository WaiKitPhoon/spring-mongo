package com.dev.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("employee")
public class Employee implements Serializable {

    @Id
    @NotBlank
    private String employeeId;
    private String firstName;
    private String lastName;
}
