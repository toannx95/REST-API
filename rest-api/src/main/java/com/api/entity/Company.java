package com.api.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "COMPANY")
public class Company implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COMPANY_ID")
	private Integer id;

	@Column(name = "COMPANY_NAME", nullable = false)
	private String companyName;

	@Column(name = "URL", nullable = false)
	private String url;

	@Column(name = "PHONE")
	private int phone;

	@ManyToOne
	@JoinColumn(name = "DOMAIN_ID")
	private Domain domain;

	@ManyToOne
	@JoinColumn(name = "LAYOUT_ID")
	private Layout layout;

	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	private Set<Department> departments;

	public Company() {
		super();
	}

	public Company(Integer id, String companyName, String url, int phone, Domain domain, Layout layout,
			Set<Department> departments) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.url = url;
		this.phone = phone;
		this.domain = domain;
		this.layout = layout;
		this.departments = departments;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public Layout getLayout() {
		return layout;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	public Set<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}

}
