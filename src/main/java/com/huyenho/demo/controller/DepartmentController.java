package com.huyenho.demo.controller;

import com.huyenho.demo.dto.ApiResponse;
import com.huyenho.demo.dto.Gender;
import com.huyenho.demo.dto.JsonResponse;
import com.huyenho.demo.dto.exception.AppException;
import com.huyenho.demo.dto.exception.ErrorCode;
import com.huyenho.demo.model.Department;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/department")
public class DepartmentController {
    private List<Department> departments = new ArrayList<>(
            Arrays.asList(
                    new Department(1, "Nhân sự"),
                    new Department(2, "Kỹ thuật"),
                    new Department(3, "Kinh doanh")
            )
    );

    @GetMapping("")
    public ResponseEntity<?> getAllDepartments() {
        return JsonResponse.ok(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartment(@PathVariable String id) {
        for (Department dp : departments) {
            if (dp.getId() == Integer.parseInt(id)) {
                return JsonResponse.ok(dp);
            }
        }
        throw new AppException(ErrorCode.EMPLOYEE_NOT_EXIST);
    }

    @PostMapping("")
    public ResponseEntity<?> addDepartment(@RequestBody Department dp) {
        dp.setId((int) (Math.random() * 100000));
        departments.add(dp);
        return JsonResponse.created(dp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Department>> updateEmployee(@PathVariable String id, @RequestBody Department updatedData) {
        Department existingDepartment = departments.stream()
                .filter(dp -> dp.getId() == Integer.parseInt(id))
                .findFirst()
                .orElse(null);

        if (existingDepartment == null) {
            throw new AppException(ErrorCode.DEPARTMENT_NOT_EXIST);
        }

        existingDepartment.setName(updatedData.getName());

        return JsonResponse.ok(existingDepartment);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable String id) {
        for (Iterator<Department> iterator = departments.iterator(); iterator.hasNext();) {
            Department dp = iterator.next();
            if (dp.getId() == Integer.parseInt(id)) {
                iterator.remove();
                return JsonResponse.noContent();
            }
        }
        throw new AppException(ErrorCode.DEPARTMENT_NOT_EXIST);
    }
}
