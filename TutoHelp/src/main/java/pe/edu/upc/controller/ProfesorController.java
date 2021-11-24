package pe.edu.upc.controller;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@RequestMapping("/delete")
	public String delete(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				sService.delete(id);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se pudo eliminar");
		}
		model.put("listaProfesores", sService.list());

		return "profesor/listProfesores";
	}

	@RequestMapping("/update/{id}")
	public String goUpdate(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Profesor> profesor = sService.listarId(id);
		if (profesor == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrio un error");
			return "profesor/profesorMOD";
		} else {
			model.addAttribute("profesor", profesor);
			return "profesor/profesorMOD";
		}
	}
	
	@PostMapping("/save2")
	public String save(@Valid Profesor profesor, BindingResult binRes, Model model, SessionStatus status)
			throws Exception {
		if (binRes.hasErrors()) {
			return "profesor/profesorMOD";
		} else {
			sService.insert2(profesor);
			model.addAttribute("mensaje", "Se actualizó correctamente");
			status.setComplete();

		}
		model.addAttribute("profesor", new Profesor());
		return "redirect:/profesores/list";
	}
}
