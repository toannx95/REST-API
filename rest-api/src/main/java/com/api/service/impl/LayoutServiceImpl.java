package com.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.LayoutDTO;
import com.api.entity.Layout;
import com.api.exception.BadRequestException;
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
	public List<LayoutDTO> getAllLayouts() throws BadRequestException {
		List<Layout> layouts = layoutRepository.findAll();
		if (layouts.isEmpty()) {
			return new ArrayList<>();
		}
		return layouts.stream().map(layout -> DTOConverter.convertLayout(layout)).collect(Collectors.toList());
	}

	@Override
	public LayoutDTO getLayout(Integer layoutId) throws BadRequestException {
		if (NumberUtils.isEmpty(layoutId)) {
			throw new BadRequestException("Unidentified layoutId");
		}

		Layout layout = layoutRepository.findOne(layoutId);
		if (Objects.isNull(layout)) {
			throw new BadRequestException("Layout with " + layoutId + " is no exists.");
		}
		return DTOConverter.convertLayout(layout);
	}

	@Override
	public LayoutDTO createLayout(LayoutDTO layoutDTO) throws BadRequestException {
		return DTOConverter.convertLayout(layoutRepository.save(DAOConverter.convertLayout(layoutDTO)));
	}

	@Override
	public LayoutDTO updateLayout(LayoutDTO layoutDTO) throws BadRequestException {
		Integer layoutId = layoutDTO.getLayoutId();
		if (NumberUtils.isEmpty(layoutId)) {
			throw new BadRequestException("Unidentified layoutId");
		}

		Layout layout = layoutRepository.findOne(layoutId);
		if (Objects.isNull(layout)) {
			throw new BadRequestException("Layout with " + layoutId + " is no longer exists.");
		}

		layout.setId(layoutId);
		layout.setLayoutName(layoutDTO.getLayoutName());
		return DTOConverter.convertLayout(layoutRepository.save(layout));
	}

	@Override
	public void deleteLayout(Integer layoutId) throws BadRequestException {
		if (NumberUtils.isEmpty(layoutId)) {
			throw new BadRequestException("Unidentified layoutId");
		}

		Layout layout = layoutRepository.findOne(layoutId);
		if (Objects.isNull(layout)) {
			throw new BadRequestException("Layout with " + layoutId + " is no exists.");
		}
		layoutRepository.delete(layoutId);
	}

}
