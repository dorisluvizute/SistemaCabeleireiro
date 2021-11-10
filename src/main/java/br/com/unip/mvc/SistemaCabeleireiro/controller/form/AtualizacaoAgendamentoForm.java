package br.com.unip.mvc.SistemaCabeleireiro.controller.form;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;

import br.com.unip.mvc.SistemaCabeleireiro.model.entity.Agendamento;
import br.com.unip.mvc.SistemaCabeleireiro.model.entity.Cabeleireiro;
import br.com.unip.mvc.SistemaCabeleireiro.repository.AgendamentoRepository;
import br.com.unip.mvc.SistemaCabeleireiro.repository.CabeleireiroRepository;
import br.com.unip.mvc.SistemaCabeleireiro.repository.ClienteRepository;

public class AtualizacaoAgendamentoForm {
	
	private Long idCabeleireiro;
	
	@Column(length = 45)
	private String servico;	
	
	private Double valor;	
	
	private Integer dia;	
	
	private Integer mes;	
	
	private Integer ano;	
	
	private Integer hora;	
	
	private Integer minuto;

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
	
	
	public Agendamento atualizar(Long id, AgendamentoRepository agendamentoRepository, CabeleireiroRepository cabeleireiroRepository) {
		
		Agendamento agendamento = agendamentoRepository.getOne(id);
		
		Cabeleireiro cabeleireiro = cabeleireiroRepository.getOne(idCabeleireiro);
		
		agendamento.setCabeleireiro(cabeleireiro);
		agendamento.setServico(servico);
		agendamento.setValor(valor);
		agendamento.setDataAgendada(geraAgendamento());
		
		return agendamento;
	}
	
}
