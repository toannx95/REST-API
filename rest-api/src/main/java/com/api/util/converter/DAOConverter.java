package com.api.util.converter;

import org.modelmapper.ModelMapper;

import com.api.dto.CompanyDTO;
import com.api.dto.DepartmentDTO;
import com.api.dto.DomainDTO;
import com.api.dto.EmployeeDTO;
import com.api.dto.LayoutDTO;
import com.api.dto.ProjectDTO;
import com.api.entity.Company;
import com.api.entity.Department;
import com.api.entity.Domain;
import com.api.entity.Employee;
import com.api.entity.Layout;
import com.api.entity.Project;

public class DAOConverter {

	// to fix bug: "matches multiple source property hierarchies"
	// use: modelMapper.getConfiguration().setAmbiguityIgnored(true);

	public static Company convertCompany(CompanyDTO companyDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(companyDTO, Company.class);
	}

	public static Department convertDepartment(DepartmentDTO departmentDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(departmentDTO, Department.class);
	}

	public static Domain convertDomain(DomainDTO domainDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(domainDTO, Domain.class);
	}

	public static Employee convertEmployee(EmployeeDTO employeeDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(employeeDTO, Employee.class);
	}

	public static Layout convertLayout(LayoutDTO layoutDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(layoutDTO, Layout.class);
	}

	public static Project convertProject(ProjectDTO projectDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(projectDTO, Project.class);
	}
}
