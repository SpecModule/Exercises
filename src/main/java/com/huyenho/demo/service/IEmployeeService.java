package com.huyenho.demo.service;

import com.huyenho.demo.dto.EmployeeSearchRequest;
import com.huyenho.demo.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    List<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest);
    Employee updateEmployee(int id, Employee updatedData);
    void deleteEmployee(int id);
    Page<Employee> getAllEmployees(Pageable pageable);
    Optional<Employee> getEmployee(int id);
    List<Employee> addEmployee(Employee emp);
}