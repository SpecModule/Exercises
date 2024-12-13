package com.huyenho.demo.controller;

import com.huyenho.demo.dto.Gender;
import com.huyenho.demo.model.Employee;
import com.huyenho.demo.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/")
    public ResponseEntity<Employee> getEmployee(@RequestParam int id) {
        Employee emp = employee.get(id);
        return emp != null ?
                ResponseEntity.ok(emp)
                : ResponseEntity.notFound().build();
    }
}
