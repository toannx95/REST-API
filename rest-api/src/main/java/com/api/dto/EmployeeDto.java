package com.api.dto;

import java.util.Date;
import java.util.Set;

public class EmployeeDto {

	private Integer employeeId;
	private String employeeName;
	private Date birthday;
	private int sex;
	private int phone;
	private Set<ProjectDto> projects;
	private DepartmentDto department;

	public EmployeeDto() {
		super();
	}

	public EmployeeDto(Integer employeeId, String employeeName, Date birthday, int sex, int phone,
			Set<ProjectDto> projects, DepartmentDto department) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.birthday = birthday;
		this.sex = sex;
		this.phone = phone;
		this.projects = projects;
		this.department = department;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public Set<ProjectDto> getProjects() {
		return projects;
	}

	public void setProjects(Set<ProjectDto> projects) {
		this.projects = projects;
	}

	public DepartmentDto getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentDto department) {
		this.department = department;
	}

}
