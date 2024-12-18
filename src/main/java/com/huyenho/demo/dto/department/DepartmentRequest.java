package com.huyenho.demo.dto.department;

import com.huyenho.demo.dto.clazz.ClazzRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DepartmentRequest {
    int id;
    String name;
}
