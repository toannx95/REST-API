package com.api.service;

import java.util.List;

import com.api.dto.DepartmentDTO;
import com.api.dto.EmployeeDTO;
import com.api.exception.BadRequestException;

public interface DepartmentService {

	List<DepartmentDTO> getAllDepartments() throws BadRequestException;

	DepartmentDTO getDepartment(Integer departmentId) throws BadRequestException;

	DepartmentDTO createDepartment(DepartmentDTO departmentDTO) throws BadRequestException;

	DepartmentDTO updateDepartment(DepartmentDTO departmentDTO) throws BadRequestException;

	void deleteDepartment(Integer departmentId) throws BadRequestException;

	List<EmployeeDTO> getAllEmployees(Integer departmentId) throws BadRequestException;

	EmployeeDTO getEmployee(Integer departmentId, Integer employeeId) throws BadRequestException;

	EmployeeDTO createEmployee(Integer departmentId, EmployeeDTO employeeDTO) throws BadRequestException;

	EmployeeDTO updateEmployee(Integer departmentId, EmployeeDTO employeeDTO) throws BadRequestException;

	void deleteEmployee(Integer departmentId, Integer employeeId) throws BadRequestException;

}
