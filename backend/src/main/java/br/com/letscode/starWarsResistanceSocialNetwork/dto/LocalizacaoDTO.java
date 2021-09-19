package br.com.letscode.starWarsResistanceSocialNetwork.dto;

import java.io.Serializable;

import br.com.letscode.starWarsResistanceSocialNetwork.model.Localizacao;

public class LocalizacaoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Double x;
	private Double y;
	private String base;
	
	public LocalizacaoDTO() {
	}

	public LocalizacaoDTO(Long id, Double x, Double y, String base) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.base = base;
	}
	
	public LocalizacaoDTO(Localizacao entity) {
		id = entity.getId();
		x = entity.getX();
		y = entity.getY();
		base = entity.getBase();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}
	
}
