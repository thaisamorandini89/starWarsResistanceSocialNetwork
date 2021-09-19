package br.com.letscode.starWarsResistanceSocialNetwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.letscode.starWarsResistanceSocialNetwork.model.Localizacao;

@Repository
public interface LocalizacaoRepostiory extends JpaRepository<Localizacao, Long> {

}
