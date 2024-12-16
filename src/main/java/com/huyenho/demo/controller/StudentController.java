package com.huyenho.demo.controller;

import com.huyenho.demo.dto.ApiResponse;
import com.huyenho.demo.dto.exception.AppException;
import com.huyenho.demo.dto.exception.ErrorCode;
import com.huyenho.demo.emtity.Student;
import com.huyenho.demo.service.IStudentService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentController {
    IStudentService studentService;

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

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student student) {
        Student stud = studentService.update(id, student);
        if (stud == null) {
            throw new AppException(ErrorCode.STUDENT_NOT_EXIST);
        }
        return ResponseEntity.ok(stud);
    }

    @GetMapping("/find")
    public ResponseEntity<List<Student>> findByName(@RequestParam(defaultValue = "") String name) {
        List<Student> students = studentService.findByNameContaining(name);
        if (students == null || students.isEmpty()) {
            throw new AppException(ErrorCode.STUDENT_NOT_EXIST);
        }
        return ResponseEntity.ok(students);
    }

    @GetMapping("/findByAttr")
    public ResponseEntity<List<Student>> findByAttr(@RequestParam(defaultValue = "") String name, double fromScore, double toScore) {
        List<Student> students = studentService.findByAttr(name, fromScore, toScore);
        if (students == null || students.isEmpty()) {
            throw new AppException(ErrorCode.STUDENT_NOT_EXIST);
        }
        return ResponseEntity.ok(students);
    }
}
