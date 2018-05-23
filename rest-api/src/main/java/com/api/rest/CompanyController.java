package com.api.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.constant.HttpConstant;
import com.api.dto.CompanyDTO;
import com.api.dto.DepartmentDTO;
import com.api.dto.GenericResponseDTO;
import com.api.exception.BadRequestException;
import com.api.service.CompanyService;

@RestController
@RequestMapping("/companies")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	private GenericResponseDTO addLinkGenericResponseResource(Object object) {
		GenericResponseDTO genericResponseDTO = new GenericResponseDTO(HttpConstant.MESSAGE_SUCCESS, object);
		genericResponseDTO.add(linkTo(methodOn(CompanyController.class).getAllCompanies()).withSelfRel());
		return genericResponseDTO;
	}

	private GenericResponseDTO addLinkGenericResponseResource(String message) {
		GenericResponseDTO genericResponseDTO = new GenericResponseDTO(message);
		genericResponseDTO.add(linkTo(methodOn(CompanyController.class).getAllCompanies()).withSelfRel());
		return genericResponseDTO;
	}

	public static Resource<CompanyDTO> addLinkCompanyResource(CompanyDTO companyDTO) {
		return new Resource<>(companyDTO,
				linkTo(methodOn(CompanyController.class).getCompany(companyDTO.getCompanyId())).withSelfRel());
	}

	@GetMapping
	public ResponseEntity<GenericResponseDTO> getAllCompanies() {
		try {
			List<CompanyDTO> companies = companyService.getAllCompanies();
			List<Resource<CompanyDTO>> resources = new ArrayList<>();
			companies.stream().map(company -> resources.add(addLinkCompanyResource(company)))
					.collect(Collectors.toList());
			return ResponseEntity.ok(addLinkGenericResponseResource(resources));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

	@GetMapping("/{companyId}")
	public ResponseEntity<GenericResponseDTO> getCompany(@PathVariable("companyId") Integer companyId) {
		try {
			CompanyDTO company = companyService.getCompany(companyId);
			return ResponseEntity.ok(addLinkGenericResponseResource(addLinkCompanyResource(company)));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

	@PostMapping
	public ResponseEntity<GenericResponseDTO> createCompany(@RequestBody CompanyDTO companyDTO) {
		try {
			CompanyDTO company = companyService.createCompany(companyDTO);
			return ResponseEntity.ok(addLinkGenericResponseResource(addLinkCompanyResource(company)));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

	@PutMapping
	public ResponseEntity<GenericResponseDTO> updateCompany(@RequestBody CompanyDTO companyDTO) {
		try {
			CompanyDTO company = companyService.updateCompany(companyDTO);
			return ResponseEntity.ok(addLinkGenericResponseResource(addLinkCompanyResource(company)));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

	@DeleteMapping("/{companyId}")
	public ResponseEntity<GenericResponseDTO> deleteCompany(@PathVariable("companyId") Integer companyId) {
		try {
			companyService.deleteCompany(companyId);
			return ResponseEntity.ok(addLinkGenericResponseResource(HttpConstant.MESSAGE_SUCCESS));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

	@GetMapping("/{companyId}/departments")
	public ResponseEntity<GenericResponseDTO> getAllDepartments(@PathVariable("companyId") Integer companyId) {
		try {
			List<DepartmentDTO> departments = companyService.getAllDepartments(companyId);
			List<Resource<DepartmentDTO>> resources = new ArrayList<>();
			departments.stream()
					.map(department -> resources.add(DepartmentController.addLinkDepartmentResource(department)))
					.collect(Collectors.toList());
			GenericResponseDTO genericResponseDTO = new GenericResponseDTO(HttpConstant.MESSAGE_SUCCESS, resources);
			genericResponseDTO.add(linkTo(methodOn(CompanyController.class).getAllCompanies()).withSelfRel());

			return ResponseEntity.ok(genericResponseDTO);
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

	@GetMapping("/{companyId}/departments/{departmentId}")
	public ResponseEntity<GenericResponseDTO> getDepartment(@PathVariable("companyId") Integer companyId,
			@PathVariable("departmentId") Integer departmentId) {
		try {
			DepartmentDTO department = companyService.getDepartment(companyId, departmentId);
			return ResponseEntity
					.ok(addLinkGenericResponseResource(DepartmentController.addLinkDepartmentResource(department)));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

	@PostMapping("/{companyId}/departments")
	public ResponseEntity<GenericResponseDTO> createDeparment(@PathVariable("companyId") Integer companyId,
			@RequestBody DepartmentDTO departmentDTO) {
		try {
			DepartmentDTO department = companyService.createDepartment(companyId, departmentDTO);
			return ResponseEntity
					.ok(addLinkGenericResponseResource(DepartmentController.addLinkDepartmentResource(department)));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

	@PutMapping("/{companyId}/departments")
	public ResponseEntity<GenericResponseDTO> updateDeparment(@PathVariable("companyId") Integer companyId,
			@RequestBody DepartmentDTO departmentDTO) {
		try {
			DepartmentDTO department = companyService.updateDeparment(companyId, departmentDTO);
			return ResponseEntity
					.ok(addLinkGenericResponseResource(DepartmentController.addLinkDepartmentResource(department)));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

	@DeleteMapping("/{companyId}/departments/{departmentId}")
	public ResponseEntity<GenericResponseDTO> deleteDepartment(@PathVariable("companyId") Integer companyId,
			@PathVariable("departmentId") Integer departmentId) {
		try {
			companyService.deleteDepartment(companyId, departmentId);
			return ResponseEntity.ok(addLinkGenericResponseResource(HttpConstant.MESSAGE_SUCCESS));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

}
