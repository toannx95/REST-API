package com.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.EmployeeDTO;
import com.api.entity.Employee;
import com.api.exception.BadRequestException;
import com.api.repository.DepartmentRepository;
import com.api.repository.EmployeeRepository;
import com.api.service.EmployeeService;
import com.api.util.NumberUtils;
import com.api.util.converter.DAOConverter;
import com.api.util.converter.DTOConverter;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public List<EmployeeDTO> getAllEmployees() throws BadRequestException {
		List<Employee> employees = employeeRepository.findAll();
		if (employees.isEmpty()) {
			return new ArrayList<>();
		}
		return employees.stream().map(employee -> DTOConverter.convertEmployee(employee)).collect(Collectors.toList());
	}

	@Override
	public EmployeeDTO getEmployee(Integer employeeId) throws BadRequestException {
		if (NumberUtils.isEmpty(employeeId)) {
			throw new BadRequestException("Unidentified employeeId");
		}

		Employee employee = employeeRepository.findOne(employeeId);
		if (Objects.isNull(employee)) {
			throw new BadRequestException("Employee with " + employeeId + " is no exists.");
		}
		return DTOConverter.convertEmployee(employee);
	}

	@Override
	public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) throws BadRequestException {
		return DTOConverter.convertEmployee(employeeRepository.save(DAOConverter.convertEmployee(employeeDTO)));
	}

	@Override
	public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) throws BadRequestException {
		Integer employeeId = employeeDTO.getEmployeeId();

		if (NumberUtils.isEmpty(employeeId)) {
			throw new BadRequestException("Unidentified employeeId");
		}

		Employee employee = employeeRepository.findOne(employeeId);
		if (Objects.isNull(employee)) {
			throw new BadRequestException("Employee with " + employeeId + " is no longer exists.");
		}

		employee.setId(employeeId);
		employee.setEmployeeName(employeeDTO.getEmployeeName());
		employee.setBirthday(employeeDTO.getBirthday());
		employee.setPhone(employeeDTO.getPhone());
		employee.setSex(employeeDTO.getSex());
		employee.setDepartment(
				departmentRepository.findOne(DAOConverter.convertDepartment(employeeDTO.getDepartment()).getId()));
		return DTOConverter.convertEmployee(employeeRepository.save(employee));
	}

	@Override
	public void deleteEmployee(Integer employeeId) throws BadRequestException {
		if (NumberUtils.isEmpty(employeeId)) {
			throw new BadRequestException("Unidentified employeeId");
		}

		Employee employee = employeeRepository.findOne(employeeId);
		if (Objects.isNull(employee)) {
			throw new BadRequestException("Employee with " + employeeId + " is no exists.");
		}
		employeeRepository.delete(employeeId);
	}

}
