package com.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.CompanyDTO;
import com.api.dto.DepartmentDTO;
import com.api.entity.Company;
import com.api.entity.Department;
import com.api.exception.BadRequestException;
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
	public List<CompanyDTO> getAllCompanies() {
		List<Company> companies = companyRepository.findAll();
		if (companies.isEmpty()) {
			return new ArrayList<>();
		}
		return companies.stream().map(company -> DTOConverter.convertCompany(company)).collect(Collectors.toList());
	}

	@Override
	public CompanyDTO getCompany(Integer companyId) throws BadRequestException {
		if (NumberUtils.isEmpty(companyId)) {
			throw new BadRequestException("Unidentified companyId.");
		}

		Company company = companyRepository.findOne(companyId);
		if (Objects.isNull(company)) {
			throw new BadRequestException("Company with " + companyId + " is no exists.");
		}
		return DTOConverter.convertCompany(company);
	}

	@Override
	public CompanyDTO createCompany(CompanyDTO companyDTO) {
		return DTOConverter.convertCompany(companyRepository.save(DAOConverter.convertCompany(companyDTO)));
	}

	@Override
	public CompanyDTO updateCompany(CompanyDTO companyDTO) throws BadRequestException {
		Integer companyId = companyDTO.getCompanyId();
		if (NumberUtils.isEmpty(companyId)) {
			throw new BadRequestException("Unidentified companyId.");
		}

		Company company = companyRepository.findOne(companyId);
		if (Objects.isNull(company)) {
			throw new BadRequestException("Company with " + companyId + " is no longer exists.");
		}

		company.setId(companyId);
		company.setCompanyName(companyDTO.getCompanyName());
		company.setUrl(companyDTO.getUrl());
		company.setPhone(companyDTO.getPhone());
		company.setDomain(DAOConverter.convertDomain(companyDTO.getDomain()));
		company.setLayout(DAOConverter.convertLayout(companyDTO.getLayout()));
		return DTOConverter.convertCompany(companyRepository.save(company));
	}

	@Override
	public void deleteCompany(Integer companyId) throws BadRequestException {
		if (NumberUtils.isEmpty(companyId)) {
			throw new BadRequestException("Unidentified companyId.");
		}

		Company company = companyRepository.findOne(companyId);
		if (Objects.isNull(company)) {
			throw new BadRequestException("Company with " + companyId + " is no exists.");
		}
		companyRepository.delete(companyId);
	}

	@Override
	public List<DepartmentDTO> getAllDepartments(Integer companyId) throws BadRequestException {
		List<Department> departments = departmentRepository.findAllByCompanyId(companyId);
		if (departments.isEmpty()) {
			return new ArrayList<>();
		}
		return departments.stream().map(department -> DTOConverter.convertDepartment(department))
				.collect(Collectors.toList());
	}

	@Override
	public DepartmentDTO getDepartment(Integer companyId, Integer departmentId) throws BadRequestException {
		if (NumberUtils.isEmpty(companyId)) {
			throw new BadRequestException("Unidentified companyId.");
		}

		if (NumberUtils.isEmpty(departmentId)) {
			throw new BadRequestException("Unidentified departmentId.");
		}

		Company company = companyRepository.findOne(companyId);
		if (Objects.isNull(company)) {
			throw new BadRequestException("Company with " + companyId + " is no exists.");
		}

		Department department = departmentRepository.findOneByCompanyId(companyId, departmentId);
		if (Objects.isNull(department)) {
			throw new BadRequestException("Department with " + departmentId + " is no exists.");
		}
		return DTOConverter.convertDepartment(department);
	}

	@Override
	public DepartmentDTO createDepartment(Integer companyId, DepartmentDTO departmentDTO) throws BadRequestException {
		if (NumberUtils.isEmpty(companyId)) {
			throw new BadRequestException("Unidentified companyId.");
		}

		Company company = companyRepository.findOne(companyId);
		if (Objects.isNull(company)) {
			throw new BadRequestException("Company with " + companyId + " is no exists.");
		}

		departmentDTO.setCompany(DTOConverter.convertCompany(company));
		return DTOConverter.convertDepartment(departmentRepository.save(DAOConverter.convertDepartment(departmentDTO)));
	}

	@Override
	public DepartmentDTO updateDeparment(Integer companyId, DepartmentDTO departmentDTO) throws BadRequestException {
		if (NumberUtils.isEmpty(companyId)) {
			throw new BadRequestException("Unidentified companyId.");
		}

		Company company = companyRepository.findOne(companyId);
		if (Objects.isNull(company)) {
			throw new BadRequestException("Company with " + companyId + " is no exists.");
		}

		Integer departmentId = departmentDTO.getDepartmentId();
		Department department = departmentRepository.findOne(departmentId);
		if (Objects.isNull(department)) {
			throw new BadRequestException("Department with " + departmentId + " is no longer exists.");
		}

		department.setDepartName(departmentDTO.getDepartName());
		department.setDescription(departmentDTO.getDescription());
		department.setEmail(departmentDTO.getEmail());
		department.setCompany(DAOConverter.convertCompany(departmentDTO.getCompany()));
		return DTOConverter.convertDepartment(departmentRepository.save(department));
	}

	@Override
	public void deleteDepartment(Integer companyId, Integer departmentId) throws BadRequestException {
		if (NumberUtils.isEmpty(companyId)) {
			throw new BadRequestException("Unidentified companyId.");
		}

		if (NumberUtils.isEmpty(departmentId)) {
			throw new BadRequestException("Unidentified departmentId.");
		}

		Company company = companyRepository.findOne(companyId);
		if (Objects.isNull(company)) {
			throw new BadRequestException("Company with " + companyId + " is no exists.");
		}

		Department department = departmentRepository.findOneByCompanyId(companyId, departmentId);
		if (Objects.isNull(department)) {
			throw new BadRequestException("Department with " + departmentId + " is no exists.");
		}
		departmentRepository.delete(department);
	}

}
