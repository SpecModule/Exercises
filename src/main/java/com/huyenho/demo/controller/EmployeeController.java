package com.huyenho.demo.controller;

import com.huyenho.demo.dto.ApiResponse;
import com.huyenho.demo.dto.Gender;
import com.huyenho.demo.dto.JsonResponse;
import com.huyenho.demo.dto.exception.AppException;
import com.huyenho.demo.dto.exception.ErrorCode;
import com.huyenho.demo.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/employee")
public class EmployeeController {
    private List<Employee> employee = new ArrayList<>(
            Arrays.asList(
                    new Employee(1, "Ho Thi Huyen", "25/06/2004", Gender.FEMALE.getGender(), 16000000),
                    new Employee(2, "Ho Van Hai", "09/03/2000", Gender.MALE.getGender(), 20000000)
            )
    );

    @GetMapping("")
    public ResponseEntity<?> getAllEmployees() {
        return JsonResponse.ok(employee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable String id) {
        for (Employee emp : employee) {
            if (emp.getId() == Integer.parseInt(id)) {
                return JsonResponse.ok(emp);
            }
        }
        throw new AppException(ErrorCode.EMPLOYEE_NOT_EXIST);
    }

    @PostMapping("")
    public ResponseEntity<?> addEmployee(@RequestBody Employee emp) {
        emp.setId((int) (Math.random() * 100000));
        employee.add(emp);
        return JsonResponse.created(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> updateEmployee(@PathVariable String id, @RequestBody Employee updatedData) {
        Employee existingEmployee = employee.stream()
                .filter(emp -> emp.getId() == Integer.parseInt(id))
                .findFirst()
                .orElse(null);

        if (existingEmployee == null) {
            throw new AppException(ErrorCode.EMPLOYEE_NOT_EXIST);
        }

        existingEmployee.setName(updatedData.getName());
        existingEmployee.setBirthday(updatedData.getBirthday());
        existingEmployee.setGender(updatedData.getGender());
        existingEmployee.setSalary(updatedData.getSalary());

        return JsonResponse.ok(existingEmployee);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String id) {
        for (Iterator<Employee> iterator = employee.iterator(); iterator.hasNext();) {
            Employee emp = iterator.next();
            if (emp.getId() == Integer.parseInt(id)) {
                iterator.remove();
                return JsonResponse.noContent();
            }
        }
        throw new AppException(ErrorCode.EMPLOYEE_NOT_EXIST);
    }
}
