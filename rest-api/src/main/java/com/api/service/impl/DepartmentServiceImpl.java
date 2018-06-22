package com.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.DepartmentDto;
import com.api.dto.EmployeeDto;
import com.api.entity.Department;
import com.api.entity.Employee;
import com.api.exception.BadRequestException;
import com.api.exception.NotFoundException;
import com.api.repository.DepartmentRepository;
import com.api.repository.EmployeeRepository;
import com.api.service.DepartmentService;
import com.api.util.NumberUtils;
import com.api.util.converter.DAOConverter;
import com.api.util.converter.DTOConverter;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<DepartmentDto> getAllDepartments() {
		List<Department> departments = departmentRepository.findAll();
		if (departments.isEmpty()) {
			return new ArrayList<>();
		}
		return departments.stream().map(department -> DTOConverter.convertDepartment(department))
				.collect(Collectors.toList());
	}

	@Override
	public DepartmentDto getDepartment(Integer departmentId) {
		if (NumberUtils.isEmpty(departmentId)) {
			throw new BadRequestException("Unidentified departmentId!");
		}

		Department department = departmentRepository.findOne(departmentId);
		if (Objects.isNull(department)) {
			throw new NotFoundException("Department", "departmentId", departmentId);
		}
		return DTOConverter.convertDepartment(department);
	}

	@Override
	public DepartmentDto createDepartment(DepartmentDto departmentDto) {
		return DTOConverter.convertDepartment(departmentRepository.save(DAOConverter.convertDepartment(departmentDto)));
	}

	@Override
	public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
		Integer departmentId = departmentDto.getDepartmentId();

		if (NumberUtils.isEmpty(departmentId)) {
			throw new BadRequestException("Unidentified departmentId!");
		}

		Department department = departmentRepository.findOne(departmentId);
		if (Objects.isNull(department)) {
			throw new NotFoundException("Department", "departmentId", departmentId);
		}

		department.setId(departmentDto.getDepartmentId());
		department.setDepartName(departmentDto.getDepartName());
		department.setDescription(departmentDto.getDescription());
		department.setEmail(departmentDto.getEmail());
		department.setCompany(DAOConverter.convertCompany(departmentDto.getCompany()));
		return DTOConverter.convertDepartment(departmentRepository.save(department));
	}

	@Override
	public void deleteDepartment(Integer departmentId) {
		if (NumberUtils.isEmpty(departmentId)) {
			throw new BadRequestException("Unidentified departmentId!");
		}

		Department department = departmentRepository.findOne(departmentId);
		if (Objects.isNull(department)) {
			throw new NotFoundException("Department", "departmentId", departmentId);
		}
		departmentRepository.delete(departmentId);
	}

	@Override
	public List<EmployeeDto> getAllEmployees(Integer departmentId) {
		List<Employee> employees = employeeRepository.findAllByDepartmentId(departmentId);
		if (employees.isEmpty()) {
			return new ArrayList<>();
		}
		return employees.stream().map(employee -> DTOConverter.convertEmployee(employee)).collect(Collectors.toList());
	}

	@Override
	public EmployeeDto getEmployee(Integer departmentId, Integer employeeId) {
		if (NumberUtils.isEmpty(departmentId)) {
			throw new BadRequestException("Unidentified departmentId!");
		}

		if (NumberUtils.isEmpty(employeeId)) {
			throw new BadRequestException("Unidentified employeeId!");
		}

		Department department = departmentRepository.findOne(departmentId);
		if (Objects.isNull(department)) {
			throw new NotFoundException("Department", "departmentId", departmentId);
		}

		Employee employee = employeeRepository.findOneByDepartmentId(departmentId, employeeId);
		if (Objects.isNull(employee)) {
			throw new NotFoundException("Employee", "employeeId", employeeId);
		}
		return DTOConverter.convertEmployee(employee);
	}

	@Override
	public EmployeeDto createEmployee(Integer departmentId, EmployeeDto employeeDto) {
		if (NumberUtils.isEmpty(departmentId)) {
			throw new BadRequestException("Unidentified departmentId!");
		}

		Department department = departmentRepository.findOne(departmentId);
		if (Objects.isNull(department)) {
			throw new NotFoundException("Department", "departmentId", departmentId);
		}

		employeeDto.setDepartment(DTOConverter.convertDepartment(department));
		return DTOConverter.convertEmployee(employeeRepository.save(DAOConverter.convertEmployee(employeeDto)));

	}

	@Override
	public EmployeeDto updateEmployee(Integer departmentId, EmployeeDto employeeDto) {
		if (NumberUtils.isEmpty(departmentId)) {
			throw new BadRequestException("Unidentified departmentId!");
		}

		Department department = departmentRepository.findOne(departmentId);
		if (Objects.isNull(department)) {
			throw new NotFoundException("Department", "departmentId", departmentId);
		}

		Integer employeeId = employeeDto.getEmployeeId();
		Employee employee = employeeRepository.findOne(employeeId);
		if (Objects.isNull(employee)) {
			throw new NotFoundException("Employee", "employeeId", employeeId);
		}

		employee.setEmployeeName(employeeDto.getEmployeeName());
		employee.setBirthday(employeeDto.getBirthday());
		employee.setSex(employeeDto.getSex());
		employee.setPhone(employeeDto.getPhone());
		employee.setProjects(employeeDto.getProjects().stream().map(project -> DAOConverter.convertProject(project))
				.collect(Collectors.toSet()));
		employee.setDepartment(DAOConverter.convertDepartment(employeeDto.getDepartment()));

		return DTOConverter.convertEmployee(employeeRepository.save(employee));
	}

	@Override
	public void deleteEmployee(Integer departmentId, Integer employeeId) {
		if (NumberUtils.isEmpty(departmentId)) {
			throw new BadRequestException("Unidentified departmentId!");
		}

		if (NumberUtils.isEmpty(employeeId)) {
			throw new BadRequestException("Unidentified employeeId!");
		}

		Department department = departmentRepository.findOne(departmentId);
		if (Objects.isNull(department)) {
			throw new NotFoundException("Department", "departmentId", departmentId);
		}

		Employee employee = employeeRepository.findOneByDepartmentId(departmentId, employeeId);
		if (Objects.isNull(employee)) {
			throw new NotFoundException("Employee", "employeeId", employeeId);
		}
		employeeRepository.delete(employee);
	}

}
