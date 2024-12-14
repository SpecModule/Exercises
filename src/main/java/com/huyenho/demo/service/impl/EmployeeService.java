package com.huyenho.demo.service.impl;

import com.huyenho.demo.model.Employee;
import com.huyenho.demo.repository.IEmployeeRepository;
import com.huyenho.demo.repository.impl.EmployeeRepository;
import com.huyenho.demo.service.IEmployeeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeService implements IEmployeeService {
    @Autowired
    IEmployeeRepository employeeRepository;

    public List<Employee> findByAttributes(String name, String dobFrom, String dobTo, String gender, String salaryRange, String phone, String departmentId) {
        return employeeRepository.findByAttributes(name, dobFrom, dobTo, gender, salaryRange, phone, departmentId);
    }

    public Void deleteEmployee(String id) {
        return employeeRepository.deleteEmployee(id);
    }

    public Employee updateEmployee(String id, Employee updatedData){
        return employeeRepository.updateEmployee(id, updatedData);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public Employee getEmployee(String id) {
        return employeeRepository.getEmployee(id);
    }

    public List<Employee> addEmployee(Employee emp) {
        return employeeRepository.addEmployee(emp);
    }
}
