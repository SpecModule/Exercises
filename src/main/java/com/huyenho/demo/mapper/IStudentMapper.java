package com.huyenho.demo.mapper;

import com.huyenho.demo.dto.student.StudentRequest;
import com.huyenho.demo.dto.student.StudentResponse;
import com.huyenho.demo.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IStudentMapper {
    StudentResponse studentToStudentResponse(Student student);
    Student studentRequestToStudent(StudentRequest studentRequest);
}
