package com.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.DomainDTO;
import com.api.entity.Domain;
import com.api.exception.BadRequestException;
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
	public List<DomainDTO> getAllDomains() throws BadRequestException {
		List<Domain> domains = domainRepository.findAll();
		if (domains.isEmpty()) {
			return new ArrayList<>();
		}
		return domains.stream().map(domain -> DTOConverter.convertDomain(domain)).collect(Collectors.toList());
	}

	@Override
	public DomainDTO getDomain(Integer domainId) throws BadRequestException {
		if (NumberUtils.isEmpty(domainId)) {
			throw new BadRequestException("Unidentified companyId");
		}

		Domain domain = domainRepository.findOne(domainId);
		if (Objects.isNull(domain)) {
			throw new BadRequestException("Domain with " + domainId + " is no exists.");
		}
		return DTOConverter.convertDomain(domain);
	}

	@Override
	public DomainDTO createDomain(DomainDTO domainDTO) throws BadRequestException {
		return DTOConverter.convertDomain(domainRepository.save(DAOConverter.convertDomain(domainDTO)));
	}

	@Override
	public DomainDTO updateDomain(DomainDTO domainDTO) throws BadRequestException {
		Integer domainId = domainDTO.getDomainId();
		if (NumberUtils.isEmpty(domainId)) {
			throw new BadRequestException("Unidentified domainId.");
		}

		Domain domain = domainRepository.findOne(domainId);
		if (Objects.isNull(domain)) {
			throw new BadRequestException("Domain with " + domainId + " is no longer exists.");
		}

		domain.setId(domainId);
		domain.setDomainName(domainDTO.getDomainName());
		return DTOConverter.convertDomain(domainRepository.save(domain));
	}

	@Override
	public void deleteDomain(Integer domainId) throws BadRequestException {
		if (NumberUtils.isEmpty(domainId)) {
			throw new BadRequestException("Unidentified domainId.");
		}

		Domain domain = domainRepository.findOne(domainId);
		if (Objects.isNull(domain)) {
			throw new BadRequestException("Domain with " + domainId + " is no exists.");
		}
		domainRepository.delete(domainId);
	}

}
