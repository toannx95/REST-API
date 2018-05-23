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
import com.api.dto.GenericResponseDTO;
import com.api.dto.ProjectDTO;
import com.api.exception.BadRequestException;
import com.api.service.ProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	private GenericResponseDTO addLinkGenericResponseResource(Object object) {
		GenericResponseDTO genericResponseDTO = new GenericResponseDTO(HttpConstant.MESSAGE_SUCCESS, object);
		genericResponseDTO.add(linkTo(methodOn(ProjectController.class).getAllProjects()).withSelfRel());
		return genericResponseDTO;
	}

	private GenericResponseDTO addLinkGenericResponseResource(String message) {
		GenericResponseDTO genericResponseDTO = new GenericResponseDTO(message);
		genericResponseDTO.add(linkTo(methodOn(ProjectController.class).getAllProjects()).withSelfRel());
		return genericResponseDTO;
	}

	public static Resource<ProjectDTO> addLinkProjectResource(ProjectDTO projectDTO) {
		return new Resource<ProjectDTO>(projectDTO,
				linkTo(methodOn(ProjectController.class).getProject(projectDTO.getProjectId())).withSelfRel());
	}

	@GetMapping
	public ResponseEntity<GenericResponseDTO> getAllProjects() {
		try {
			List<ProjectDTO> projects = projectService.getAllProjects();
			List<Resource<ProjectDTO>> resources = new ArrayList<>();
			projects.stream().map(project -> resources.add(addLinkProjectResource(project)))
					.collect(Collectors.toList());
			return ResponseEntity.ok(addLinkGenericResponseResource(resources));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

	@GetMapping("/{projectId}")
	public ResponseEntity<GenericResponseDTO> getProject(@PathVariable("projectId") Integer projectId) {
		try {
			ProjectDTO project = projectService.getProject(projectId);
			return ResponseEntity.ok(addLinkGenericResponseResource(addLinkProjectResource(project)));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

	@PostMapping
	public ResponseEntity<GenericResponseDTO> createProject(@RequestBody ProjectDTO projectDTO) {
		try {
			ProjectDTO project = projectService.createProject(projectDTO);
			return ResponseEntity.ok(addLinkGenericResponseResource(addLinkProjectResource(project)));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

	@PutMapping
	public ResponseEntity<GenericResponseDTO> updateProject(@RequestBody ProjectDTO projectDTO) {
		try {
			ProjectDTO project = projectService.updateProject(projectDTO);
			return ResponseEntity.ok(addLinkGenericResponseResource(addLinkProjectResource(project)));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

	@DeleteMapping("/{projectId}")
	public ResponseEntity<GenericResponseDTO> deleteProject(@PathVariable("projectId") Integer projectId) {
		try {
			projectService.deleteProject(projectId);
			return ResponseEntity.ok(addLinkGenericResponseResource(HttpConstant.MESSAGE_SUCCESS));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

}
