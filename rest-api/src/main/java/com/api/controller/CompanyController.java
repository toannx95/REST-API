package com.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.dto.ApiResponseDto;
import com.api.dto.CompanyDto;
import com.api.dto.DepartmentDto;
import com.api.service.CompanyService;

@RestController
@RequestMapping("/companies")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@GetMapping
	public ResponseEntity<?> getAllCompanies() {
		List<CompanyDto> companies = companyService.getAllCompanies();
		if (companies.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(companies);
	}

	@GetMapping("/{companyId}")
	public ResponseEntity<?> getCompany(@PathVariable("companyId") Integer companyId) {
		return ResponseEntity.ok(companyService.getCompany(companyId));
	}

	@PostMapping
	public ResponseEntity<?> createCompany(@RequestBody CompanyDto companyDTO) {
		CompanyDto company = companyService.createCompany(companyDTO);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{companyId}")
				.buildAndExpand(company.getCompanyId()).toUri();
		return ResponseEntity.created(location).body(new ApiResponseDto(true, "Company created successfully!"));
	}

	@PutMapping
	public ResponseEntity<?> updateCompany(@RequestBody CompanyDto companyDTO) {
		companyService.updateCompany(companyDTO);
		return ResponseEntity.ok(new ApiResponseDto(true, "Company updated successfully!"));
	}

	@DeleteMapping("/{companyId}")
	public ResponseEntity<?> deleteCompany(@PathVariable("companyId") Integer companyId) {
		companyService.deleteCompany(companyId);
		return ResponseEntity.ok(new ApiResponseDto(true, "Company deleted successfully!"));
	}

	@GetMapping("/{companyId}/departments")
	public ResponseEntity<?> getAllDepartments(@PathVariable("companyId") Integer companyId) {
		List<DepartmentDto> departments = companyService.getAllDepartments(companyId);
		if (departments.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(departments);
	}

	@GetMapping("/{companyId}/departments/{departmentId}")
	public ResponseEntity<?> getDepartment(@PathVariable("companyId") Integer companyId,
			@PathVariable("departmentId") Integer departmentId) {
		return ResponseEntity.ok(companyService.getDepartment(companyId, departmentId));
	}

	@PostMapping("/{companyId}/departments")
	public ResponseEntity<?> createDeparment(@PathVariable("companyId") Integer companyId,
			@RequestBody DepartmentDto departmentDTO) {
		DepartmentDto department = companyService.createDepartment(companyId, departmentDTO);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{departmentId}")
				.buildAndExpand(department.getDepartmentId()).toUri();
		return ResponseEntity.created(location).body(new ApiResponseDto(true, "Department created successfully!"));
	}

	@PutMapping("/{companyId}/departments")
	public ResponseEntity<?> updateDeparment(@PathVariable("companyId") Integer companyId,
			@RequestBody DepartmentDto departmentDTO) {
		companyService.updateDeparment(companyId, departmentDTO);
		return ResponseEntity.ok(new ApiResponseDto(true, "Department updated successfully!"));
	}

	@DeleteMapping("/{companyId}/departments/{departmentId}")
	public ResponseEntity<?> deleteDepartment(@PathVariable("companyId") Integer companyId,
			@PathVariable("departmentId") Integer departmentId) {
		companyService.deleteDepartment(companyId, departmentId);
		return ResponseEntity.ok(new ApiResponseDto(true, "Department deleted successfully!"));
	}

	@GetMapping("/findByNameStartingWith")
	public ResponseEntity<?> findByNameStartingWith(@RequestParam("companyName") String companyName) {
		List<CompanyDto> companies = companyService.findByNameStartingWith(companyName);
		if (companies.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(companies);
	}

	@GetMapping("/findByNameContainsWith")
	public ResponseEntity<?> findByNameContainsWith(@RequestParam("companyName") String companyName) {
		List<CompanyDto> companies = companyService.findByNameContainsWith(companyName);
		if (companies.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(companies);
	}

}
