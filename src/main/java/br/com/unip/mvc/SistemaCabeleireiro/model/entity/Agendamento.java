package br.com.unip.mvc.SistemaCabeleireiro.model.entity;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.Entity;	
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Agendamento {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Cliente cliente;	
	
	@ManyToOne
	private Cabeleireiro cabeleireiro;	
	
	private String servico;
	
	private Double valor;
	
	private LocalDateTime dataAgendada;
	

	public Agendamento() {
	}


	public Agendamento(Long id, Cliente cliente, Cabeleireiro cabeleireiro, String servico, Double valor) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.cabeleireiro = cabeleireiro;
		this.servico = servico;
		this.valor = valor;
	}


	public Agendamento(Cliente cliente, Cabeleireiro cabeleireiro, String servico, Double valor, LocalDateTime dataAgendada) {
		super();
		this.cliente = cliente;
		this.cabeleireiro = cabeleireiro;
		this.servico = servico;
		this.valor = valor;
		this.dataAgendada = dataAgendada;
	}


	public Agendamento(Long id, Cliente cliente, Cabeleireiro cabeleireiro, String servico, Double valor,
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
