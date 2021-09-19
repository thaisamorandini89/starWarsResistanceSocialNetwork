package br.com.letscode.starWarsResistanceSocialNetwork.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.letscode.starWarsResistanceSocialNetwork.dto.LocalizacaoDTO;
import br.com.letscode.starWarsResistanceSocialNetwork.services.LocalizacaoService;

@RestController
@RequestMapping(value = "/localizacoes")
public class LocalizacaoResource {

	@Autowired
	private LocalizacaoService service;

	@GetMapping
	public ResponseEntity<List<LocalizacaoDTO>> findAll() {
		List<LocalizacaoDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<LocalizacaoDTO> findById(@PathVariable Long id) {
		LocalizacaoDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping
	public ResponseEntity<LocalizacaoDTO> insert(@RequestBody LocalizacaoDTO dto){
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	
}
