package com.huyenho.demo.controller;

import com.huyenho.demo.dto.ApiResponse;
import com.huyenho.demo.dto.exception.AppException;
import com.huyenho.demo.dto.exception.ErrorCode;
import com.huyenho.demo.model.Student;
import com.huyenho.demo.service.IStudentService;
import com.huyenho.demo.service.impl.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private IStudentService studentService = new StudentService();

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getStudent(@PathVariable int id) {
        Student student = studentService.findById(id);
        if (student == null) {
            throw new AppException(ErrorCode.STUDENT_NOT_EXIST);
        }
        return ResponseEntity.ok(ApiResponse.<Student>builder().data(student).build());
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        studentService.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }
}
