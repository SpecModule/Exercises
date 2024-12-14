package com.huyenho.demo.dto.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorCode {
    STUDENT_NOT_EXIST(40401, "Student is not exist", HttpStatus.NOT_FOUND),
    EMPLOYEE_NOT_EXIST(40402, "Employee is not exist", HttpStatus.NOT_FOUND),
    DEPARTMENT_NOT_EXIST(40403, "Department is not exist", HttpStatus.NOT_FOUND);


    Integer code;
    String message;
    HttpStatus httpStatus;
}
