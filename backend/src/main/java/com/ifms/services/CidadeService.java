package com.ifms.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifms.dto.CidadeDTO;
import com.ifms.entities.Cidade;
import com.ifms.repositories.CidadeRepository;
import com.ifms.services.exceptions.DataBaseException;
import com.ifms.services.exceptions.ResourceNotFoundException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repository;

	@Transactional(readOnly = true)
	public List<CidadeDTO> findAll() {
		List<Cidade> list = repository.findAll();
		return list.stream().map(Cidade -> new CidadeDTO(Cidade))
				            .collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public CidadeDTO findById(Long id) {
		java.util.Optional<Cidade> obj = repository.findById(id);
		Cidade Cidade = obj.orElseThrow(() -> new ResourceNotFoundException(
				"O Cidade solicitado não foi localizado."));
		return new CidadeDTO(Cidade);
	}

	@Transactional
	public CidadeDTO insert(CidadeDTO dto) {
		Cidade entity = new Cidade();
		entity.setNome(dto.getNome());
		entity.setUF(dto.getUF());		
		entity = repository.save(entity);
		return new CidadeDTO(entity);
	}

	@Transactional
	public CidadeDTO update(Long id, CidadeDTO dto) {
		try {
			Cidade entity = repository.getOne(id);
			entity.setNome(dto.getNome());
			entity.setUF(dto.getUF());		
			entity = repository.save(entity);
			return new CidadeDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("O id do Cidade não foi localizado");
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Não foi possivel deletar, o id do Cidade não foi localizado");
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Não foi possivel deletar o Cidade");
		}
		repository.deleteById(id);
		
	}
}
