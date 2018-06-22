package com.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.EmployeeDto;
import com.api.entity.Employee;
import com.api.exception.BadRequestException;
import com.api.exception.NotFoundException;
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
	public List<EmployeeDto> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		if (employees.isEmpty()) {
			return new ArrayList<>();
		}
		return employees.stream().map(employee -> DTOConverter.convertEmployee(employee)).collect(Collectors.toList());
	}

	@Override
	public EmployeeDto getEmployee(Integer employeeId) {
		if (NumberUtils.isEmpty(employeeId)) {
			throw new BadRequestException("Unidentified employeeId!");
		}

		Employee employee = employeeRepository.findOne(employeeId);
		if (Objects.isNull(employee)) {
			throw new NotFoundException("Employee", "employeeId", employeeId);
		}
		return DTOConverter.convertEmployee(employee);
	}

	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		return DTOConverter.convertEmployee(employeeRepository.save(DAOConverter.convertEmployee(employeeDto)));
	}

	@Override
	public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
		Integer employeeId = employeeDto.getEmployeeId();

		if (NumberUtils.isEmpty(employeeId)) {
			throw new BadRequestException("Unidentified employeeId!");
		}

		Employee employee = employeeRepository.findOne(employeeId);
		if (Objects.isNull(employee)) {
			throw new NotFoundException("Employee", "employeeId", employeeId);
		}

		employee.setId(employeeId);
		employee.setEmployeeName(employeeDto.getEmployeeName());
		employee.setBirthday(employeeDto.getBirthday());
		employee.setPhone(employeeDto.getPhone());
		employee.setSex(employeeDto.getSex());
		employee.setDepartment(
				departmentRepository.findOne(DAOConverter.convertDepartment(employeeDto.getDepartment()).getId()));
		return DTOConverter.convertEmployee(employeeRepository.save(employee));
	}

	@Override
	public void deleteEmployee(Integer employeeId) {
		if (NumberUtils.isEmpty(employeeId)) {
			throw new BadRequestException("Unidentified employeeId!");
		}

		Employee employee = employeeRepository.findOne(employeeId);
		if (Objects.isNull(employee)) {
			throw new NotFoundException("Employee", "employeeId", employeeId);
		}
		employeeRepository.delete(employeeId);
	}

}
