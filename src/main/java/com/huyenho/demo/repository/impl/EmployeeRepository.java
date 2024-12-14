package com.huyenho.demo.repository.impl;

import com.huyenho.demo.dto.Gender;
import com.huyenho.demo.model.Employee;
import com.huyenho.demo.repository.IEmployeeRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Repository
public class EmployeeRepository implements IEmployeeRepository {
    private List<Employee> employee = new ArrayList<>(
            Arrays.asList(
                    new Employee(1, "Ho Thi Huyen", LocalDate.of(2004, 3, 6), Gender.FEMALE.getGender(), "0354382937", 16000000, 1),
                    new Employee(2, "Ho Van Hai", LocalDate.of(2000, 9, 12), Gender.MALE.getGender(), "0354382937", 20000000, 2)
            )
    );

    public List<Employee> findByAttributes(String name, String dobFrom, String dobTo, String gender, String salaryRange, String phone, String departmentId) {
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

        return filteredEmployees;
    }

    public List<Employee> getAllEmployees() {
        return employee;
    }

    public Employee getEmployee(String id) {
        Employee empl = null;
        for (Employee emp : employee) {
            if (emp.getId() == Integer.parseInt(id)) {
                empl = emp;
            }
        }
        return empl;
    }

    public List<Employee> addEmployee(Employee emp) {
        emp.setId((int) (Math.random() * 100000));
        employee.add(emp);
        return employee;
    }

    public Employee updateEmployee(String id, Employee updatedData)  {
        Employee existingEmployee = employee.stream()
                .filter(emp -> emp.getId() == Integer.parseInt(id))
                .findFirst()
                .orElse(null);

        existingEmployee.setName(updatedData.getName());
        existingEmployee.setBirthday(updatedData.getBirthday());
        existingEmployee.setGender(updatedData.getGender());
        existingEmployee.setPhone(updatedData.getPhone());
        existingEmployee.setSalary(updatedData.getSalary());

        return existingEmployee;
    }

    public Void deleteEmployee(String id) {
        for (Iterator<Employee> iterator = employee.iterator(); iterator.hasNext();) {
            Employee emp = iterator.next();
            if (emp.getId() == Integer.parseInt(id)) {
                iterator.remove();
            }
        }
        return null;
    }
}
