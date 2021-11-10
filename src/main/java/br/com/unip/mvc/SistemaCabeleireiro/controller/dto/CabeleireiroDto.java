package br.com.unip.mvc.SistemaCabeleireiro.controller.dto;

import java.util.List;	
import java.util.stream.Collectors;

import br.com.unip.mvc.SistemaCabeleireiro.model.entity.Cabeleireiro;

public class CabeleireiroDto {

	private Long id;
	private String nome;
	private String sexo;
	
	
	
	public CabeleireiroDto() {
		super();
	}

	public CabeleireiroDto(Cabeleireiro cabeleireiro) {
		this.id = cabeleireiro.getId();
		this.nome = cabeleireiro.getNome();
		this.sexo = cabeleireiro.getSexo();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getSexo() {
		return sexo;
	}
	
	public List<CabeleireiroDto> converter(List<Cabeleireiro> cabeleireiros) {		
		return cabeleireiros.stream().map(CabeleireiroDto::new).collect(Collectors.toList());
	}
}
