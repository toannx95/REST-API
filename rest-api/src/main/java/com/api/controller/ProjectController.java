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
import com.api.dto.ProjectDto;
import com.api.service.ProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@GetMapping
	public ResponseEntity<?> getAllProjects() {
		List<ProjectDto> projects = projectService.getAllProjects();
		if (projects.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(projects);
	}

	@GetMapping("/{projectId}")
	public ResponseEntity<?> getProject(@PathVariable("projectId") Integer projectId) {
		return ResponseEntity.ok(projectService.getProject(projectId));
	}

	@PostMapping
	public ResponseEntity<?> createProject(@RequestBody ProjectDto projectDTO) {
		ProjectDto project = projectService.createProject(projectDTO);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{projectId}")
				.buildAndExpand(project.getProjectId()).toUri();
		return ResponseEntity.created(location).body(new ApiResponseDto(true, "Project created successfully!"));
	}

	@PutMapping
	public ResponseEntity<?> updateProject(@RequestBody ProjectDto projectDTO) {
		projectService.updateProject(projectDTO);
		return ResponseEntity.ok(new ApiResponseDto(true, "Project updated successfully!"));
	}

	@DeleteMapping("/{projectId}")
	public ResponseEntity<?> deleteProject(@PathVariable("projectId") Integer projectId) {
		projectService.deleteProject(projectId);
		return ResponseEntity.ok(new ApiResponseDto(true, "Project deleted successfully!"));
	}

}
