package com.api.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPLOYEE_ID")
	private Integer id;

	@Column(name = "EMPLOYEE_NAME", nullable = false)
	private String employeeName;

	@Column(name = "BIRTHDAY")
	private Date birthday;

	@Column(name = "SEX")
	private int sex;

	@Column(name = "PHONE")
	private int phone;

	@ManyToMany
	@JoinTable(name = "EMPLOYEE_PROJECT", joinColumns = { @JoinColumn(name = "EMPLOYEE_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "PROJECT_ID") })
	private Set<Project> projects;

	@ManyToOne
	@JoinColumn(name = "DEPARTMENT_ID")
	private Department department;

	public Employee() {
		super();
	}

	public Employee(Integer id, String employeeName, Date birthday, int sex, int phone, Set<Project> projects,
			Department department) {
		super();
		this.id = id;
		this.employeeName = employeeName;
		this.birthday = birthday;
		this.sex = sex;
		this.phone = phone;
		this.projects = projects;
		this.department = department;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
