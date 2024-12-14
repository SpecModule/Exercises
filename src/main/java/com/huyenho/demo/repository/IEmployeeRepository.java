package com.huyenho.demo.repository;

import com.huyenho.demo.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IEmployeeRepository {
    List<Employee> findByAttributes(String name, String dobFrom, String dobTo, String gender, String salaryRange, String phone, String departmentId);
    Void deleteEmployee(String id);
    Employee updateEmployee(String id, Employee updatedData);
    List<Employee> getAllEmployees();
    Employee getEmployee(String id);
    List<Employee> addEmployee(Employee emp);
}
