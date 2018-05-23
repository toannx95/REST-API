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
import com.api.dto.EmployeeDTO;
import com.api.dto.GenericResponseDTO;
import com.api.exception.BadRequestException;
import com.api.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	private GenericResponseDTO addLinkGenericResponseResource(Object object) {
		GenericResponseDTO genericResponseDTO = new GenericResponseDTO(HttpConstant.MESSAGE_SUCCESS, object);
		genericResponseDTO.add(linkTo(methodOn(EmployeeController.class).getAllEmployees()).withSelfRel());
		return genericResponseDTO;
	}

	private GenericResponseDTO addLinkGenericResponseResource(String message) {
		GenericResponseDTO genericResponseDTO = new GenericResponseDTO(message);
		genericResponseDTO.add(linkTo(methodOn(EmployeeController.class).getAllEmployees()).withSelfRel());
		return genericResponseDTO;
	}

	public static Resource<EmployeeDTO> addLinkEmployeeResource(EmployeeDTO employeeDTO) {
		return new Resource<>(employeeDTO,
				linkTo(methodOn(EmployeeController.class).getEmployee(employeeDTO.getEmployeeId())).withSelfRel());
	}

	@GetMapping
	public ResponseEntity<GenericResponseDTO> getAllEmployees() {
		try {
			List<EmployeeDTO> employees = employeeService.getAllEmployees();
			List<Resource<EmployeeDTO>> resources = new ArrayList<>();
			employees.stream().map(employee -> resources.add(addLinkEmployeeResource(employee)))
					.collect(Collectors.toList());
			return ResponseEntity.ok(addLinkGenericResponseResource(resources));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

	@GetMapping("/{employeeId}")
	public ResponseEntity<GenericResponseDTO> getEmployee(@PathVariable("employeeId") Integer employeeId) {
		try {
			EmployeeDTO employee = employeeService.getEmployee(employeeId);
			return ResponseEntity.ok(addLinkGenericResponseResource(employee));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

	@PostMapping
	public ResponseEntity<GenericResponseDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
		try {
			EmployeeDTO employee = employeeService.createEmployee(employeeDTO);
			return ResponseEntity.ok(addLinkGenericResponseResource(employee));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

	@PutMapping
	public ResponseEntity<GenericResponseDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
		try {
			EmployeeDTO employee = employeeService.updateEmployee(employeeDTO);
			return ResponseEntity.ok(addLinkGenericResponseResource(employee));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

	@DeleteMapping("/{employeeId}")
	public ResponseEntity<GenericResponseDTO> deleteEmployee(@PathVariable("employeeId") Integer employeeId) {
		try {
			employeeService.deleteEmployee(employeeId);
			return ResponseEntity.ok(addLinkGenericResponseResource(HttpConstant.MESSAGE_SUCCESS));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

}
