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
@Table(name = "LAYOUT")
public class Layout implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LAYOUT_ID")
	private Integer id;

	@Column(name = "LAYOUT_NAME", nullable = false)
	private String layoutName;

	// mappedBy variable is variable in Company class (private Layout layout)
	@OneToMany(mappedBy = "layout", cascade = CascadeType.ALL)
	private Set<Company> companies;

	public Layout() {
		super();
	}

	public Layout(Integer id, String layoutName, Set<Company> companies) {
		super();
		this.id = id;
		this.layoutName = layoutName;
		this.companies = companies;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLayoutName() {
		return layoutName;
	}

	public void setLayoutName(String layoutName) {
		this.layoutName = layoutName;
	}

	public Set<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(Set<Company> companies) {
		this.companies = companies;
	}

}
