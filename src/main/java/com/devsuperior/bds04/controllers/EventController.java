package com.devsuperior.bds04.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.services.EventService;
import com.devsuperior.bds04.services.exceptions.ResourceNotFoundException;

import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/events")
public class EventController {

	@Autowired
	EventService service;
	
	@GetMapping
	public ResponseEntity<Page<EventDTO>> findAll(Pageable pageable) {
		Page<EventDTO> page = service.findAll(pageable);
		return ResponseEntity.ok().body(page);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
	@PostMapping
	public ResponseEntity<EventDTO> insert(@Valid @RequestBody EventDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EventDTO> update(@PathVariable Long id, @RequestBody EventDTO dto) {
		try {
			dto = service.update(id, dto);
			return ResponseEntity.ok().body(dto);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		
	}
}
