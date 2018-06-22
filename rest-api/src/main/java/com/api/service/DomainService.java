package com.api.service;

import java.util.List;

import com.api.dto.DomainDto;

public interface DomainService {

	List<DomainDto> getAllDomains();

	DomainDto getDomain(Integer domainId);

	DomainDto createDomain(DomainDto domainDto);

	DomainDto updateDomain(DomainDto domainDto);

	void deleteDomain(Integer domainId);

}
