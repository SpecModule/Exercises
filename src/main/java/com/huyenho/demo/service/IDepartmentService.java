package com.huyenho.demo.service;

import com.huyenho.demo.emtity.Department;

import java.util.List;
import java.util.Optional;

public interface IDepartmentService {
    List<Department> getAllDepartments();

    Optional<Department> getDepartment(int id);

    Department addDepartment(Department dp);

    Department updateDepartment(int id, Department updatedData);
    void deleteDepartment(int id);
}
