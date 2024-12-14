package com.huyenho.demo.model;

import com.huyenho.demo.dto.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    private int id;
    private String name;
    private String birthday;
    private String gender;
    private double salary;
}
