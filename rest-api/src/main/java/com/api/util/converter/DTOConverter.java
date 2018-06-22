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

public class DTOConverter {

	// to fix bug: "matches multiple source property hierarchies"
	// use: modelMapper.getConfiguration().setAmbiguityIgnored(true);

	public static CompanyDto convertCompany(Company company) {
		ModelMapper modelMapper = new ModelMapper();
		// modelMapper.getConfiguration().setAmbiguityIgnored(true);
		return modelMapper.map(company, CompanyDto.class);
	}

	public static DepartmentDto convertDepartment(Department department) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(department, DepartmentDto.class);
	}

	public static DomainDto convertDomain(Domain domain) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(domain, DomainDto.class);
	}

	public static EmployeeDto convertEmployee(Employee employee) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(employee, EmployeeDto.class);
	}

	public static LayoutDto convertLayout(Layout layout) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(layout, LayoutDto.class);
	}

	public static ProjectDto convertProject(Project project) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(project, ProjectDto.class);
	}

}
