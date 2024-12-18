package com.huyenho.demo.mapper;

import com.huyenho.demo.dto.department.DepartmentRequest;
import com.huyenho.demo.dto.department.DepartmentResponse;
import com.huyenho.demo.entity.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IDepartmentMapper {
    DepartmentResponse departmentToDepartmentResponse(Department department);
    Department departmentRequestToDepartment(DepartmentRequest departmentRequest);
}
