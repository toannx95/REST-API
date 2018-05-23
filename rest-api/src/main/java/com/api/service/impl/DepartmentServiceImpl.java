package com.api.service.impl;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

import com.api.dto.DepartmentDTO;
import com.api.dto.EmployeeDTO;
import com.api.entity.Department;
import com.api.entity.Employee;
import com.api.exception.BadRequestException;
import com.api.repository.DepartmentRepository;
import com.api.repository.EmployeeRepository;
import com.api.rest.DepartmentController;
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

	public Resource<DepartmentDTO> getDepartResource(DepartmentDTO departmentDTO) {
		return new Resource<>(departmentDTO,
				linkTo(methodOn(DepartmentController.class).getDepartment(departmentDTO.getDepartmentId()))
						.withSelfRel());
	}

	@Override
	public List<DepartmentDTO> getAllDepartments() throws BadRequestException {
		List<Department> departments = departmentRepository.findAll();
		if (departments.isEmpty()) {
			return new ArrayList<>();
		}
		return departments.stream().map(department -> DTOConverter.convertDepartment(department))
				.collect(Collectors.toList());
	}

	@Override
	public DepartmentDTO getDepartment(Integer departmentId) throws BadRequestException {
		if (NumberUtils.isEmpty(departmentId)) {
			throw new BadRequestException("Unidentified departmentId");
		}

		Department department = departmentRepository.findOne(departmentId);
		if (Objects.isNull(department)) {
			throw new BadRequestException("Department with " + departmentId + " is no exists.");
		}
		return DTOConverter.convertDepartment(department);
	}

	@Override
	public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) throws BadRequestException {
		return DTOConverter.convertDepartment(departmentRepository.save(DAOConverter.convertDepartment(departmentDTO)));
	}

	@Override
	public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO) throws BadRequestException {
		Integer departmentId = departmentDTO.getDepartmentId();

		if (NumberUtils.isEmpty(departmentId)) {
			throw new BadRequestException("Unidentified departmentId");
		}

		Department department = departmentRepository.findOne(departmentId);
		if (Objects.isNull(department)) {
			throw new BadRequestException("Department with " + departmentId + " is no longer exists.");
		}

		department.setId(departmentDTO.getDepartmentId());
		department.setDepartName(departmentDTO.getDepartName());
		department.setDescription(departmentDTO.getDescription());
		department.setEmail(departmentDTO.getEmail());
		department.setCompany(DAOConverter.convertCompany(departmentDTO.getCompany()));
		return DTOConverter.convertDepartment(departmentRepository.save(department));
	}

	@Override
	public void deleteDepartment(Integer departmentId) throws BadRequestException {
		if (NumberUtils.isEmpty(departmentId)) {
			throw new BadRequestException("Unidentified departmentId");
		}

		Department department = departmentRepository.findOne(departmentId);
		if (Objects.isNull(department)) {
			throw new BadRequestException("Department with " + departmentId + " is no exists.");
		}
		departmentRepository.delete(departmentId);
	}

	@Override
	public List<EmployeeDTO> getAllEmployees(Integer departmentId) throws BadRequestException {
		List<Employee> employees = employeeRepository.findAllByDepartmentId(departmentId);
		if (employees.isEmpty()) {
			return new ArrayList<>();
		}
		return employees.stream().map(employee -> DTOConverter.convertEmployee(employee)).collect(Collectors.toList());
	}

	@Override
	public EmployeeDTO getEmployee(Integer departmentId, Integer employeeId) throws BadRequestException {
		if (NumberUtils.isEmpty(departmentId)) {
			throw new BadRequestException("Unidentified departmentId");
		}

		if (NumberUtils.isEmpty(employeeId)) {
			throw new BadRequestException("Unidentified employeeId");
		}

		Department department = departmentRepository.findOne(departmentId);
		if (Objects.isNull(department)) {
			throw new BadRequestException("Department with " + departmentId + " is no exists.");
		}

		Employee employee = employeeRepository.findOneByDepartmentId(departmentId, employeeId);
		if (Objects.isNull(employee)) {
			throw new BadRequestException("Employee with " + employeeId + " is no exists.");
		}
		return DTOConverter.convertEmployee(employee);
	}

	@Override
	public EmployeeDTO createEmployee(Integer departmentId, EmployeeDTO employeeDTO) throws BadRequestException {
		if (NumberUtils.isEmpty(departmentId)) {
			throw new BadRequestException("Unidentified departmentId");
		}

		Department department = departmentRepository.findOne(departmentId);
		if (Objects.isNull(department)) {
			throw new BadRequestException("Department with " + departmentId + " is no exists.");
		}

		employeeDTO.setDepartment(DTOConverter.convertDepartment(department));
		return DTOConverter.convertEmployee(employeeRepository.save(DAOConverter.convertEmployee(employeeDTO)));

	}

	@Override
	public EmployeeDTO updateEmployee(Integer departmentId, EmployeeDTO employeeDTO) throws BadRequestException {
		if (NumberUtils.isEmpty(departmentId)) {
			throw new BadRequestException("Unidentified departmentId");
		}

		Department department = departmentRepository.findOne(departmentId);
		if (Objects.isNull(department)) {
			throw new BadRequestException("Department with " + departmentId + " is no exists.");
		}

		Integer employeeId = employeeDTO.getEmployeeId();
		Employee employee = employeeRepository.findOne(employeeId);
		if (Objects.isNull(employee)) {
			throw new BadRequestException("Employee with " + employeeId + " is no exists.");
		}

		employee.setEmployeeName(employeeDTO.getEmployeeName());
		employee.setBirthday(employeeDTO.getBirthday());
		employee.setSex(employeeDTO.getSex());
		employee.setPhone(employeeDTO.getPhone());
		employee.setProjects(employeeDTO.getProjects().stream().map(project -> DAOConverter.convertProject(project))
				.collect(Collectors.toSet()));
		employee.setDepartment(DAOConverter.convertDepartment(employeeDTO.getDepartment()));

		return DTOConverter.convertEmployee(employeeRepository.save(employee));
	}

	@Override
	public void deleteEmployee(Integer departmentId, Integer employeeId) throws BadRequestException {
		if (NumberUtils.isEmpty(departmentId)) {
			throw new BadRequestException("Unidentified departmentId");
		}

		if (NumberUtils.isEmpty(employeeId)) {
			throw new BadRequestException("Unidentified employeeId");
		}

		Department department = departmentRepository.findOne(departmentId);
		if (Objects.isNull(department)) {
			throw new BadRequestException("Department with " + departmentId + " is no exists.");
		}

		Employee employee = employeeRepository.findOneByDepartmentId(departmentId, employeeId);
		if (Objects.isNull(employee)) {
			throw new BadRequestException("Employee with " + employeeId + " is no exists.");
		}
		employeeRepository.delete(employee);
	}

}
