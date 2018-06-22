package com.api.service;

import java.util.List;

import com.api.dto.EmployeeDto;

public interface EmployeeService {

	List<EmployeeDto> getAllEmployees();

	EmployeeDto getEmployee(Integer employeeId);

	EmployeeDto createEmployee(EmployeeDto employeeDto);

	EmployeeDto updateEmployee(EmployeeDto employeeDto);

	void deleteEmployee(Integer employeeId);

}
