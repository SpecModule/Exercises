package com.huyenho.demo.repository;

import com.huyenho.demo.model.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IDepartmentRepository {
    List<Department> getAllDepartments();

    Department getDepartment(String id);

    Department addDepartment(Department dp);

    Department updateDepartment(String id, Department updatedData);
    Void deleteDepartment(String id);
}
