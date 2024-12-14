package com.huyenho.demo.model;

import com.huyenho.demo.dto.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.time.LocalDate;
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
    private LocalDate birthday;
    private String gender;
    private String phone;
    private double salary;
    private Integer departmentId;
}
