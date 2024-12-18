package com.huyenho.demo.mapper;

import com.huyenho.demo.dto.employee.EmployeeRequest;
import com.huyenho.demo.dto.employee.EmployeeResponse;
import com.huyenho.demo.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IEmployeeMapper {
    EmployeeResponse employeeToEmployeeResponse(Employee employee);
    Employee employeeRequestToEmployee(EmployeeRequest employeeRequest);
}
