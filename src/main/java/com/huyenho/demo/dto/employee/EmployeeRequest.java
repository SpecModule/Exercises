package com.huyenho.demo.dto.employee;

import com.huyenho.demo.dto.department.DepartmentRequest;
import com.huyenho.demo.entity.Department;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeRequest {
    String name;
    LocalDate birthday;
    String gender;
    String phone;
    double salary;

    DepartmentRequest department;
}
