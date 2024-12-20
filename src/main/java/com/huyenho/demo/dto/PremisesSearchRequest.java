package com.huyenho.demo.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PremisesSearchRequest {
    String id;
    String name;
    String address;
    String type;
    Double areaFrom;
    Double areaTo;
    String rentPriceRange;
    LocalDate fromDate;
    LocalDate toDate;
}
