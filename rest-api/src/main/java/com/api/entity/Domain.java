package com.api.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DOMAIN")
public class Domain implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DOMAIN_ID")
	private Integer id;

	@Column(name = "DOMAIN_NAME", nullable = false)
	private String domainName;

	// mappedBy variable is variable in Company class (private Domain domain)
	@OneToMany(mappedBy = "domain", cascade = CascadeType.ALL)
	private Set<Company> companies;

	public Domain() {
		super();
	}

	public Domain(String domainName) {
		super();
		this.domainName = domainName;
	}

	public Domain(Integer id, String domainName) {
		super();
		this.id = id;
		this.domainName = domainName;
	}

	public Domain(Integer id, String domainName, Set<Company> companies) {
		super();
		this.id = id;
		this.domainName = domainName;
		this.companies = companies;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer domainId) {
		this.id = domainId;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public Set<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(Set<Company> companies) {
		this.companies = companies;
	}

}
