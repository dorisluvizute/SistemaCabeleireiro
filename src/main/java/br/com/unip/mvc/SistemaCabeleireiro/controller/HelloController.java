package br.com.unip.mvc.SistemaCabeleireiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

	@GetMapping("/hello")
	public void hello(Model model) {
		model.addAttribute("nome", "mundo");
//		return "hello";
	}
}
