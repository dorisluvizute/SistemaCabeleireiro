package br.com.unip.mvc.SistemaCabeleireiro.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.unip.mvc.SistemaCabeleireiro.model.Pedido;
import br.com.unip.mvc.SistemaCabeleireiro.repository.PedidoRepository;

@Controller
public class HomeController {

	@Autowired
	PedidoRepository pedidoRepository;
	
	@GetMapping("/home")
	public String home(Model model) {
		List<Pedido> pedidos = pedidoRepository.recuperaTodosOsPedidos();
		
		model.addAttribute("pedidos", pedidos);
		return "home";
	}
}
