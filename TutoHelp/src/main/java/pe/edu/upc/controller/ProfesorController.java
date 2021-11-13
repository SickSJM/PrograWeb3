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

import pe.edu.upc.entity.Profesor;
import pe.edu.upc.service.IProfesorService;
import pe.edu.upc.service.IUserService;

@Controller
@RequestMapping("/profesores")
public class ProfesorController {

	@Autowired
	private IUserService uService;
	@Autowired
	private IProfesorService sService;

	@GetMapping("/new")
	public String newProfesor(Model model) {
		model.addAttribute("profesor", new Profesor());
		model.addAttribute("listaUsuarios", uService.list());
		return "profesor/profesor";
	}

	@PostMapping("/save")
	public String saveProfesor(@Valid Profesor profesor, BindingResult result, Model model, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "/profesor/profesor";
		} else {
			sService.insert(profesor);
			model.addAttribute("mensaje", "Se guardó correctamente");
			status.setComplete();
		}
		model.addAttribute("listaProfesores", sService.list());

		return "/profesor/profesor";

	}

	@GetMapping("/list")
	public String listProfesor(Model model) {
		try {
			model.addAttribute("profesor", new Profesor());
			model.addAttribute("listaProfesores", sService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/profesor/listProfesor";
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
			model.put("mensaje", "No se puede eliminar el rol");
		}
		model.put("listaProfesores", sService.list());

		return "profesor/listProfesor";
	}
	@GetMapping("/detalle/{id}")
	public String detailsProfesor(@PathVariable(value = "id") int id, Model model) {
		try {
			model.addAttribute("listaUsuarios", uService.list());
			Optional<Profesor> profesor = sService.listarId(id);
			if (!profesor.isPresent()) {
				model.addAttribute("info", "Usuario no existe");
				return "redirect:/profesores/list";
			} else {
				
				model.addAttribute("profesor", profesor.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/profesor/update";
	}
}
