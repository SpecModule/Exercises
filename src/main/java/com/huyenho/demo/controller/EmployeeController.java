package com.huyenho.demo.controller;

import com.huyenho.demo.dto.ApiResponse;
import com.huyenho.demo.dto.Gender;
import com.huyenho.demo.dto.JsonResponse;
import com.huyenho.demo.dto.exception.AppException;
import com.huyenho.demo.dto.exception.ErrorCode;
import com.huyenho.demo.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/employee")
public class EmployeeController {
    private List<Employee> employee = new ArrayList<>(
            Arrays.asList(
                    new Employee(1, "Ho Thi Huyen", LocalDate.of(2004, 3, 6), Gender.FEMALE.getGender(), "0354382937", 16000000, 1),
                    new Employee(2, "Ho Van Hai", LocalDate.of(2000, 9, 12), Gender.MALE.getGender(), "0354382937", 20000000, 2)
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
        existingEmployee.setPhone(updatedData.getPhone());
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

    @GetMapping("/search")
    public ResponseEntity<?> searchEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String dobFrom,
            @RequestParam(required = false) String dobTo,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String salaryRange,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String departmentId) {

        final Double salaryFrom;
        final Double salaryTo;

        if (salaryRange != null && salaryRange.matches("\\d+-\\d+")) {
            String[] range = salaryRange.split("-");
            salaryFrom = Double.parseDouble(range[0]);
            salaryTo = Double.parseDouble(range[1]);
        } else {
            salaryFrom = null;
            salaryTo = null;
        }

        final LocalDate parsedDobFrom = dobFrom != null ? LocalDate.parse(dobFrom) : null;
        final LocalDate parsedDobTo = dobTo != null ? LocalDate.parse(dobTo) : null;

        final Integer parsedDepartmentId = departmentId != null ? Integer.parseInt(departmentId) : null;

        List<Employee> filteredEmployees = employee.stream()
                .filter(emp -> name == null || emp.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(emp -> parsedDobFrom == null || !emp.getBirthday().isBefore(parsedDobFrom))
                .filter(emp -> parsedDobTo == null || !emp.getBirthday().isAfter(parsedDobTo))
                .filter(emp -> gender == null || emp.getGender().equalsIgnoreCase(gender))
                .filter(emp -> salaryFrom == null || emp.getSalary() >= salaryFrom)
                .filter(emp -> salaryTo == null || emp.getSalary() <= salaryTo)
                .filter(emp -> phone == null || emp.getPhone().contains(phone))
                .filter(emp -> parsedDepartmentId == null || emp.getDepartmentId().equals(parsedDepartmentId))
                .toList();

        if (filteredEmployees.isEmpty()) {
            throw new AppException(ErrorCode.EMPLOYEE_NOT_EXIST);
        }

        return JsonResponse.ok(filteredEmployees);
    }
}
