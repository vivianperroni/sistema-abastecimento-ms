package com.ifms.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifms.dto.ModeloDTO;
import com.ifms.entities.Modelo;
import com.ifms.repositories.ModeloRepository;
import com.ifms.services.exceptions.DataBaseException;
import com.ifms.services.exceptions.ResourceNotFoundException;

@Service
public class ModeloService {

	@Autowired
	private ModeloRepository repository;

	@Transactional(readOnly = true)
	public List<ModeloDTO> findAll() {
		List<Modelo> list = repository.findAll();
		return list.stream().map(Modelo -> new ModeloDTO(Modelo))
				            .collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public ModeloDTO findById(Long id) {
		java.util.Optional<Modelo> obj = repository.findById(id);
		Modelo Modelo = obj.orElseThrow(() -> new ResourceNotFoundException(
				"O Modelo solicitado não foi localizado."));
		return new ModeloDTO(Modelo);
	}

	@Transactional
	public ModeloDTO insert(ModeloDTO dto) {
		Modelo entity = new Modelo();
		entity.setDescricao(dto.getDescricao());
		entity.setMarca(dto.getMarca());
		entity = repository.save(entity);
		return new ModeloDTO(entity);
	}

	@Transactional
	public ModeloDTO update(Long id, ModeloDTO dto) {
		try {
			Modelo entity = repository.getOne(id);
			entity.setDescricao(dto.getDescricao());
			entity.setMarca(dto.getMarca());
			entity = repository.save(entity);
			return new ModeloDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("O id do Modelo não foi localizado");
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Não foi possivel deletar, o id do Modelo não foi localizado");
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Não foi possivel deletar o Modelo");
		}
		repository.deleteById(id);
		
	}
}
