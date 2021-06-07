package com.ifms.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifms.dto.MarcaDTO;
import com.ifms.entities.Marca;
import com.ifms.repositories.MarcaRepository;
import com.ifms.services.exceptions.DataBaseException;
import com.ifms.services.exceptions.ResourceNotFoundException;

@Service
public class MarcaService {

	@Autowired
	private MarcaRepository repository;

	@Transactional(readOnly = true)
	public List<MarcaDTO> findAll() {
		List<Marca> list = repository.findAll();
		return list.stream().map(Marca -> new MarcaDTO(Marca))
				            .collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public MarcaDTO findById(Long id) {
		java.util.Optional<Marca> obj = repository.findById(id);
		Marca Marca = obj.orElseThrow(() -> new ResourceNotFoundException(
				"O Marca solicitado não foi localizado."));
		return new MarcaDTO(Marca);
	}

	@Transactional
	public MarcaDTO insert(MarcaDTO dto) {
		Marca entity = new Marca();
		entity.setDescricao(dto.getDescricao());
		entity = repository.save(entity);
		return new MarcaDTO(entity);
	}

	@Transactional
	public MarcaDTO update(Long id, MarcaDTO dto) {
		try {
			Marca entity = repository.getOne(id);
			entity.setDescricao(dto.getDescricao());
			entity = repository.save(entity);
			return new MarcaDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("O id do Marca não foi localizado");
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Não foi possivel deletar, o id do Marca não foi localizado");
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Não foi possivel deletar o Marca");
		}
		repository.deleteById(id);
		
	}
}
