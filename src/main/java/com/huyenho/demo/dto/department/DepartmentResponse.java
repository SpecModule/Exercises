package com.huyenho.demo.dto.department;

import com.huyenho.demo.dto.clazz.ClazzResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DepartmentResponse {
    int id;
    String name;
}
