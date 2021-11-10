package br.com.unip.mvc.SistemaCabeleireiro.controller;

import java.net.URI;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.unip.mvc.SistemaCabeleireiro.controller.dto.CabeleireiroDto;
import br.com.unip.mvc.SistemaCabeleireiro.controller.form.CabeleireiroForm;
import br.com.unip.mvc.SistemaCabeleireiro.model.entity.Cabeleireiro;
import br.com.unip.mvc.SistemaCabeleireiro.repository.CabeleireiroRepository;

@Controller
public class CabeleireiroController {

	@Autowired
	private CabeleireiroRepository cabeleireiroRepository;
	
	
	@GetMapping("/cabeleireiro")
	public String buscaTodos(Model model) {
		List<Cabeleireiro> cabeleireiros = cabeleireiroRepository.findAll();
		
		ArrayList<String> retorno = new ArrayList<String>();
		
		for (Cabeleireiro cabeleireiro : cabeleireiros) {
			retorno.add("ID: " + cabeleireiro.getId() + " | Nome: " + cabeleireiro.getNome() + " | Sexo: " + cabeleireiro.getSexo());
		}
		
		model.addAttribute("cabeleireiro", retorno);
		return "cabeleireiro";
	}
	
	@GetMapping("/cabeleireiro/{nome}")
	public String buscaPorNome(Model model, @PathVariable String nome) {
		List<Cabeleireiro> cabeleireiros = cabeleireiroRepository.findByNome(nome);
		
		ArrayList<String> retorno = new ArrayList<String>();
		
		for (Cabeleireiro cabeleireiro : cabeleireiros) {
			retorno.add("ID: " + cabeleireiro.getId() + " | Nome: " + cabeleireiro.getNome() + " | Sexo: " + cabeleireiro.getSexo());
		}
		
		model.addAttribute("cabeleireiro", retorno);
		return "cabeleireiro";
	}
	
//	@GetMapping("/cabeleireiro/{id}")
//	public String buscaPorId(Model model, @PathVariable Long id){
//		Optional<Cabeleireiro> cabeleireiro = cabeleireiroRepository.findById(id);
//		if (cabeleireiro.isPresent()) {
//			ResponseEntity.ok(new CabeleireiroDto(cabeleireiro.get()));
//			model.addAttribute("cabeleireiro", cabeleireiro);
//			return "cabeleireiro";
//			
//		} 
//		ResponseEntity.notFound().build();
//		model.addAttribute("cabeleireiro", "Não encontrou nenhum resultado.");
//		return "cabeleireiro";s
//	}
	
	@PostMapping("/cabeleireiro/cadastrar")
	@Transactional
	public String cadastrar(Model model, @RequestBody @Validated CabeleireiroForm form, UriComponentsBuilder uriBuilder) throws Exception {
		try{
			Cabeleireiro cabeleireiro = form.converter();
			cabeleireiroRepository.save(cabeleireiro);
			
			URI uri = uriBuilder.path("/cabeleireiro/cadastrar/{id}").buildAndExpand(cabeleireiro.getId()).toUri();
			ResponseEntity.created(uri).body(new CabeleireiroDto(cabeleireiro));
			
			model.addAttribute("validacao", "Cadastro realizado!");
			return "cabeleireiroCadastro";
					
		} catch (Exception e) {
			model.addAttribute("validacao", "Erro no cadastro.");
			return "cabeleireiroCadastro";
		}
		
		
	}	
	
	
	@DeleteMapping("cabeleireiro/deletar/{id}")
	@Transactional
	public String remover (Model model, @PathVariable Long id){
		Optional<Cabeleireiro> optional = cabeleireiroRepository.findById(id);
		if (optional.isPresent()) {
			cabeleireiroRepository.deleteById(id);
			ResponseEntity.ok().build();
			
			model.addAttribute("validacao", "Cabeleireiro deletado!");
			return "cabeleireiroDeletar";
		}		
		ResponseEntity.notFound().build();	
		model.addAttribute("validacao", "Erro ao deletar.");
		return "cabeleireiroDeletar";
	}
}
