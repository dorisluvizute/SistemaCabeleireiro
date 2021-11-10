package br.com.unip.mvc.SistemaCabeleireiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.unip.mvc.SistemaCabeleireiro.model.entity.Agendamento;
import br.com.unip.mvc.SistemaCabeleireiro.model.entity.Cabeleireiro;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
	
	List<Agendamento> findAll();
	
	List<Agendamento> findById(Integer idAgendamento);
	
	List<Agendamento> findByServico(String servico);	
	
	
}
