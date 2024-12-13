package com.huyenho.demo.controller;

import com.huyenho.demo.dto.Gender;
import com.huyenho.demo.model.Employee;
import com.huyenho.demo.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable String id) {
        for (Employee emp : employee) {
            if (emp.getId() == Integer.parseInt(id)) {
                return ResponseEntity.ok(emp);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @RequestBody Employee updatedData) {
        Employee existingEmployee = employee.stream()
                .filter(emp -> emp.getId() == Integer.parseInt(id))
                .findFirst()
                .orElse(null);

        if (existingEmployee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        existingEmployee.setName(updatedData.getName());
        existingEmployee.setBirthday(updatedData.getBirthday());
        existingEmployee.setGender(updatedData.getGender());
        existingEmployee.setSalary(updatedData.getSalary());

        return ResponseEntity.ok(existingEmployee);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String id) {
        for (Iterator<Employee> iterator = employee.iterator(); iterator.hasNext();) {
            Employee emp = iterator.next();
            if (emp.getId() == Integer.parseInt(id)) {
                iterator.remove();
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
