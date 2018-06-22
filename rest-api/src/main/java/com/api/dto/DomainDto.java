package com.api.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DomainDto {

	private Integer domainId;
	private String domainName;
	@JsonIgnore
	private Set<CompanyDto> companies;

	public DomainDto() {
		super();
	}

	public DomainDto(String domainName) {
		super();
		this.domainName = domainName;
	}

	public DomainDto(Integer domainId, String domainName) {
		super();
		this.domainId = domainId;
		this.domainName = domainName;
	}

	public DomainDto(Integer domainId, String domainName, Set<CompanyDto> companies) {
		super();
		this.domainId = domainId;
		this.domainName = domainName;
		this.companies = companies;
	}

	public Integer getDomainId() {
		return domainId;
	}

	public void setDomainId(Integer domainId) {
		this.domainId = domainId;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public Set<CompanyDto> getCompanies() {
		return companies;
	}

	public void setCompanies(Set<CompanyDto> companies) {
		this.companies = companies;
	}

}
