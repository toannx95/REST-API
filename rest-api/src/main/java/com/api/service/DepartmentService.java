package com.api.service;

import java.util.List;

import com.api.dto.DepartmentDto;
import com.api.dto.EmployeeDto;

public interface DepartmentService {

	List<DepartmentDto> getAllDepartments();

	DepartmentDto getDepartment(Integer departmentId);

	DepartmentDto createDepartment(DepartmentDto departmentDto);

	DepartmentDto updateDepartment(DepartmentDto departmentDto);

	void deleteDepartment(Integer departmentId);

	List<EmployeeDto> getAllEmployees(Integer departmentId);

	EmployeeDto getEmployee(Integer departmentId, Integer employeeId);

	EmployeeDto createEmployee(Integer departmentId, EmployeeDto employeeDto);

	EmployeeDto updateEmployee(Integer departmentId, EmployeeDto employeeDto);

	void deleteEmployee(Integer departmentId, Integer employeeId);

}
