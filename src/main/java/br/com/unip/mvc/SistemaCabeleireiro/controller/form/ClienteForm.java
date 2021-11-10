package br.com.unip.mvc.SistemaCabeleireiro.controller.form;

import javax.persistence.Column;

import org.springframework.lang.NonNull;

import br.com.unip.mvc.SistemaCabeleireiro.model.entity.Cliente;


public class ClienteForm {

	@NonNull @Column(length = 45)
	private String nome;
	
	@NonNull @Column(length = 45)
	private String sexo;
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public Cliente converter() {
		return new Cliente(nome, sexo);
	}
	
	
}
