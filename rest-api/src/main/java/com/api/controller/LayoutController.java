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
import com.api.dto.LayoutDto;
import com.api.service.LayoutService;

@RestController
@RequestMapping("/layouts")
public class LayoutController {

	@Autowired
	private LayoutService layoutService;

	@GetMapping
	public ResponseEntity<?> getAllLayouts() {
		List<LayoutDto> layouts = layoutService.getAllLayouts();
		if (layouts.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(layouts);
	}

	@GetMapping("/{layoutId}")
	public ResponseEntity<?> getLayout(@PathVariable("layoutId") Integer layoutId) {
		return ResponseEntity.ok(layoutService.getLayout(layoutId));
	}

	@PostMapping
	public ResponseEntity<?> createLayout(@RequestBody LayoutDto layoutDTO) {
		LayoutDto layout = layoutService.createLayout(layoutDTO);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{layoutId}")
				.buildAndExpand(layout.getLayoutId()).toUri();
		return ResponseEntity.created(location).body(new ApiResponseDto(true, "Layout created successfully!"));
	}

	@PutMapping
	public ResponseEntity<?> updateLayout(@RequestBody LayoutDto layoutDTO) {
		layoutService.updateLayout(layoutDTO);
		return ResponseEntity.ok(new ApiResponseDto(true, "Layout updated successfully!"));
	}

	@DeleteMapping("/{layoutId}")
	public ResponseEntity<?> deleteLayout(@PathVariable("layoutId") Integer layoutId) {
		layoutService.deleteLayout(layoutId);
		return ResponseEntity.ok(new ApiResponseDto(true, "Layout deleted successfully!"));
	}

}
