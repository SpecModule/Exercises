package com.huyenho.demo.controller;

import com.huyenho.demo.dto.ApiResponse;
import com.huyenho.demo.dto.CustomPage;
import com.huyenho.demo.dto.EmployeeSearchRequest;
import com.huyenho.demo.dto.JsonResponse;
import com.huyenho.demo.dto.employee.EmployeeRequest;
import com.huyenho.demo.dto.employee.EmployeeResponse;
import com.huyenho.demo.dto.exception.AppException;
import com.huyenho.demo.dto.exception.ErrorCode;
import com.huyenho.demo.entity.Employee;
import com.huyenho.demo.mapper.IEmployeeMapper;
import com.huyenho.demo.service.IEmployeeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/employee")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeController {
    IEmployeeService employeeService;
    IEmployeeMapper employeeMapper;

    @GetMapping("")
    public ResponseEntity<?> getAllEmployees(EmployeeSearchRequest employeeSearchRequest,
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Employee> employees = employeeService.findByAttributes(employeeSearchRequest, pageable);

        Page<EmployeeResponse> employeeResponses = employees.map(employeeMapper::employeeToEmployeeResponse);
        
        return ResponseEntity.ok(employeeResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable int id) {
        Optional<Employee> empl = employeeService.getEmployee(id);
        if (empl.isEmpty()) {
            throw new AppException(ErrorCode.EMPLOYEE_NOT_EXIST);
        }
        
        EmployeeResponse employeeResponse = employeeMapper.employeeToEmployeeResponse(empl.get());
        
        return JsonResponse.ok(employeeResponse);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<EmployeeResponse>> addEmployee(@RequestBody EmployeeRequest employee) {
        Employee emp = employeeMapper.employeeRequestToEmployee(employee);
        
        employeeService.addEmployee(emp);
        
        EmployeeResponse employeeResponse = employeeMapper.employeeToEmployeeResponse(emp);
        
        return JsonResponse.created(employeeResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponse>> updateEmployee(@PathVariable int id, @RequestBody EmployeeRequest updatedData) {
        Employee emp = employeeMapper.employeeRequestToEmployee(updatedData);

        Employee updatedEmployee = employeeService.updateEmployee(id, emp);

        if (updatedEmployee == null) {
            throw new AppException(ErrorCode.EMPLOYEE_NOT_EXIST);
        }

        EmployeeResponse employeeResponse = employeeMapper.employeeToEmployeeResponse(updatedEmployee);

        return JsonResponse.ok(employeeResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return JsonResponse.noContent();
    }
}
