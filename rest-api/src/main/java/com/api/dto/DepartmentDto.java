package com.api.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DepartmentDto {

	// need create name for Id is "departmentId", if create name for Id is departId,
	// it will not mapper for Id in entity
	private Integer departmentId;
	private String departName;
	private String description;
	private String email;
	private CompanyDto company;
	@JsonIgnore
	private Set<EmployeeDto> employees;

	public DepartmentDto() {
		super();
	}

	public DepartmentDto(Integer departmentId, String departName, String description, String email, CompanyDto company,
			Set<EmployeeDto> employees) {
		super();
		this.departmentId = departmentId;
		this.departName = departName;
		this.description = description;
		this.email = email;
		this.company = company;
		this.employees = employees;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public CompanyDto getCompany() {
		return company;
	}

	public void setCompany(CompanyDto company) {
		this.company = company;
	}

	public Set<EmployeeDto> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<EmployeeDto> employees) {
		this.employees = employees;
	}

}
