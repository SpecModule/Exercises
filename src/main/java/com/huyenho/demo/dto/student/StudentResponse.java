package com.huyenho.demo.dto.student;

import com.huyenho.demo.dto.clazz.ClazzResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentResponse {
    int id;
    String name;
    double score;

    ClazzResponse clazz;
}
