package com.api.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CompanyDto {

	private Integer companyId;
	private String companyName;
	private String url;
	private int phone;
	private DomainDto domain;
	private LayoutDto layout;
	@JsonIgnore
	private Set<DepartmentDto> departments;

	public CompanyDto() {
		super();
	}

	public CompanyDto(Integer companyId, String companyName, String url, int phone, DomainDto domain, LayoutDto layout,
			Set<DepartmentDto> departments) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
		this.url = url;
		this.phone = phone;
		this.domain = domain;
		this.layout = layout;
		this.departments = departments;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public DomainDto getDomain() {
		return domain;
	}

	public void setDomain(DomainDto domain) {
		this.domain = domain;
	}

	public LayoutDto getLayout() {
		return layout;
	}

	public void setLayout(LayoutDto layout) {
		this.layout = layout;
	}

	public Set<DepartmentDto> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<DepartmentDto> departments) {
		this.departments = departments;
	}

}
