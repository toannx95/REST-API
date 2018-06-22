package com.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.DomainDto;
import com.api.entity.Domain;
import com.api.exception.BadRequestException;
import com.api.exception.NotFoundException;
import com.api.repository.DomainRepository;
import com.api.service.DomainService;
import com.api.util.NumberUtils;
import com.api.util.converter.DAOConverter;
import com.api.util.converter.DTOConverter;

@Service
public class DomainServiceImpl implements DomainService {

	@Autowired
	private DomainRepository domainRepository;

	@Override
	public List<DomainDto> getAllDomains() {
		List<Domain> domains = domainRepository.findAll();
		if (domains.isEmpty()) {
			return new ArrayList<>();
		}
		return domains.stream().map(domain -> DTOConverter.convertDomain(domain)).collect(Collectors.toList());
	}

	@Override
	public DomainDto getDomain(Integer domainId) {
		if (NumberUtils.isEmpty(domainId)) {
			throw new BadRequestException("Unidentified domainId!");
		}

		Domain domain = domainRepository.findOne(domainId);
		if (Objects.isNull(domain)) {
			throw new NotFoundException("Domain", "domainId", domainId);
		}
		return DTOConverter.convertDomain(domain);
	}

	@Override
	public DomainDto createDomain(DomainDto domainDto) {
		return DTOConverter.convertDomain(domainRepository.save(DAOConverter.convertDomain(domainDto)));
	}

	@Override
	public DomainDto updateDomain(DomainDto domainDto) {
		Integer domainId = domainDto.getDomainId();
		if (NumberUtils.isEmpty(domainId)) {
			throw new BadRequestException("Unidentified domainId!");
		}

		Domain domain = domainRepository.findOne(domainId);
		if (Objects.isNull(domain)) {
			throw new NotFoundException("Domain", "domainId", domainId);
		}

		domain.setId(domainId);
		domain.setDomainName(domainDto.getDomainName());
		return DTOConverter.convertDomain(domainRepository.save(domain));
	}

	@Override
	public void deleteDomain(Integer domainId) {
		if (NumberUtils.isEmpty(domainId)) {
			throw new BadRequestException("Unidentified domainId!");
		}

		Domain domain = domainRepository.findOne(domainId);
		if (Objects.isNull(domain)) {
			throw new NotFoundException("Domain", "domainId", domainId);
		}
		domainRepository.delete(domainId);
	}

}
