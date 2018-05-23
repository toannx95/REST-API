package com.api.service;

import java.util.List;

import com.api.dto.ProjectDTO;
import com.api.exception.BadRequestException;

public interface ProjectService {

	List<ProjectDTO> getAllProjects() throws BadRequestException;

	ProjectDTO getProject(Integer projectId) throws BadRequestException;

	ProjectDTO createProject(ProjectDTO projectDTO) throws BadRequestException;

	ProjectDTO updateProject(ProjectDTO projectDTO) throws BadRequestException;

	void deleteProject(Integer projectId) throws BadRequestException;

}
