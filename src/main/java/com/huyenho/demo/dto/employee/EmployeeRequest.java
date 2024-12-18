package com.huyenho.demo.dto.employee;

import com.huyenho.demo.dto.department.DepartmentRequest;
import com.huyenho.demo.entity.Department;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeRequest {
    @NotBlank(message = "Name must be provided")
    @Pattern(regexp = "[A-Za-zÀ-ỹ\\s]+", message = "Name is invalid (only letters and spaces are allowed)")
    @Size(min = 3, message = "Name must be more than 3 characters")
    String name;

    @NotNull(message = "Birthday cannot be null")
    @Past(message = "Birthday must be in the past")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate birthday;

    @NotBlank(message = "Gender must be provided")
    @Pattern(regexp = "[A-Za-zÀ-ỹ]+", message = "Name is invalid (only letters is allowed)")
    String gender;

    @NotBlank(message = "Name must be provided")
    @Pattern(regexp = "[A-Za-zÀ-ỹ\\s]+", message = "Name is invalid (only letters and spaces are allowed)")
    @Size(min = 10, message = "Phone must be equal to 10")
    @Size(max = 10, message = "Phone must be equal to 10")
    String phone;

    @NotNull(message = "Salary cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than 0")
    double salary;

    @NotNull(message = "Department cannot be null")
    DepartmentRequest department;
}
