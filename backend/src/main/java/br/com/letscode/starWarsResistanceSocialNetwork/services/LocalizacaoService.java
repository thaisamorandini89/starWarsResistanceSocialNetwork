package br.com.letscode.starWarsResistanceSocialNetwork.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.letscode.starWarsResistanceSocialNetwork.dto.LocalizacaoDTO;
import br.com.letscode.starWarsResistanceSocialNetwork.model.Localizacao;
import br.com.letscode.starWarsResistanceSocialNetwork.repositories.LocalizacaoRepostiory;
import br.com.letscode.starWarsResistanceSocialNetwork.services.exceptions.EntityNotFoundException;

@Service
public class LocalizacaoService {
	
	@Autowired
	private LocalizacaoRepostiory repository;
	
	@Transactional(readOnly = true)
	public List<LocalizacaoDTO> findAll(){
		List<Localizacao> list = repository.findAll();
		return list.stream().map(x -> new LocalizacaoDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public LocalizacaoDTO findById(Long id) {
		Optional<Localizacao> obj = repository.findById(id);
		Localizacao entity = obj.orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada"));
		return new LocalizacaoDTO(entity);
	}

	@Transactional(readOnly = true)
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
}
