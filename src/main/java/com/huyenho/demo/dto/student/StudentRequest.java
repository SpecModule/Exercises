package com.huyenho.demo.dto.student;

import com.huyenho.demo.dto.clazz.ClazzRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentRequest {
    String name;
    double score;

    ClazzRequest clazz;
}
