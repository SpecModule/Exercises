package com.huyenho.demo.service;

import com.huyenho.demo.dto.CustomPage;
import com.huyenho.demo.dto.EmployeeSearchRequest;
import com.huyenho.demo.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    Page<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest, Pageable pageable);
    Employee updateEmployee(int id, Employee updatedData);
    void deleteEmployee(int id);
    Optional<Employee> getEmployee(int id);
    List<Employee> addEmployee(Employee emp);
}