package com.huyenho.demo.controller;

import com.huyenho.demo.dto.ApiResponse;
import com.huyenho.demo.dto.clazz.ClazzResponse;
import com.huyenho.demo.dto.exception.AppException;
import com.huyenho.demo.dto.exception.ErrorCode;
import com.huyenho.demo.dto.student.StudentRequest;
import com.huyenho.demo.dto.student.StudentResponse;
import com.huyenho.demo.entity.Clazz;
import com.huyenho.demo.entity.Student;
import com.huyenho.demo.mapper.IStudentMapper;
import com.huyenho.demo.service.IStudentService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentController {
    IStudentService studentService;
    IStudentMapper studentMapper;

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getStudents(@RequestParam(defaultValue = "") String name,
                                                             Double fromScore,
                                                             Double toScore) {
        return ResponseEntity.ok(studentService.findAll(name, fromScore, toScore)
                .stream().map(studentMapper::studentToStudentResponse)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> getStudent(@PathVariable int id) {
        Student student = studentService.findById(id);
        if (student == null) {
            throw new AppException(ErrorCode.STUDENT_NOT_EXIST);
        }

        StudentResponse studentResponse = studentMapper.studentToStudentResponse(student);

        return ResponseEntity.ok(ApiResponse.<StudentResponse>builder().data(studentResponse).build());
    }

    @PostMapping
    public ResponseEntity<?> addStudent(@Valid @RequestBody StudentRequest student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getAllErrors().forEach(error -> {
                String nameError = ((FieldError) error).getField();
                String messageError = error.getDefaultMessage();
                errors.put(nameError, messageError);
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        //Bước 1: chuyển từ DTO -> entity
        Student stud = studentMapper.studentRequestToStudent(student);

        //Bước 2: thực hiện chức năng
        studentService.save(stud);

        //Bước 3: chuyển từ entity -> DTO
        StudentResponse studentResponse = studentMapper.studentToStudentResponse(stud);

        //trả về kết quả
        return ResponseEntity.status(HttpStatus.CREATED).body(studentResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable int id, @Valid @RequestBody StudentRequest student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getAllErrors().forEach(error -> {
                String nameError = ((FieldError) error).getField();
                String messageError = error.getDefaultMessage();
                errors.put(nameError, messageError);
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        Student stud = studentMapper.studentRequestToStudent(student);

        Student stu = studentService.update(id, stud);
        if (stu == null) {
            throw new AppException(ErrorCode.STUDENT_NOT_EXIST);
        }

        StudentResponse studentResponse = studentMapper.studentToStudentResponse(stu);

        return ResponseEntity.ok(studentResponse);
    }

    @GetMapping("/find")
    public ResponseEntity<List<StudentResponse>> findByName(@RequestParam(defaultValue = "") String name) {
        List<Student> students = studentService.findByNameContaining(name);
        if (students == null || students.isEmpty()) {
            throw new AppException(ErrorCode.STUDENT_NOT_EXIST);
        }
        return ResponseEntity.ok(students
                .stream().map(studentMapper::studentToStudentResponse)
                .collect(Collectors.toList()));
    }
}
