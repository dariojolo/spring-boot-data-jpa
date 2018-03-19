package com.ideasideales.sp.app.controllers;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ideasideales.sp.app.models.dao.IClienteDao;
import com.ideasideales.sp.app.models.entities.Cliente;

import groovy.lang.Binding;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	@Qualifier("clienteDaoJpa")
	private IClienteDao clienteDao;

	@GetMapping("listar")
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clienteDao.findAll());
		return "listar";
	}
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("titulo", "Pagina de clientes");
		model.addAttribute("mensaje", "Bienvenido");
		return "index";
	}
	//Redireccionamiento forma 1
   	@GetMapping("/")
	public String redirect() {
		return "redirect:/clientes/index";
   	}
   	@GetMapping("/form")
	public String crear(Map<String, Object> model) {
   		Cliente cliente = new Cliente();
   		model.put("cliente",cliente);
		model.put("titulo", "Formulario de cliente");
   		return "form";
   	}
   	@GetMapping("/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
   		Cliente cliente = null;
   		if (id > 0) {
   		 	cliente = clienteDao.findOne(id);
   		}else {
   			return "redirect:/clientes/listar";
   		}
   		model.put("cliente",cliente);
		model.put("titulo", "Editar cliente");
   		return "form";
   	}
   	@PostMapping("/form")
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model) {
   		model.addAttribute("titulo", "Formulario de cliente");
   		if (result.hasErrors()){
   			return "form";
   		}
   		clienteDao.save(cliente);
   		return "redirect:/clientes/listar";
   	}
}
