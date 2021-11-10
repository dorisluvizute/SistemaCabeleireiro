package br.com.unip.mvc.SistemaCabeleireiro.controller.form;

import java.time.LocalDateTime;

import javax.persistence.Column;

import org.springframework.lang.NonNull;

import br.com.unip.mvc.SistemaCabeleireiro.model.entity.Agendamento;
import br.com.unip.mvc.SistemaCabeleireiro.model.entity.Cabeleireiro;
import br.com.unip.mvc.SistemaCabeleireiro.model.entity.Cliente;
import br.com.unip.mvc.SistemaCabeleireiro.repository.CabeleireiroRepository;
import br.com.unip.mvc.SistemaCabeleireiro.repository.ClienteRepository;

public class AgendamentoForm {

	@NonNull
	private Long idCliente;
	
	@NonNull
	private Long idCabeleireiro;
	
	@NonNull @Column(length = 45)
	private String servico;
	
	@NonNull
	private Double valor;	
	
	@NonNull
	private Integer dia;
	
	@NonNull
	private Integer mes;
	
	@NonNull
	private Integer ano;
	
	@NonNull
	private Integer hora;
	
	@NonNull
	private Integer minuto;
	

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Long getIdCabeleireiro() {
		return idCabeleireiro;
	}

	public void setIdCabeleireiro(Long idCabeleireiro) {
		this.idCabeleireiro = idCabeleireiro;
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

	public Integer getDia() {
		return dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getHora() {
		return hora;
	}

	public void setHora(Integer hora) {
		this.hora = hora;
	}

	public Integer getMinuto() {
		return minuto;
	}

	public void setMinuto(Integer minuto) {
		this.minuto = minuto;
	}
	
	
	public LocalDateTime geraAgendamento() {
		LocalDateTime agendamento = LocalDateTime.of(this.ano, this.mes, this.dia, this.hora, this.minuto);
		return agendamento;
	}
	
	
	public Agendamento converter(ClienteRepository clienteRepository, CabeleireiroRepository cabeleireiroRepository, LocalDateTime dataAgendada) {
		Cliente cliente = clienteRepository.getOne(idCliente);
		Cabeleireiro cabeleireiro = cabeleireiroRepository.getOne(idCabeleireiro);
		
		return new Agendamento(cliente, cabeleireiro, servico, valor, dataAgendada);
	}
	
}
