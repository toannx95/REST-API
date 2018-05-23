package com.api.service;

import java.util.List;

import com.api.dto.DomainDTO;
import com.api.exception.BadRequestException;

public interface DomainService {

	List<DomainDTO> getAllDomains() throws BadRequestException;

	DomainDTO getDomain(Integer domainId) throws BadRequestException;

	DomainDTO createDomain(DomainDTO domainDTO) throws BadRequestException;

	DomainDTO updateDomain(DomainDTO domainDTO) throws BadRequestException;

	void deleteDomain(Integer domainId) throws BadRequestException;

}
