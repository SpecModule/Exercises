package com.huyenho.demo.service.impl;

import com.huyenho.demo.dto.exception.AppException;
import com.huyenho.demo.dto.exception.ErrorCode;
import com.huyenho.demo.model.Department;
import com.huyenho.demo.repository.IDepartmentRepository;
import com.huyenho.demo.repository.IEmployeeRepository;
import com.huyenho.demo.repository.impl.DepartmentRepository;
import com.huyenho.demo.service.IDepartmentService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Iterator;
import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DepartmentService implements IDepartmentService {
    @Autowired
    IDepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.getAllDepartments();
    }

    public Department getDepartment(@PathVariable String id) {
        return departmentRepository.getDepartment(id);
    }

    public Department addDepartment(@RequestBody Department dp) {
        return departmentRepository.addDepartment(dp);
    }

    public Department updateDepartment(@PathVariable String id, @RequestBody Department updatedData) {
        return departmentRepository.updateDepartment(id, updatedData);
    }

    public Void deleteDepartment(@PathVariable String id) {
        return departmentRepository.deleteDepartment(id);
    }
}
