package com.huyenho.demo.repository.impl;

import com.huyenho.demo.dto.ApiResponse;
import com.huyenho.demo.dto.JsonResponse;
import com.huyenho.demo.dto.exception.AppException;
import com.huyenho.demo.dto.exception.ErrorCode;
import com.huyenho.demo.model.Department;
import com.huyenho.demo.repository.IDepartmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Repository
public class DepartmentRepository implements IDepartmentRepository {
    private List<Department> departments = new ArrayList<>(
            Arrays.asList(
                    new Department(1, "Nhân sự"),
                    new Department(2, "Kỹ thuật"),
                    new Department(3, "Kinh doanh")
            )
    );

    public List<Department> getAllDepartments() {
        return departments;
    }

    public Department getDepartment(@PathVariable String id) {
        for (Department dp : departments) {
            if (dp.getId() == Integer.parseInt(id)) {
                return dp;
            }
        }
        return null;
    }

    public Department addDepartment(@RequestBody Department dp) {
        dp.setId((int) (Math.random() * 100000));
        departments.add(dp);
        return dp;
    }

    public Department updateDepartment(@PathVariable String id, @RequestBody Department updatedData) {
        Department existingDepartment = departments.stream()
                .filter(dp -> dp.getId() == Integer.parseInt(id))
                .findFirst()
                .orElse(null);

        existingDepartment.setName(updatedData.getName());

        return existingDepartment;
    }

    public Void deleteDepartment(@PathVariable String id) {
        for (Iterator<Department> iterator = departments.iterator(); iterator.hasNext();) {
            Department dp = iterator.next();
            if (dp.getId() == Integer.parseInt(id)) {
                iterator.remove();
            }
        }
        return null;
    }
}
