package com.api.service;

import java.util.List;

import com.api.dto.LayoutDTO;
import com.api.exception.BadRequestException;

public interface LayoutService {

	List<LayoutDTO> getAllLayouts() throws BadRequestException;

	LayoutDTO getLayout(Integer layoutId) throws BadRequestException;

	LayoutDTO createLayout(LayoutDTO layoutDTO) throws BadRequestException;

	LayoutDTO updateLayout(LayoutDTO layoutDTO) throws BadRequestException;

	void deleteLayout(Integer layoutId) throws BadRequestException;

}
