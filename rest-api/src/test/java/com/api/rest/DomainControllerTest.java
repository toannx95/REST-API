package com.api.rest;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.apache.catalina.filters.CorsFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.api.controller.DomainController;
import com.api.dto.DomainDto;
import com.api.service.DomainService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class DomainControllerTest {

	private MockMvc mockMvc;

	@Mock
	private DomainService domainService;

	@InjectMocks
	private DomainController domainController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(domainController).addFilters(new CorsFilter()).build();
	}
	
	public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Test
	public void testGetAllDomains() throws Exception {
		DomainDto domainA = new DomainDto(1, "Domain A");
		DomainDto domainB = new DomainDto(2, "Domain B");
		List<DomainDto> domainDTOs = Arrays.asList(domainA, domainB);

		when(domainService.getAllDomains()).thenReturn(domainDTOs);
		mockMvc.perform(get("/domains"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.message").value("success"))
			.andExpect(jsonPath("$.data[0].domainId", is(1)))
			.andExpect(jsonPath("$.data[0].domainName", is("Domain A")))
			.andExpect(jsonPath("$.data[1].domainId", is(2)))
			.andExpect(jsonPath("$.data[1].domainName", is("Domain B")));

		verify(domainService, times(1)).getAllDomains();
		verifyNoMoreInteractions(domainService);
	}

	@Test
	public void testGetDomain() throws Exception {
		DomainDto domain = new DomainDto(1, "Domain A");

		when(domainService.getDomain(domain.getDomainId())).thenReturn(domain);
		mockMvc.perform(get("/domains/{id}", domain.getDomainId()))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.message").value("success"))
			.andExpect(jsonPath("$.data.domainId", is(1)))
			.andExpect(jsonPath("$.data.domainName", is("Domain A")));

		verify(domainService, times(1)).getDomain(domain.getDomainId());
		verifyNoMoreInteractions(domainService);
	}

	@Test
	public void testCreateDomain() throws Exception {
		DomainDto domain = new DomainDto("Domain A");
		
		when(domainService.createDomain(domain)).thenReturn(domain);
		mockMvc.perform(post("/domains")
							.contentType(MediaType.APPLICATION_JSON)
							.content(asJsonString(domain)))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.message").value("success"))
			.andExpect(jsonPath("$.data.domainName", is("Domain A")));
		
		verify(domainService, times(1)).createDomain(domain);
		verifyNoMoreInteractions(domainService);
	}
	
	@Test
	public void testUpdateDomain() throws Exception {
		DomainDto domain = new DomainDto(1, "Domain A");
		
		when(domainService.updateDomain(domain)).thenReturn(domain);
		mockMvc.perform(put("/domains")
							.contentType(MediaType.APPLICATION_JSON)
							.content(asJsonString(domain)))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.message").value("success"))
			.andExpect(jsonPath("$.data.domainId", is(1)))
			.andExpect(jsonPath("$.data.domainName", is("Domain A")));
		
		verify(domainService, times(1)).updateDomain(domain);
		verifyNoMoreInteractions(domainService);
	}
	
	@Test
	public void testDeleteDomain() throws Exception {
		DomainDto domain = new DomainDto(1, "Domain A");
		
		doNothing().when(domainService).deleteDomain(domain.getDomainId());
		mockMvc.perform(delete("/domains/{id}", domain.getDomainId()))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.message").value("success"));
		
		verify(domainService, times(1)).deleteDomain(domain.getDomainId());
		verifyNoMoreInteractions(domainService);
	}

}
