package com.ifms.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifms.dto.EstadoDTO;
import com.ifms.entities.Estado;
import com.ifms.repositories.EstadoRepository;
import com.ifms.services.exceptions.DataBaseException;
import com.ifms.services.exceptions.ResourceNotFoundException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repository;

	@Transactional(readOnly = true)
	public List<EstadoDTO> findAll() {
		List<Estado> list = repository.findAll();
		return list.stream().map(Estado -> new EstadoDTO(Estado))
				            .collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public EstadoDTO findById(Long id) {
		java.util.Optional<Estado> obj = repository.findById(id);
		Estado Estado = obj.orElseThrow(() -> new ResourceNotFoundException(
				"O Estado solicitado não foi localizado."));
		return new EstadoDTO(Estado);
	}

	@Transactional
	public EstadoDTO insert(EstadoDTO dto) {
		Estado entity = new Estado();
		entity.setUF(dto.getUF());
		entity = repository.save(entity);
		return new EstadoDTO(entity);
	}

	@Transactional
	public EstadoDTO update(Long id, EstadoDTO dto) {
		try {
			Estado entity = repository.getOne(id);
			entity.setUF(dto.getUF());
			entity = repository.save(entity);
			return new EstadoDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("O id do Estado não foi localizado");
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Não foi possivel deletar, o id do Estado não foi localizado");
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Não foi possivel deletar o Estado");
		}
		repository.deleteById(id);
		
	}
}
