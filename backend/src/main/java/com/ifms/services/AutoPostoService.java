package com.ifms.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifms.dto.AutoPostoDTO;
import com.ifms.entities.AutoPosto;
import com.ifms.repositories.AutoPostoRepository;
import com.ifms.services.exceptions.DataBaseException;
import com.ifms.services.exceptions.ResourceNotFoundException;

@Service
public class AutoPostoService {

	@Autowired
	private AutoPostoRepository repository;

	@Transactional(readOnly = true)
	public List<AutoPostoDTO> findAll() {
		List<AutoPosto> list = repository.findAll();
		return list.stream().map(AutoPosto -> new AutoPostoDTO(AutoPosto))
				            .collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public AutoPostoDTO findById(Long id) {
		java.util.Optional<AutoPosto> obj = repository.findById(id);
		AutoPosto AutoPosto = obj.orElseThrow(() -> new ResourceNotFoundException(
				"O AutoPosto solicitado não foi localizado."));
		return new AutoPostoDTO(AutoPosto);
	}

	@Transactional
	public AutoPostoDTO insert(AutoPostoDTO dto) {
		AutoPosto entity = new AutoPosto();
		entity.setNomeFantasia(dto.getNomeFantasia());
		entity.setTelefone(dto.getTelefone());
		entity.setEmail(dto.getEmail());
		entity.setCNPJ(dto.getCnpj());
		entity.setEndereco(dto.getEndereco());
		entity.setCidade(dto.getCidade());
		entity = repository.save(entity);
		return new AutoPostoDTO(entity);
	}

	@Transactional
	public AutoPostoDTO update(Long id, AutoPostoDTO dto) {
		try {
			AutoPosto entity = repository.getOne(id);
			entity.setNomeFantasia(dto.getNomeFantasia());
			entity.setTelefone(dto.getTelefone());
			entity.setEmail(dto.getEmail());
			entity.setCNPJ(dto.getCnpj());
			entity.setEndereco(dto.getEndereco());
			entity.setCidade(dto.getCidade());
			entity = repository.save(entity);
			return new AutoPostoDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("O id do AutoPosto não foi localizado");
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Não foi possivel deletar, o id do AutoPosto não foi localizado");
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Não foi possivel deletar o AutoPosto");
		}
		repository.deleteById(id);
		
	}
}
