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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public ResponseEntity<?> addDepartment(@RequestBody DepartmentRequest dp) {
        Department department = departmentMapper.departmentRequestToDepartment(dp);
        departmentService.addDepartment(department);
        DepartmentResponse departmentResponse = departmentMapper.departmentToDepartmentResponse(department);
        return JsonResponse.created(departmentResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartmentResponse>> updateEmployee(@PathVariable int id, @RequestBody DepartmentRequest updatedData) {
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
