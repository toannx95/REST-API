package com.api.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProjectDto {

	private Integer projectId;
	private String projectName;
	private String status;
	private String description;
	@JsonIgnore
	private Set<EmployeeDto> employees;

	public ProjectDto() {
		super();
	}

	public ProjectDto(Integer projectId, String projectName, String status, String description,
			Set<EmployeeDto> employees) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.status = status;
		this.description = description;
		this.employees = employees;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<EmployeeDto> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<EmployeeDto> employees) {
		this.employees = employees;
	}

}
