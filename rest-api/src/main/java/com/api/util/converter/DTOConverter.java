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

public class DTOConverter {

	// to fix bug: "matches multiple source property hierarchies"
	// use: modelMapper.getConfiguration().setAmbiguityIgnored(true);

	public static CompanyDTO convertCompany(Company company) {
		ModelMapper modelMapper = new ModelMapper();
		// modelMapper.getConfiguration().setAmbiguityIgnored(true);
		return modelMapper.map(company, CompanyDTO.class);
	}

	public static DepartmentDTO convertDepartment(Department department) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(department, DepartmentDTO.class);
	}

	public static DomainDTO convertDomain(Domain domain) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(domain, DomainDTO.class);
	}

	public static EmployeeDTO convertEmployee(Employee employee) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(employee, EmployeeDTO.class);
	}

	public static LayoutDTO convertLayout(Layout layout) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(layout, LayoutDTO.class);
	}

	public static ProjectDTO convertProject(Project project) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(project, ProjectDTO.class);
	}

}
