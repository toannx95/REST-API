package com.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.ProjectDTO;
import com.api.entity.Project;
import com.api.exception.BadRequestException;
import com.api.repository.ProjectRepository;
import com.api.service.ProjectService;
import com.api.util.NumberUtils;
import com.api.util.converter.DAOConverter;
import com.api.util.converter.DTOConverter;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public List<ProjectDTO> getAllProjects() throws BadRequestException {
		List<Project> projects = projectRepository.findAll();
		if (projects.isEmpty()) {
			return new ArrayList<>();
		}
		return projects.stream().map(project -> DTOConverter.convertProject(project)).collect(Collectors.toList());
	}

	@Override
	public ProjectDTO getProject(Integer projectId) throws BadRequestException {
		if (NumberUtils.isEmpty(projectId)) {
			throw new BadRequestException("Unidentified projectId");
		}

		Project project = projectRepository.findOne(projectId);
		if (Objects.isNull(project)) {
			throw new BadRequestException("Company with " + projectId + " is no exists.");
		}
		return DTOConverter.convertProject(project);
	}

	@Override
	public ProjectDTO createProject(ProjectDTO projectDTO) throws BadRequestException {
		return DTOConverter.convertProject(projectRepository.save(DAOConverter.convertProject(projectDTO)));
	}

	@Override
	public ProjectDTO updateProject(ProjectDTO projectDTO) throws BadRequestException {
		Integer projectId = projectDTO.getProjectId();

		if (NumberUtils.isEmpty(projectId)) {
			throw new BadRequestException("Unidentified projectId");
		}

		Project project = projectRepository.findOne(projectId);
		if (Objects.isNull(project)) {
			throw new BadRequestException("Company with " + projectId + " is no longer exists.");
		}

		project.setId(projectId);
		project.setProjectName(projectDTO.getProjectName());
		project.setDescription(projectDTO.getDescription());
		project.setStatus(projectDTO.getStatus());
		return DTOConverter.convertProject(projectRepository.save(project));
	}

	@Override
	public void deleteProject(Integer projectId) throws BadRequestException {
		if (NumberUtils.isEmpty(projectId)) {
			throw new BadRequestException("Unidentified projectId");
		}

		Project project = projectRepository.findOne(projectId);
		if (Objects.isNull(project)) {
			throw new BadRequestException("Company with " + projectId + " is no exists.");
		}
		projectRepository.delete(projectId);
	}

}
