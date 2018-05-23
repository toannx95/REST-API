package com.api.dto;

import java.util.Set;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DomainDTO extends ResourceSupport {

	private Integer domainId;
	private String domainName;
	@JsonIgnore
	private Set<CompanyDTO> companies;

	public DomainDTO() {
		super();
	}

	public DomainDTO(String domainName) {
		super();
		this.domainName = domainName;
	}

	public DomainDTO(Integer domainId, String domainName) {
		super();
		this.domainId = domainId;
		this.domainName = domainName;
	}

	public DomainDTO(Integer domainId, String domainName, Set<CompanyDTO> companies) {
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

	public Set<CompanyDTO> getCompanies() {
		return companies;
	}

	public void setCompanies(Set<CompanyDTO> companies) {
		this.companies = companies;
	}

}
