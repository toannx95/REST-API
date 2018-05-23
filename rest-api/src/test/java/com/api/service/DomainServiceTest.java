package com.api.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.api.dto.DomainDTO;
import com.api.entity.Domain;
import com.api.repository.DomainRepository;
import com.api.service.impl.DomainServiceImpl;
import com.api.util.converter.DTOConverter;

@RunWith(MockitoJUnitRunner.class)
public class DomainServiceTest {

	@Mock
	private DomainRepository domainRepository;

	@InjectMocks
	private DomainServiceImpl domainServiceImpl;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAllDomains() throws Exception {
		Domain domainA = new Domain(1, "Domain A");
		Domain domainB = new Domain(2, "Domain B");
		List<Domain> domains = Arrays.asList(domainA, domainB);

		when(domainRepository.findAll()).thenReturn(domains);
		List<DomainDTO> domainDTOs = domainServiceImpl.getAllDomains();
		assertEquals(domains.size(), domainDTOs.size());
	}

	@Test
	public void testGetDomain() throws Exception {
		Domain domain = new Domain(1, "Domain A");

		when(domainRepository.findOne(domain.getId())).thenReturn(domain);
		DomainDTO domainDTO = domainServiceImpl.getDomain(domain.getId());
		assertEquals(domain.getId(), domainDTO.getDomainId());
		assertEquals(domain.getDomainName(), domainDTO.getDomainName());
	}

	@Test
	public void testCreateDomain() throws Exception {
		Domain domain = new Domain("Domain A");

		when(domainRepository.save(domain)).thenReturn(domain);
		
		DomainDTO domainDTO = domainServiceImpl.createDomain(DTOConverter.convertDomain(domain));

		assertEquals(domain.getDomainName(), domainDTO.getDomainName());
	}

	@Test
	public void testDeleteDomain() throws Exception {
		Domain domain = new Domain(1, "Domain A");

		when(domainRepository.findOne(domain.getId())).thenReturn(domain);
		doNothing().when(domainRepository).delete(domain.getId());
		domainServiceImpl.deleteDomain(domain.getId());

		verify(domainRepository, times(1)).findOne(domain.getId());
		verify(domainRepository, times(1)).delete(domain.getId());
	}

}
