package com.api.util.converter;

import org.modelmapper.ModelMapper;

import com.api.dto.CompanyDto;
import com.api.dto.DepartmentDto;
import com.api.dto.DomainDto;
import com.api.dto.EmployeeDto;
import com.api.dto.LayoutDto;
import com.api.dto.ProjectDto;
import com.api.entity.Company;
import com.api.entity.Department;
import com.api.entity.Domain;
import com.api.entity.Employee;
import com.api.entity.Layout;
import com.api.entity.Project;

public class DAOConverter {

	// to fix bug: "matches multiple source property hierarchies"
	// use: modelMapper.getConfiguration().setAmbiguityIgnored(true);

	public static Company convertCompany(CompanyDto companyDto) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(companyDto, Company.class);
	}

	public static Department convertDepartment(DepartmentDto departmentDto) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(departmentDto, Department.class);
	}

	public static Domain convertDomain(DomainDto domainDto) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(domainDto, Domain.class);
	}

	public static Employee convertEmployee(EmployeeDto employeeDto) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(employeeDto, Employee.class);
	}

	public static Layout convertLayout(LayoutDto layoutDto) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(layoutDto, Layout.class);
	}

	public static Project convertProject(ProjectDto projectDto) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(projectDto, Project.class);
	}
}
