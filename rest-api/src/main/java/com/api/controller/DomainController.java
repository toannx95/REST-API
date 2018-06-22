package com.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.dto.ApiResponseDto;
import com.api.dto.DomainDto;
import com.api.service.DomainService;

@RestController
@RequestMapping("/domains")
public class DomainController {

	@Autowired
	private DomainService domainService;

	@GetMapping
	public ResponseEntity<?> getAllDomains() {
		List<DomainDto> domains = domainService.getAllDomains();
		if (domains.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(domains);
	}

	@GetMapping("/{domainId}")
	public ResponseEntity<?> getDomain(@PathVariable("domainId") Integer domainId) {
		return ResponseEntity.ok(domainService.getDomain(domainId));
	}

	@PostMapping
	public ResponseEntity<?> createDomain(@RequestBody DomainDto domainDTO) {
		DomainDto domain = domainService.createDomain(domainDTO);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{domainId}")
				.buildAndExpand(domain.getDomainId()).toUri();
		return ResponseEntity.created(location).body(new ApiResponseDto(true, "Domain created successfully!"));

	}

	@PutMapping
	public ResponseEntity<?> updateDomain(@RequestBody DomainDto domainDTO) {
		domainService.updateDomain(domainDTO);
		return ResponseEntity.ok(new ApiResponseDto(true, "Domain updated successfully!"));
	}

	@DeleteMapping("/{domainId}")
	public ResponseEntity<?> deleteDomain(@PathVariable("domainId") Integer domainId) {
		domainService.deleteDomain(domainId);
		return ResponseEntity.ok(new ApiResponseDto(true, "Domain delete successfully!"));
	}

}
