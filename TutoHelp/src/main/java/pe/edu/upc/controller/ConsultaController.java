package pe.edu.upc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entity.Consulta;
import pe.edu.upc.service.IConsultaService;
import pe.edu.upc.service.IUserService;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

	@Autowired
	private IUserService uService;
	@Autowired
	private IConsultaService sService;

	@GetMapping("/new")
	public String newSoporte(Model model) {
		model.addAttribute("consulta", new Consulta());
		model.addAttribute("listaUsuarios", uService.list());
		return "consulta/consulta";
	}

	@PostMapping("/save")
	public String saveConsulta(@Valid Consulta consulta, BindingResult result, Model model, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "/consulta/consulta";
		} else {
			sService.insert(consulta);
			model.addAttribute("mensaje", "Se guardó correctamente");
			status.setComplete();
		}
		model.addAttribute("listaConsultas", sService.list());

		return "/consulta/consulta";

	}

	@GetMapping("/list")
	public String listConsultas(Model model) {
		try {
			model.addAttribute("consulta", new Consulta());
			model.addAttribute("listaConsultas", sService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/consulta/listConsultas";
	}

}
