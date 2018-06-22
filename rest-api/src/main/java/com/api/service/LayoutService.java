package com.api.service;

import java.util.List;

import com.api.dto.LayoutDto;

public interface LayoutService {

	List<LayoutDto> getAllLayouts();

	LayoutDto getLayout(Integer layoutId);

	LayoutDto createLayout(LayoutDto layoutDto);

	LayoutDto updateLayout(LayoutDto layoutDto);

	void deleteLayout(Integer layoutId);

}
