package com.huyenho.demo.dto.premises;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PremisesResponse {
    String id;
    String name;
    String address;
    String type;
    Double area;
    Double rentPrice;
    LocalDate startDate;
}
