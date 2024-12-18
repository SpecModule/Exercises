package com.huyenho.demo.dto.employee;

import com.huyenho.demo.dto.department.DepartmentResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class EmployeeResponse {
    int id;
    String name;
    String gender;

    DepartmentResponse department;
}
