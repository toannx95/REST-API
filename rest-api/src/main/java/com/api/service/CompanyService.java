package com.api.service;

import java.util.List;

import com.api.dto.CompanyDto;
import com.api.dto.DepartmentDto;

public interface CompanyService {

	List<CompanyDto> getAllCompanies();

	CompanyDto getCompany(Integer companyId);

	CompanyDto createCompany(CompanyDto companyDto);

	CompanyDto updateCompany(CompanyDto companyDto);

	void deleteCompany(Integer companyId);

	List<DepartmentDto> getAllDepartments(Integer companyId);

	DepartmentDto getDepartment(Integer companyId, Integer departmentId);

	DepartmentDto createDepartment(Integer companyId, DepartmentDto departmentDto);

	DepartmentDto updateDeparment(Integer companyId, DepartmentDto departmentDto);

	void deleteDepartment(Integer companyId, Integer departmentId);

}
