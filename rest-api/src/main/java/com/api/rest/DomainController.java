package com.api.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.constant.HttpConstant;
import com.api.dto.DomainDTO;
import com.api.dto.GenericResponseDTO;
import com.api.exception.BadRequestException;
import com.api.service.DomainService;

@RestController
@RequestMapping("/domains")
public class DomainController {

	@Autowired
	private DomainService domainService;

	private GenericResponseDTO addLinkGenericResponseResource(Object object) {
		GenericResponseDTO genericResponseDTO = new GenericResponseDTO(HttpConstant.MESSAGE_SUCCESS, object);
		genericResponseDTO.add(linkTo(methodOn(DomainController.class).getAllDomains()).withSelfRel());
		return genericResponseDTO;
	}

	private GenericResponseDTO addLinkGenericResponseResource(String message) {
		GenericResponseDTO genericResponseDTO = new GenericResponseDTO(message);
		genericResponseDTO.add(linkTo(methodOn(DomainController.class).getAllDomains()).withSelfRel());
		return genericResponseDTO;
	}

	private static Resource<DomainDTO> addLinkDomainResource(DomainDTO domainDTO) {
		return new Resource<>(domainDTO,
				linkTo(methodOn(DomainController.class).getDomain(domainDTO.getDomainId())).withSelfRel());
	}

	@GetMapping
	public ResponseEntity<GenericResponseDTO> getAllDomains() {
		try {
			List<DomainDTO> domains = domainService.getAllDomains();
			List<Resource<DomainDTO>> resources = new ArrayList<>();
			domains.stream().map(domain -> resources.add(addLinkDomainResource(domain))).collect(Collectors.toList());
			return ResponseEntity.ok(addLinkGenericResponseResource(resources));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

	@GetMapping("/{domainId}")
	public ResponseEntity<GenericResponseDTO> getDomain(@PathVariable("domainId") Integer domainId) {
		try {
			DomainDTO domain = domainService.getDomain(domainId);
			return ResponseEntity.ok(addLinkGenericResponseResource(domain));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

	@PostMapping
	public ResponseEntity<GenericResponseDTO> createDomain(@RequestBody DomainDTO domainDTO) {
		try {
			DomainDTO domain = domainService.createDomain(domainDTO);
			return ResponseEntity.ok(addLinkGenericResponseResource(domain));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

	@PutMapping
	public ResponseEntity<GenericResponseDTO> updateDomain(@RequestBody DomainDTO domainDTO) {
		try {
			DomainDTO domain = domainService.updateDomain(domainDTO);
			return ResponseEntity.ok(addLinkGenericResponseResource(domain));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

	@DeleteMapping("/{domainId}")
	public ResponseEntity<GenericResponseDTO> deleteDomain(@PathVariable("domainId") Integer domainId) {
		try {
			domainService.deleteDomain(domainId);
			return ResponseEntity.ok(addLinkGenericResponseResource(HttpConstant.MESSAGE_SUCCESS));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

}
