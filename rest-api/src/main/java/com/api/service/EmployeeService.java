package com.api.service;

import java.util.List;

import com.api.dto.EmployeeDTO;
import com.api.exception.BadRequestException;

public interface EmployeeService {

	List<EmployeeDTO> getAllEmployees() throws BadRequestException;

	EmployeeDTO getEmployee(Integer employeeId) throws BadRequestException;

	EmployeeDTO createEmployee(EmployeeDTO employeeDTO) throws BadRequestException;

	EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) throws BadRequestException;

	void deleteEmployee(Integer employeeId) throws BadRequestException;

}
