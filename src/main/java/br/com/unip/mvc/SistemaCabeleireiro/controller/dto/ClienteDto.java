package br.com.unip.mvc.SistemaCabeleireiro.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.unip.mvc.SistemaCabeleireiro.model.entity.Cliente;

public class ClienteDto {

	private Long id;
	private String nome;
	private String sexo;
	
	public ClienteDto(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.sexo = cliente.getSexo();
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
	
	public static List<ClienteDto> converter(List<Cliente> clientes) {		
		return clientes.stream().map(ClienteDto::new).collect(Collectors.toList());
	}
}
