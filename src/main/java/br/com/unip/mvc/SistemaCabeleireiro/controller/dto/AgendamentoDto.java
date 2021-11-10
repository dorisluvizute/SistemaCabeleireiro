package br.com.unip.mvc.SistemaCabeleireiro.controller.dto;

import java.time.LocalDateTime;

import br.com.unip.mvc.SistemaCabeleireiro.model.entity.Agendamento;
import br.com.unip.mvc.SistemaCabeleireiro.model.entity.Cabeleireiro;
import br.com.unip.mvc.SistemaCabeleireiro.model.entity.Cliente;

public class AgendamentoDto {

	private Long id;
	private Cliente cliente;
	private Cabeleireiro cabeleireiro;
	private String servico;
	private Double valor;
	private LocalDateTime dataAgendada;
	
	public AgendamentoDto() {
		super();
	}
	
	public AgendamentoDto(Agendamento agendamento) {
		this.id = agendamento.getId();
		this.cliente = agendamento.getCliente();
		this.cabeleireiro = agendamento.getCabeleireiro();
		this.servico = agendamento.getServico();
		this.valor = agendamento.getValor();
		this.dataAgendada = agendamento.getDataAgendada();	
	}

	public AgendamentoDto(Long id, Cliente cliente, Cabeleireiro cabeleireiro, String servico, Double valor,
			LocalDateTime dataAgendada) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.cabeleireiro = cabeleireiro;
		this.servico = servico;
		this.valor = valor;
		this.dataAgendada = dataAgendada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cabeleireiro getCabeleireiro() {
		return cabeleireiro;
	}

	public void setCabeleireiro(Cabeleireiro cabeleireiro) {
		this.cabeleireiro = cabeleireiro;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public LocalDateTime getDataAgendada() {
		return dataAgendada;
	}

	public void setDataAgendada(LocalDateTime dataAgendada) {
		this.dataAgendada = dataAgendada;
	}
	
	
}
