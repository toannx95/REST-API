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
import com.api.dto.DepartmentDTO;
import com.api.dto.GenericResponseDTO;
import com.api.exception.BadRequestException;
import com.api.service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	private GenericResponseDTO addLinkGenericResponseResource(Object object) {
		GenericResponseDTO genericResponseDTO = new GenericResponseDTO(HttpConstant.MESSAGE_SUCCESS, object);
		genericResponseDTO.add(linkTo(methodOn(DepartmentController.class).getAllDeparments()).withSelfRel());
		return genericResponseDTO;
	}

	private GenericResponseDTO addLinkGenericResponseResource(String message) {
		GenericResponseDTO genericResponseDTO = new GenericResponseDTO(message);
		genericResponseDTO.add(linkTo(methodOn(DepartmentController.class).getAllDeparments()).withSelfRel());
		return genericResponseDTO;
	}

	public static Resource<DepartmentDTO> addLinkDepartmentResource(DepartmentDTO departmentDTO) {
		return new Resource<>(departmentDTO,
				linkTo(methodOn(DepartmentController.class).getDepartment(departmentDTO.getDepartmentId()))
						.withSelfRel());
	}

	@GetMapping
	public ResponseEntity<GenericResponseDTO> getAllDeparments() {
		try {
			List<DepartmentDTO> departments = departmentService.getAllDepartments();
			List<Resource<DepartmentDTO>> resources = new ArrayList<>();
			departments.stream().map(depart -> resources.add(addLinkDepartmentResource(depart)))
					.collect(Collectors.toList());
			return ResponseEntity.ok(addLinkGenericResponseResource(resources));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

	@GetMapping("/{departmentId}")
	public ResponseEntity<GenericResponseDTO> getDepartment(@PathVariable("departmentId") Integer departmentId) {
		try {
			DepartmentDTO department = departmentService.getDepartment(departmentId);
			return ResponseEntity.ok(addLinkGenericResponseResource(addLinkDepartmentResource(department)));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

	@PostMapping
	public ResponseEntity<GenericResponseDTO> createDepartment(@RequestBody DepartmentDTO departmentDTO) {
		try {
			DepartmentDTO department = departmentService.createDepartment(departmentDTO);
			return ResponseEntity.ok(addLinkGenericResponseResource(addLinkDepartmentResource(department)));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

	@PutMapping
	public ResponseEntity<GenericResponseDTO> updateDepartment(@RequestBody DepartmentDTO departmentDTO) {
		try {
			DepartmentDTO department = departmentService.updateDepartment(departmentDTO);
			return ResponseEntity.ok(addLinkGenericResponseResource(addLinkDepartmentResource(department)));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

	@DeleteMapping("/{departmentId}")
	public ResponseEntity<GenericResponseDTO> deleteDepartment(@PathVariable("departmentId") Integer departmentId) {
		try {
			departmentService.deleteDepartment(departmentId);
			return ResponseEntity.ok(addLinkGenericResponseResource(HttpConstant.MESSAGE_SUCCESS));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

}
