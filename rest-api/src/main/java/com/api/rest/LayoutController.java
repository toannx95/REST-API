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
import com.api.dto.GenericResponseDTO;
import com.api.dto.LayoutDTO;
import com.api.exception.BadRequestException;
import com.api.service.LayoutService;

@RestController
@RequestMapping("/layouts")
public class LayoutController {

	@Autowired
	private LayoutService layoutService;

	private GenericResponseDTO addLinkGenericResponseResource(Object object) {
		GenericResponseDTO genericResponseDTO = new GenericResponseDTO(HttpConstant.MESSAGE_SUCCESS, object);
		genericResponseDTO.add(linkTo(methodOn(LayoutController.class).getAllLayouts()).withSelfRel());
		return genericResponseDTO;
	}

	private GenericResponseDTO addLinkGenericResponseResource(String message) {
		GenericResponseDTO genericResponseDTO = new GenericResponseDTO(message);
		genericResponseDTO.add(linkTo(methodOn(LayoutController.class).getAllLayouts()).withSelfRel());
		return genericResponseDTO;
	}

	public static Resource<LayoutDTO> addLinkLayoutResource(LayoutDTO layoutDTO) {
		return new Resource<>(layoutDTO,
				linkTo(methodOn(LayoutController.class).getLayout(layoutDTO.getLayoutId())).withSelfRel());
	}

	@GetMapping
	public ResponseEntity<GenericResponseDTO> getAllLayouts() {
		try {
			List<LayoutDTO> layouts = layoutService.getAllLayouts();
			List<Resource<LayoutDTO>> resources = new ArrayList<>();
			layouts.stream().map(layout -> resources.add(addLinkLayoutResource(layout))).collect(Collectors.toList());
			return ResponseEntity.ok(addLinkGenericResponseResource(resources));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

	@GetMapping("/{layoutId}")
	public ResponseEntity<GenericResponseDTO> getLayout(@PathVariable("layoutId") Integer layoutId) {
		try {
			LayoutDTO layout = layoutService.getLayout(layoutId);
			return ResponseEntity.ok(addLinkGenericResponseResource(addLinkLayoutResource(layout)));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

	@PostMapping
	public ResponseEntity<GenericResponseDTO> createLayout(@RequestBody LayoutDTO layoutDTO) {
		try {
			LayoutDTO layout = layoutService.createLayout(layoutDTO);
			return ResponseEntity.ok(addLinkGenericResponseResource(addLinkLayoutResource(layout)));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

	@PutMapping
	public ResponseEntity<GenericResponseDTO> updateLayout(@RequestBody LayoutDTO layoutDTO) {
		try {
			LayoutDTO layout = layoutService.updateLayout(layoutDTO);
			return ResponseEntity.ok(addLinkGenericResponseResource(addLinkLayoutResource(layout)));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

	@DeleteMapping("/{layoutId}")
	public ResponseEntity<GenericResponseDTO> deleteLayout(@PathVariable("layoutId") Integer layoutId) {
		try {
			layoutService.deleteLayout(layoutId);
			return ResponseEntity.ok(addLinkGenericResponseResource(HttpConstant.MESSAGE_SUCCESS));
		} catch (BadRequestException e) {
			return ResponseEntity.ok(addLinkGenericResponseResource(e.getMessage()));
		}
	}

}
