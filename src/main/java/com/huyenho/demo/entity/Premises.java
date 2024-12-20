package com.huyenho.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
public class Premises {
    @Id
    private String id;
    private String name;
    private String address;
    private String type;
    private Double area;
    private Double rentPrice;
    private LocalDate startDate;
    private String description;

}
