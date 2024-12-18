package com.huyenho.demo.dto.student;

import com.huyenho.demo.dto.clazz.ClazzRequest;
import com.huyenho.demo.entity.Clazz;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentRequest {
    @NotBlank(message = "Name must be provided")
    @Pattern(regexp = "[A-Za-zÀ-ỹ\\s]+", message = "Name is invalid (only letters and spaces are allowed)")
    @Size(min = 3, message = "Name must be more than 3 characters")
    String name;

    @NotNull(message = "Score cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Score must be greater than 0")
    @DecimalMax(value = "10.0", inclusive = true, message = "Score must be less than or equal to 10")
    Double score;

    @NotNull(message = "Class cannot be null")
    ClazzRequest clazz;
}