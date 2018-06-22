package com.api.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LayoutDto {

	private Integer layoutId;
	private String layoutName;
	@JsonIgnore
	private Set<CompanyDto> companies;

	public LayoutDto() {
		super();
	}

	public LayoutDto(Integer layoutId, String layoutName, Set<CompanyDto> companies) {
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

	public Set<CompanyDto> getCompanies() {
		return companies;
	}

	public void setCompanies(Set<CompanyDto> companies) {
		this.companies = companies;
	}

}
