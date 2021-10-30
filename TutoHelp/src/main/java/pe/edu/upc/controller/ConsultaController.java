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

import pe.edu.upc.entities.Consulta;
import pe.edu.upc.serviceinterface.IUsuarioService;
import pe.edu.upc.serviceinterface.IConsultaService;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {
	@Autowired
	private IConsultaService pService;
	@Autowired
	private IUsuarioService cService;


	@GetMapping("/nuevo")
	public String newConsulta(Model model) {
		model.addAttribute("consulta", new Consulta());
		model.addAttribute("listaUsuarios", cService.list());
		return "consulta/consulta";
	}

	@GetMapping("/list")
	public String listConsultas(Model model) {
		try {
			model.addAttribute("consulta", new Consulta());
			model.addAttribute("listaConsultas", pService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "consulta/listConsultas";
	}

	@PostMapping("/save")
	public String saveMarca(@Valid Consulta consulta, BindingResult binRes, Model model,SessionStatus status)
			throws Exception {
		if (binRes.hasErrors()) {
			model.addAttribute("listaUsuarios", cService.list());
			return "consulta/consulta";
		} else {
			int rpta = pService.insert(consulta);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "consulta/consulta";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("consulta", new Consulta());
		return "redirect:/consultas/list";
	}

}
