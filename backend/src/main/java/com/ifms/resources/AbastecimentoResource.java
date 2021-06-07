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

import com.ifms.dto.AbastecimentoDTO;
import com.ifms.services.AbastecimentoService;

@RestController
@RequestMapping(value = "/abastecimentos")
public class AbastecimentoResource {

	@Autowired
	private AbastecimentoService service;
	
	
	@GetMapping
	public ResponseEntity<List<AbastecimentoDTO>> findAll(){
		List<AbastecimentoDTO> lista = service.findAll();
		return ResponseEntity.ok().body(lista);
		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AbastecimentoDTO> findById(@PathVariable Long id){
		AbastecimentoDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<AbastecimentoDTO> insert(@RequestBody AbastecimentoDTO dto){
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<AbastecimentoDTO> update(@PathVariable Long id, @RequestBody AbastecimentoDTO dto){
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<AbastecimentoDTO> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}




