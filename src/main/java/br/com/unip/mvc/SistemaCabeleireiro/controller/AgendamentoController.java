package br.com.unip.mvc.SistemaCabeleireiro.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.unip.mvc.SistemaCabeleireiro.controller.dto.AgendamentoDto;
import br.com.unip.mvc.SistemaCabeleireiro.controller.form.AgendamentoForm;
import br.com.unip.mvc.SistemaCabeleireiro.controller.form.AtualizacaoAgendamentoForm;
import br.com.unip.mvc.SistemaCabeleireiro.model.entity.Agendamento;
import br.com.unip.mvc.SistemaCabeleireiro.repository.AgendamentoRepository;
import br.com.unip.mvc.SistemaCabeleireiro.repository.CabeleireiroRepository;
import br.com.unip.mvc.SistemaCabeleireiro.repository.ClienteRepository;

@Controller
public class AgendamentoController {

	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	@Autowired
	private CabeleireiroRepository cabeleireiroRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/agendamento")
	public String buscaTodos(Model model) {
		List<Agendamento> agendamentos = agendamentoRepository.findAll();
		
		ArrayList<String> retorno = new ArrayList<String>();
		
		for (Agendamento agendamento : agendamentos) {
			retorno.add("ID: " + agendamento.getId() + " | Cliente: " + agendamento.getCliente().getNome() 
					+ " | Cabeleireiro: " + agendamento.getCabeleireiro().getNome() 
					+ " | Servico: " + agendamento.getServico() + " | Valor: " + agendamento.getValor()
					+ " | Data: " + agendamento.getDataAgendada());
		}
		
		model.addAttribute("agendamento", retorno);
		return "agendamento";
	}
	
//	@GetMapping("/agendamento/{servico}")
//	public String buscaPorNome(Model model, @PathVariable String servico) {
//		List<Agendamento> agendamentos = agendamentoRepository.findByServico(servico);
//		
//		ArrayList<String> retorno = new ArrayList<String>();
//		
//		for (Agendamento agendamento : agendamentos) {
//			retorno.add("ID: " + agendamento.getId() + " | Cliente: " + agendamento.getCliente().getNome() 
//					+ " | Cabeleireiro: " + agendamento.getCabeleireiro().getNome() 
//					+ " | Servico: " + agendamento.getServico() + " | Valor: " + agendamento.getValor()
//					+ " | Data: " + agendamento.getDataAgendada());
//		}
//		
//		model.addAttribute("agendamento", retorno);
//		return "agendamento";
//	}
	
	@GetMapping("/agendamento/{id}")
	public String buscaPorId(Model model, @PathVariable Long id){
		Agendamento agendamento = agendamentoRepository.getOne(id);
		
		try {
			
			String retorno = "ID: " + agendamento.getId() + " | Cliente: " + agendamento.getCliente().getNome() 
					+ " | Cabeleireiro: " + agendamento.getCabeleireiro().getNome() 
					+ " | Servico: " + agendamento.getServico() + " | Valor: " + agendamento.getValor()
					+ " | Data: " + agendamento.getDataAgendada();
			
			
			model.addAttribute("agendamento", retorno);
			return "agendamento";
			
		} catch (Exception e) {
			ResponseEntity.notFound().build();
			model.addAttribute("agendamento", "Não encontrou nenhum resultado.");
			return "agendamento";
		}
		 
		
	}
	
	@PostMapping("/agendamento/cadastrar")
	@Transactional
	public String cadastrar(Model model, @RequestBody @Validated AgendamentoForm form, UriComponentsBuilder uriBuilder){
		try{
			LocalDateTime dataAgendamento = form.geraAgendamento();
			Agendamento agendamento = form.converter(clienteRepository, cabeleireiroRepository, dataAgendamento);
			agendamentoRepository.save(agendamento);
			
			URI uri = uriBuilder.path("/agendamento/cadastrar/{id}").buildAndExpand(agendamento.getId()).toUri();
			ResponseEntity.created(uri).body(new AgendamentoDto(agendamento));
			
			model.addAttribute("validacao", "Cadastro realizado!");
			return "agendamentoCadastro";
					
		} catch (Exception e) {
			model.addAttribute("validacao", "Erro no cadastro.");
			return "agendamentoCadastro";
		}
		
		
	}
	
	@DeleteMapping("agendamento/deletar/{id}")
	@Transactional
	public String remover (Model model, @PathVariable Long id){
		Optional<Agendamento> optional = agendamentoRepository.findById(id);
		if (optional.isPresent()) {
			agendamentoRepository.deleteById(id);
			ResponseEntity.ok().build();
			
			model.addAttribute("validacao", "Agendamento deletado!");
			return "agendamentoDeletar";
		}		
		ResponseEntity.notFound().build();	
		model.addAttribute("validacao", "Erro ao deletar.");
		return "agendamentoDeletar";
	}
	
	@PutMapping("agendamento/atualizar/{id}")
	@Transactional
	public String atualizar(Model model, @PathVariable Long id, @RequestBody @Validated AtualizacaoAgendamentoForm form){
		Optional<Agendamento> optional = agendamentoRepository.findById(id);
		if (optional.isPresent()) {
			Agendamento agendamento = form.atualizar(id, agendamentoRepository, cabeleireiroRepository);
			ResponseEntity.ok(new AgendamentoDto(agendamento));
				
			model.addAttribute("validacao", "Agendamento atualizado!");
			return "agendamentoAtualizar";
		} 
		
		model.addAttribute("validacao", "Erro ao atualizar.");
		return "agendamentoAtualizar";				
	}
	
}
