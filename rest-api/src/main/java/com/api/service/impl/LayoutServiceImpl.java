package com.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.LayoutDto;
import com.api.entity.Layout;
import com.api.exception.BadRequestException;
import com.api.exception.NotFoundException;
import com.api.repository.LayoutRepository;
import com.api.service.LayoutService;
import com.api.util.NumberUtils;
import com.api.util.converter.DAOConverter;
import com.api.util.converter.DTOConverter;

@Service
public class LayoutServiceImpl implements LayoutService {

	@Autowired
	private LayoutRepository layoutRepository;

	@Override
	public List<LayoutDto> getAllLayouts() {
		List<Layout> layouts = layoutRepository.findAll();
		if (layouts.isEmpty()) {
			return new ArrayList<>();
		}
		return layouts.stream().map(layout -> DTOConverter.convertLayout(layout)).collect(Collectors.toList());
	}

	@Override
	public LayoutDto getLayout(Integer layoutId) {
		if (NumberUtils.isEmpty(layoutId)) {
			throw new BadRequestException("Unidentified layoutId!");
		}

		Layout layout = layoutRepository.findOne(layoutId);
		if (Objects.isNull(layout)) {
			throw new NotFoundException("Layout", "layoutId", layoutId);
		}
		return DTOConverter.convertLayout(layout);
	}

	@Override
	public LayoutDto createLayout(LayoutDto layoutDto) {
		return DTOConverter.convertLayout(layoutRepository.save(DAOConverter.convertLayout(layoutDto)));
	}

	@Override
	public LayoutDto updateLayout(LayoutDto layoutDto) {
		Integer layoutId = layoutDto.getLayoutId();
		if (NumberUtils.isEmpty(layoutId)) {
			throw new BadRequestException("Unidentified layoutId!");
		}

		Layout layout = layoutRepository.findOne(layoutId);
		if (Objects.isNull(layout)) {
			throw new NotFoundException("Layout", "layoutId", layoutId);
		}

		layout.setId(layoutId);
		layout.setLayoutName(layoutDto.getLayoutName());
		return DTOConverter.convertLayout(layoutRepository.save(layout));
	}

	@Override
	public void deleteLayout(Integer layoutId) {
		if (NumberUtils.isEmpty(layoutId)) {
			throw new BadRequestException("Unidentified layoutId!");
		}

		Layout layout = layoutRepository.findOne(layoutId);
		if (Objects.isNull(layout)) {
			throw new NotFoundException("Layout", "layoutId", layoutId);
		}
		layoutRepository.delete(layoutId);
	}

}
