package com.huyenho.demo.dto.premises;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PremisesRequest {
    @NotBlank(message = "ID must be provided")
    @Pattern(regexp = "MB\\d{3}", message = "ID must follow the format MBXXX, where XXX are digits (0-9)")
    private String id;

    @NotBlank(message = "Name must be provided")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @NotBlank(message = "Address must be provided")
    @Size(min = 3, message = "Address must be at least 3 characters long")
    private String address;

    @NotBlank(message = "Type must be provided")
    @Size(min = 3, message = "Type must be at least 3 characters long")
    private String type;

    @NotNull(message = "Area must be provided")
    @DecimalMin(value = "10.0", inclusive = false, message = "Area must be greater than 10 m²")
    private Double area;

    @NotNull(message = "Rent price must be provided")
    @DecimalMin(value = "500000", inclusive = false, message = "Rent price must be greater than 500,000 VND")
    private Double rentPrice;

    @NotNull(message = "Start date must be provided")
    @FutureOrPresent(message = "Start date must be today or a future date")
    private LocalDate startDate;

    @NotBlank(message = "Description must be provided")
    @Size(max = 500, message = "Description must be less than or equal to 500 characters")
    private String description;
}
