package com.ifms.resources;

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

import com.ifms.dto.MarcaDTO;
import com.ifms.services.MarcaService;

@RestController
@RequestMapping(value = "/marcas")
public class MarcaResource {

	@Autowired
	private MarcaService service;
	
	
	@GetMapping
	public ResponseEntity<List<MarcaDTO>> findAll(){
		List<MarcaDTO> lista = service.findAll();
		return ResponseEntity.ok().body(lista);
		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MarcaDTO> findById(@PathVariable Long id){
		MarcaDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<MarcaDTO> insert(@RequestBody MarcaDTO dto){
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<MarcaDTO> update(@PathVariable Long id, @RequestBody MarcaDTO dto){
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<MarcaDTO> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}




