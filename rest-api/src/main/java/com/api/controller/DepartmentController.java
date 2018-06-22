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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.dto.ApiResponseDto;
import com.api.dto.DepartmentDto;
import com.api.service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@GetMapping
	public ResponseEntity<?> getAllDeparments() {
		List<DepartmentDto> departments = departmentService.getAllDepartments();
		if (departments.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(departments);
	}

	@GetMapping("/{departmentId}")
	public ResponseEntity<?> getDepartment(@PathVariable("departmentId") Integer departmentId) {
		return ResponseEntity.ok(departmentService.getDepartment(departmentId));
	}

	@PostMapping
	public ResponseEntity<?> createDepartment(@RequestBody DepartmentDto departmentDTO) {
		DepartmentDto department = departmentService.createDepartment(departmentDTO);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{departmentId}")
				.buildAndExpand(department.getDepartmentId()).toUri();
		return ResponseEntity.created(location).body(new ApiResponseDto(true, "Department created successfully!"));
	}

	@PutMapping
	public ResponseEntity<?> updateDepartment(@RequestBody DepartmentDto departmentDTO) {
		departmentService.updateDepartment(departmentDTO);
		return ResponseEntity.ok(new ApiResponseDto(true, "Department updated successfully!"));
	}

	@DeleteMapping("/{departmentId}")
	public ResponseEntity<?> deleteDepartment(@PathVariable("departmentId") Integer departmentId) {
		departmentService.deleteDepartment(departmentId);
		return ResponseEntity.ok(new ApiResponseDto(true, "Department deleted successfully!"));
	}

}
