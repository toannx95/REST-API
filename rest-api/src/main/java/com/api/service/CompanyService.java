package com.api.service;

import java.util.List;

import com.api.dto.CompanyDTO;
import com.api.dto.DepartmentDTO;
import com.api.exception.BadRequestException;

public interface CompanyService {

	List<CompanyDTO> getAllCompanies() throws BadRequestException;

	CompanyDTO getCompany(Integer companyId) throws BadRequestException;

	CompanyDTO createCompany(CompanyDTO companyDTO) throws BadRequestException;

	CompanyDTO updateCompany(CompanyDTO companyDTO) throws BadRequestException;

	void deleteCompany(Integer companyId) throws BadRequestException;

	List<DepartmentDTO> getAllDepartments(Integer companyId) throws BadRequestException;

	DepartmentDTO getDepartment(Integer companyId, Integer departmentId) throws BadRequestException;

	DepartmentDTO createDepartment(Integer companyId, DepartmentDTO departmentDTO) throws BadRequestException;

	DepartmentDTO updateDeparment(Integer companyId, DepartmentDTO departmentDTO) throws BadRequestException;

	void deleteDepartment(Integer companyId, Integer departmentId) throws BadRequestException;

}
