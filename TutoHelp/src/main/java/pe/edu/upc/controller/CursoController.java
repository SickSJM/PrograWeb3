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

import pe.edu.upc.entities.Curso;
import pe.edu.upc.serviceinterface.ICursoService;

@Controller
@RequestMapping("/cursos")
public class CursoController {
	
	@Autowired
	private ICursoService cService;

	@GetMapping("/nuevo")
	public String newCurso(Model model) {
		model.addAttribute("curso", new Curso());
		return "curso/curso";
	}

	@GetMapping("/list")
	public String listCursos(Model model) {
		try {
			model.addAttribute("curso", new Curso());
			model.addAttribute("listaCursos", cService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "curso/listCursos";
	}

	@PostMapping("/save")
	public String saveMarca(@Valid Curso curso, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "curso/curso";
		} else {
			int rpta = cService.insert(curso);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "tipodeusuario/tipodeusuario";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("curso", new Curso());
		return "redirect:/cursos/list";
	}
	
	@RequestMapping("/delete")
	public String delete(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				cService.delete(id);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se pudo eliminar");
		}
		model.put("listaCursos", cService.list());

		return "curso/listCursos";
	}

	@RequestMapping("/update/{id}")
	public String goUpdate(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Curso> curso = cService.listarId(id);
		if (curso == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrio un error");
			return "curso/curso";
		} else {
			model.addAttribute("curso", curso);
			return "/curso/curso";
		}
	}
}
