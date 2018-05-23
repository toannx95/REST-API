package com.api.dto;

import java.util.Date;
import java.util.Set;

import org.springframework.hateoas.ResourceSupport;

public class EmployeeDTO extends ResourceSupport {

	private Integer employeeId;
	private String employeeName;
	private Date birthday;
	private int sex;
	private int phone;
	private Set<ProjectDTO> projects;
	private DepartmentDTO department;

	public EmployeeDTO() {
		super();
	}

	public EmployeeDTO(Integer employeeId, String employeeName, Date birthday, int sex, int phone,
			Set<ProjectDTO> projects, DepartmentDTO department) {
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

	public Set<ProjectDTO> getProjects() {
		return projects;
	}

	public void setProjects(Set<ProjectDTO> projects) {
		this.projects = projects;
	}

	public DepartmentDTO getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentDTO department) {
		this.department = department;
	}

}
