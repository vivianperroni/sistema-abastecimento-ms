package com.ifms.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifms.dto.TipoDTO;
import com.ifms.entities.Tipo;
import com.ifms.repositories.TipoRepository;
import com.ifms.services.exceptions.DataBaseException;
import com.ifms.services.exceptions.ResourceNotFoundException;

@Service
public class TipoService {

	@Autowired
	private TipoRepository repository;

	@Transactional(readOnly = true)
	public List<TipoDTO> findAll() {
		List<Tipo> list = repository.findAll();
		return list.stream().map(Tipo -> new TipoDTO(Tipo))
				            .collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public TipoDTO findById(Long id) {
		java.util.Optional<Tipo> obj = repository.findById(id);
		Tipo Tipo = obj.orElseThrow(() -> new ResourceNotFoundException(
				"O Tipo solicitado não foi localizado."));
		return new TipoDTO(Tipo);
	}

	@Transactional
	public TipoDTO insert(TipoDTO dto) {
		Tipo entity = new Tipo();
		entity.setDescricao(dto.getDescricao());
		entity = repository.save(entity);
		return new TipoDTO(entity);
	}

	@Transactional
	public TipoDTO update(Long id, TipoDTO dto) {
		try {
			Tipo entity = repository.getOne(id);
			entity.setDescricao(dto.getDescricao());
			entity = repository.save(entity);
			return new TipoDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("O id do Tipo não foi localizado");
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Não foi possivel deletar, o id do Tipo não foi localizado");
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Não foi possivel deletar o Tipo");
		}
		repository.deleteById(id);
		
	}
}
