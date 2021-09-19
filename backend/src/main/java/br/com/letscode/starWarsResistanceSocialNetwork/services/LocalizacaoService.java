package br.com.letscode.starWarsResistanceSocialNetwork.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.letscode.starWarsResistanceSocialNetwork.dto.LocalizacaoDTO;
import br.com.letscode.starWarsResistanceSocialNetwork.model.Localizacao;
import br.com.letscode.starWarsResistanceSocialNetwork.repositories.LocalizacaoRepostiory;
import br.com.letscode.starWarsResistanceSocialNetwork.services.exceptions.DatabaseException;
import br.com.letscode.starWarsResistanceSocialNetwork.services.exceptions.ResourceNotFoundException;

@Service
public class LocalizacaoService {

	@Autowired
	private LocalizacaoRepostiory repository;

	@Transactional(readOnly = true)
	public List<LocalizacaoDTO> findAll() {
		List<Localizacao> list = repository.findAll();
		return list.stream().map(x -> new LocalizacaoDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public LocalizacaoDTO findById(Long id) {
		Optional<Localizacao> obj = repository.findById(id);
		Localizacao entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada"));
		return new LocalizacaoDTO(entity);
	}

	@Transactional
	public LocalizacaoDTO insert(LocalizacaoDTO dto) {
		Localizacao entity = new Localizacao();
		copyToEntity(dto, entity);
		entity = repository.save(entity);
		return new LocalizacaoDTO(entity);
	}

	private void copyToEntity(LocalizacaoDTO dto, Localizacao entity) {
		entity.setX(dto.getX());
		entity.setY(dto.getY());
		entity.setBase(dto.getBase());
	}

	@Transactional
	public LocalizacaoDTO update(Long id, LocalizacaoDTO dto) {
		try {
			Localizacao entity = repository.getOne(id);
			copyToEntity(dto, entity);
			entity = repository.save(entity);
			return new LocalizacaoDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não encontrado " + id);
		}

	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id não encontrado " + id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Integridade violidade");
		}
		
	}
}
