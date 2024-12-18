package com.huyenho.demo.controller;

import com.huyenho.demo.dto.ApiResponse;
import com.huyenho.demo.dto.JsonResponse;
import com.huyenho.demo.dto.department.DepartmentRequest;
import com.huyenho.demo.dto.department.DepartmentResponse;
import com.huyenho.demo.dto.exception.AppException;
import com.huyenho.demo.dto.exception.ErrorCode;
import com.huyenho.demo.entity.Department;
import com.huyenho.demo.mapper.IDepartmentMapper;
import com.huyenho.demo.service.IDepartmentService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/department")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DepartmentController {
    IDepartmentService departmentService;
    IDepartmentMapper departmentMapper;

    @GetMapping("")
    public ResponseEntity<?> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        List<DepartmentResponse> departmentResponses = departments
                .stream()
                .map(departmentMapper::departmentToDepartmentResponse)
                .collect(Collectors.toList());

        return JsonResponse.ok(departmentResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartment(@PathVariable int id) {
        Optional<Department> department = departmentService.getDepartment(id);

        if (department != null) {
            DepartmentResponse departmentResponse = departmentMapper.departmentToDepartmentResponse(department.get());
            return JsonResponse.ok(departmentResponse);
        } else {
            throw new AppException(ErrorCode.DEPARTMENT_NOT_EXIST);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> addDepartment(@Valid @RequestBody DepartmentRequest dp, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getAllErrors().forEach(error -> {
                String nameError = ((FieldError) error).getField();
                String messageError = error.getDefaultMessage();
                errors.put(nameError, messageError);
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        Department department = departmentMapper.departmentRequestToDepartment(dp);
        departmentService.addDepartment(department);
        DepartmentResponse departmentResponse = departmentMapper.departmentToDepartmentResponse(department);
        return JsonResponse.created(departmentResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable int id, @Valid @RequestBody DepartmentRequest updatedData, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getAllErrors().forEach(error -> {
                String nameError = ((FieldError) error).getField();
                String messageError = error.getDefaultMessage();
                errors.put(nameError, messageError);
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        Department department = departmentMapper.departmentRequestToDepartment(updatedData);

        Department dpm = departmentService.updateDepartment(id, department);

        DepartmentResponse departmentResponse = departmentMapper.departmentToDepartmentResponse(dpm);

        if (department != null) {
            return JsonResponse.ok(departmentResponse);
        } else {
            throw new AppException(ErrorCode.DEPARTMENT_NOT_EXIST);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable int id) {
        departmentService.deleteDepartment(id);
        return JsonResponse.noContent();
    }
}
