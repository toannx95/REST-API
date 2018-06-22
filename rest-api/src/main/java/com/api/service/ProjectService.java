package com.api.service;

import java.util.List;

import com.api.dto.ProjectDto;

public interface ProjectService {

	List<ProjectDto> getAllProjects();

	ProjectDto getProject(Integer projectId);

	ProjectDto createProject(ProjectDto projectDto);

	ProjectDto updateProject(ProjectDto projectDto);

	void deleteProject(Integer projectId);

}
