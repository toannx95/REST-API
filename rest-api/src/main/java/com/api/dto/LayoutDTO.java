package com.api.dto;

import java.util.Set;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LayoutDTO extends ResourceSupport {

	private Integer layoutId;
	private String layoutName;
	@JsonIgnore
	private Set<CompanyDTO> companies;

	public LayoutDTO() {
		super();
	}

	public LayoutDTO(Integer layoutId, String layoutName, Set<CompanyDTO> companies) {
		super();
		this.layoutId = layoutId;
		this.layoutName = layoutName;
		this.companies = companies;
	}

	public Integer getLayoutId() {
		return layoutId;
	}

	public void setLayoutId(Integer layoutId) {
		this.layoutId = layoutId;
	}

	public String getLayoutName() {
		return layoutName;
	}

	public void setLayoutName(String layoutName) {
		this.layoutName = layoutName;
	}

	public Set<CompanyDTO> getCompanies() {
		return companies;
	}

	public void setCompanies(Set<CompanyDTO> companies) {
		this.companies = companies;
	}

}
