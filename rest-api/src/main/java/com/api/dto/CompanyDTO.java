package com.api.dto;

import java.util.Set;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CompanyDTO extends ResourceSupport {

	private Integer companyId;
	private String companyName;
	private String url;
	private int phone;
	private DomainDTO domain;
	private LayoutDTO layout;
	@JsonIgnore
	private Set<DepartmentDTO> departments;

	public CompanyDTO() {
		super();
	}

	public CompanyDTO(Integer companyId, String companyName, String url, int phone, DomainDTO domain, LayoutDTO layout,
			Set<DepartmentDTO> departments) {
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

	public DomainDTO getDomain() {
		return domain;
	}

	public void setDomain(DomainDTO domain) {
		this.domain = domain;
	}

	public LayoutDTO getLayout() {
		return layout;
	}

	public void setLayout(LayoutDTO layout) {
		this.layout = layout;
	}

	public Set<DepartmentDTO> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<DepartmentDTO> departments) {
		this.departments = departments;
	}

}
