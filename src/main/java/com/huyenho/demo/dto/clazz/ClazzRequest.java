package com.huyenho.demo.dto.clazz;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClazzRequest {
    @NotNull(message = "ID cannot be null")
    @Positive(message = "ID must be a positive number")
    int id;

    @NotBlank(message = "Name must be provided")
    @Pattern(regexp = "[A-Za-zÀ-ỹ\\s]+", message = "Name is invalid (only letters and spaces are allowed)")
    @Size(min = 3, message = "Name must be more than 3 characters")
    String name;
}
