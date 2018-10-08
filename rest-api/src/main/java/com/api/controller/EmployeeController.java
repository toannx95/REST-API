package com.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import com.api.dto.EmployeeDto;
import com.api.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping
	public ResponseEntity<?> getAllEmployees() {
		List<EmployeeDto> employees = employeeService.getAllEmployees();
		if (employees.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(employees);
	}

	@GetMapping("/{employeeId}")
	public ResponseEntity<?> getEmployee(@PathVariable("employeeId") Integer employeeId) {
		return ResponseEntity.ok(employeeService.getEmployee(employeeId));
	}

	@PostMapping
	public ResponseEntity<?> createEmployee(@RequestBody EmployeeDto employeeDTO) {
		EmployeeDto employee = employeeService.createEmployee(employeeDTO);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{employeeId}")
				.buildAndExpand(employee.getEmployeeId()).toUri();
		return ResponseEntity.created(location).body(new ApiResponseDto(true, "Employee created successfully!"));
	}

	@PutMapping
	public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDto employeeDTO) {
		employeeService.updateEmployee(employeeDTO);
		return ResponseEntity.ok(new ApiResponseDto(true, "Employee updated successfully!"));
	}

	@DeleteMapping("/{employeeId}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("employeeId") Integer employeeId) {
		employeeService.deleteEmployee(employeeId);
		return ResponseEntity.ok(new ApiResponseDto(true, "Employee deleted successfully!"));
	}

	@GetMapping("/search")
	public Page<EmployeeDto> search(@RequestParam(value = "employeeName", required = false) String employeeName,
			@RequestParam(value = "sortField", required = false, defaultValue = "employeeName") String sortField,
			@RequestParam(value = "order", required = false, defaultValue = "DSC") String order,
			@RequestParam(value = "perPage", required = false, defaultValue = "10") Integer perPage,
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {

		return employeeService.search(employeeName, sortField, order, new PageRequest(page, perPage));
	}
}
