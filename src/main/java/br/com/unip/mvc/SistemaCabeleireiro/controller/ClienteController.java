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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.unip.mvc.SistemaCabeleireiro.controller.dto.CabeleireiroDto;
import br.com.unip.mvc.SistemaCabeleireiro.controller.dto.ClienteDto;
import br.com.unip.mvc.SistemaCabeleireiro.controller.form.CabeleireiroForm;
import br.com.unip.mvc.SistemaCabeleireiro.controller.form.ClienteForm;
import br.com.unip.mvc.SistemaCabeleireiro.model.entity.Cliente;
import br.com.unip.mvc.SistemaCabeleireiro.model.entity.Cliente;
import br.com.unip.mvc.SistemaCabeleireiro.repository.ClienteRepository;
import br.com.unip.mvc.SistemaCabeleireiro.repository.ClienteRepository;

@Controller
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	
	@GetMapping("/cliente")
	public String buscaTodos(Model model) {
		List<Cliente> clientes = clienteRepository.findAll();
		
		ArrayList<String> retorno = new ArrayList<String>();
		
		for (Cliente cliente : clientes) {
			retorno.add("ID: " + cliente.getId() + " | Nome: " + cliente.getNome() + " | Sexo: " + cliente.getSexo());
		}
		
		model.addAttribute("cliente", retorno);
		return "cliente";
	}
	
	@GetMapping("/cliente/{nome}")
	public String buscaPorNome(Model model, @PathVariable String nome) {
		List<Cliente> clientes = clienteRepository.findByNome(nome);
		
		ArrayList<String> retorno = new ArrayList<String>();
		
		for (Cliente cliente : clientes) {
			retorno.add("ID: " + cliente.getId() + " | Nome: " + cliente.getNome() + " | Sexo: " + cliente.getSexo());
		}
		
		model.addAttribute("cliente", retorno);
		return "cliente";
	}
	
	@PostMapping("/cliente/cadastrar")
	@Transactional
	public String cadastrar(Model model, @RequestBody @Validated ClienteForm form, UriComponentsBuilder uriBuilder) throws Exception {
		try{
			Cliente cliente = form.converter();
			clienteRepository.save(cliente);
			
			URI uri = uriBuilder.path("/cliente/cadastrar/{id}").buildAndExpand(cliente.getId()).toUri();
			ResponseEntity.created(uri).body(new ClienteDto(cliente));
			
			model.addAttribute("validacao", "Cadastro realizado!");
			return "clienteCadastro";
					
		} catch (Exception e) {
			model.addAttribute("validacao", "Erro no cadastro.");
			return "clienteCadastro";
		}
		
		
	}	
	
	
	@DeleteMapping("cliente/deletar/{id}")
	@Transactional
	public String remover (Model model, @PathVariable Long id){
		Optional<Cliente> optional = clienteRepository.findById(id);
		if (optional.isPresent()) {
			clienteRepository.deleteById(id);
			ResponseEntity.ok().build();
			
			model.addAttribute("validacao", "Cliente deletado!");
			return "clienteDeletar";
		}		
		ResponseEntity.notFound().build();	
		model.addAttribute("validacao", "Erro ao deletar.");
		return "clienteDeletar";
	}
	
}
