package com.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.ProjectDto;
import com.api.entity.Project;
import com.api.exception.BadRequestException;
import com.api.exception.NotFoundException;
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
	public List<ProjectDto> getAllProjects() {
		List<Project> projects = projectRepository.findAll();
		if (projects.isEmpty()) {
			return new ArrayList<>();
		}
		return projects.stream().map(project -> DTOConverter.convertProject(project)).collect(Collectors.toList());
	}

	@Override
	public ProjectDto getProject(Integer projectId) {
		if (NumberUtils.isEmpty(projectId)) {
			throw new BadRequestException("Unidentified projectId!");
		}

		Project project = projectRepository.findOne(projectId);
		if (Objects.isNull(project)) {
			throw new NotFoundException("Project", "projectId", projectId);
		}
		return DTOConverter.convertProject(project);
	}

	@Override
	public ProjectDto createProject(ProjectDto projectDto) {
		return DTOConverter.convertProject(projectRepository.save(DAOConverter.convertProject(projectDto)));
	}

	@Override
	public ProjectDto updateProject(ProjectDto projectDto) {
		Integer projectId = projectDto.getProjectId();

		if (NumberUtils.isEmpty(projectId)) {
			throw new BadRequestException("Unidentified projectId!");
		}

		Project project = projectRepository.findOne(projectId);
		if (Objects.isNull(project)) {
			throw new NotFoundException("Project", "projectId", projectId);
		}

		project.setId(projectId);
		project.setProjectName(projectDto.getProjectName());
		project.setDescription(projectDto.getDescription());
		project.setStatus(projectDto.getStatus());
		return DTOConverter.convertProject(projectRepository.save(project));
	}

	@Override
	public void deleteProject(Integer projectId) {
		if (NumberUtils.isEmpty(projectId)) {
			throw new BadRequestException("Unidentified projectId!");
		}

		Project project = projectRepository.findOne(projectId);
		if (Objects.isNull(project)) {
			throw new NotFoundException("Project", "projectId", projectId);
		}
		projectRepository.delete(projectId);
	}

}
