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

import pe.edu.upc.entities.Profesor;
import pe.edu.upc.serviceinterface.IProfesorService;

@Controller
@RequestMapping("/profesores")
public class ProfesorController {
	
	@Autowired
	private IProfesorService sService;

	@GetMapping("/nuevo")
	public String newProfesor(Model model) {
		model.addAttribute("profesor", new Profesor());
		return "profesor/profesor";
	}

	@GetMapping("/list")
	public String listProfesores(Model model) {
		try {
			model.addAttribute("profesor", new Profesor());
			model.addAttribute("listaProfesores", sService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "profesor/listProfesores";
	}

	@PostMapping("/save")
	public String saveMarca(@Valid Profesor profesor, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "profesor/profesor";
		} else {
			int rpta = sService.insert(profesor);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "profesor/profesor";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("profesor", new Profesor());
		return "redirect:/profesores/list";
	}
}
