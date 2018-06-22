package com.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.CompanyDto;
import com.api.dto.DepartmentDto;
import com.api.entity.Company;
import com.api.entity.Department;
import com.api.exception.BadRequestException;
import com.api.exception.NotFoundException;
import com.api.repository.CompanyRepository;
import com.api.repository.DepartmentRepository;
import com.api.service.CompanyService;
import com.api.util.NumberUtils;
import com.api.util.converter.DAOConverter;
import com.api.util.converter.DTOConverter;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public List<CompanyDto> getAllCompanies() {
		List<Company> companies = companyRepository.findAll();
		if (companies.isEmpty()) {
			return new ArrayList<>();
		}
		return companies.stream().map(company -> DTOConverter.convertCompany(company)).collect(Collectors.toList());
	}

	@Override
	public CompanyDto getCompany(Integer companyId) {
		if (NumberUtils.isEmpty(companyId)) {
			throw new BadRequestException("Unidentified companyId!");
		}

		Company company = companyRepository.findOne(companyId);
		if (Objects.isNull(company)) {
			throw new NotFoundException("Company", "companyId", companyId);
		}
		return DTOConverter.convertCompany(company);
	}

	@Override
	public CompanyDto createCompany(CompanyDto companyDto) {
		return DTOConverter.convertCompany(companyRepository.save(DAOConverter.convertCompany(companyDto)));
	}

	@Override
	public CompanyDto updateCompany(CompanyDto companyDto) {
		Integer companyId = companyDto.getCompanyId();
		if (NumberUtils.isEmpty(companyId)) {
			throw new BadRequestException("Unidentified companyId!");
		}

		Company company = companyRepository.findOne(companyId);
		if (Objects.isNull(company)) {
			throw new NotFoundException("Company", "companyId", companyId);
		}

		company.setId(companyId);
		company.setCompanyName(companyDto.getCompanyName());
		company.setUrl(companyDto.getUrl());
		company.setPhone(companyDto.getPhone());
		company.setDomain(DAOConverter.convertDomain(companyDto.getDomain()));
		company.setLayout(DAOConverter.convertLayout(companyDto.getLayout()));
		return DTOConverter.convertCompany(companyRepository.save(company));
	}

	@Override
	public void deleteCompany(Integer companyId) {
		if (NumberUtils.isEmpty(companyId)) {
			throw new BadRequestException("Unidentified companyId!");
		}

		Company company = companyRepository.findOne(companyId);
		if (Objects.isNull(company)) {
			throw new BadRequestException("Company with " + companyId + " is no exists.");
		}
		companyRepository.delete(companyId);
	}

	@Override
	public List<DepartmentDto> getAllDepartments(Integer companyId) {
		List<Department> departments = departmentRepository.findAllByCompanyId(companyId);
		if (departments.isEmpty()) {
			return new ArrayList<>();
		}
		return departments.stream().map(department -> DTOConverter.convertDepartment(department))
				.collect(Collectors.toList());
	}

	@Override
	public DepartmentDto getDepartment(Integer companyId, Integer departmentId) {
		if (NumberUtils.isEmpty(companyId)) {
			throw new BadRequestException("Unidentified companyId!");
		}

		if (NumberUtils.isEmpty(departmentId)) {
			throw new BadRequestException("Unidentified departmentId!");
		}

		Company company = companyRepository.findOne(companyId);
		if (Objects.isNull(company)) {
			throw new NotFoundException("Company", "companyId", companyId);
		}

		Department department = departmentRepository.findOneByCompanyId(companyId, departmentId);
		if (Objects.isNull(department)) {
			throw new NotFoundException("Department", "departmentId", departmentId);
		}
		return DTOConverter.convertDepartment(department);
	}

	@Override
	public DepartmentDto createDepartment(Integer companyId, DepartmentDto departmentDto) {
		if (NumberUtils.isEmpty(companyId)) {
			throw new BadRequestException("Unidentified companyId!");
		}

		Company company = companyRepository.findOne(companyId);
		if (Objects.isNull(company)) {
			throw new NotFoundException("Company", "companyId", companyId);
		}

		departmentDto.setCompany(DTOConverter.convertCompany(company));
		return DTOConverter.convertDepartment(departmentRepository.save(DAOConverter.convertDepartment(departmentDto)));
	}

	@Override
	public DepartmentDto updateDeparment(Integer companyId, DepartmentDto departmentDto) {
		if (NumberUtils.isEmpty(companyId)) {
			throw new BadRequestException("Unidentified companyId!");
		}

		Company company = companyRepository.findOne(companyId);
		if (Objects.isNull(company)) {
			throw new NotFoundException("Company", "companyId", companyId);
		}

		Integer departmentId = departmentDto.getDepartmentId();
		Department department = departmentRepository.findOne(departmentId);
		if (Objects.isNull(department)) {
			throw new NotFoundException("Department", "departmentId", departmentId);
		}

		department.setDepartName(departmentDto.getDepartName());
		department.setDescription(departmentDto.getDescription());
		department.setEmail(departmentDto.getEmail());
		department.setCompany(DAOConverter.convertCompany(departmentDto.getCompany()));
		return DTOConverter.convertDepartment(departmentRepository.save(department));
	}

	@Override
	public void deleteDepartment(Integer companyId, Integer departmentId) {
		if (NumberUtils.isEmpty(companyId)) {
			throw new BadRequestException("Unidentified companyId!");
		}

		if (NumberUtils.isEmpty(departmentId)) {
			throw new BadRequestException("Unidentified departmentId!");
		}

		Company company = companyRepository.findOne(companyId);
		if (Objects.isNull(company)) {
			throw new NotFoundException("Company", "companyId", companyId);
		}

		Department department = departmentRepository.findOneByCompanyId(companyId, departmentId);
		if (Objects.isNull(department)) {
			throw new NotFoundException("Department", "departmentId", departmentId);
		}
		departmentRepository.delete(department);
	}

}
