package pe.edu.upc.controller;

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

import pe.edu.upc.entity.Curso;
import pe.edu.upc.service.ICursoService;

@Controller
@RequestMapping("/cursos")
public class CursoController {

	@Autowired
	private ICursoService sService;

	@GetMapping("/new")
	public String newCurso(Model model) {
		model.addAttribute("curso", new Curso());
		return "curso/curso";
	}

	@PostMapping("/save")
	public String saveCurso(@Valid Curso curso, BindingResult result, Model model, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "/curso/curso";
		} else {
			sService.insert(curso);
			model.addAttribute("mensaje", "Se guardó correctamente");
			status.setComplete();
		}
		model.addAttribute("listaCursos", sService.list());

		return "/curso/curso";

	}

	@GetMapping("/list")
	public String listCursos(Model model) {
		try {
			model.addAttribute("curso", new Curso());
			model.addAttribute("listaCursos", sService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/curso/listCursos";
	}
	
	@RequestMapping("/delete")
	public String deleteConsulta(@RequestParam(value="id") Integer id, Model model) {
		sService.delete(id);
		return "redirect:/cursos/list";
	}
	@RequestMapping("/update/{id}")
	public String goUpdate(@PathVariable int id,Model model, RedirectAttributes objRedir) {
		Optional<Curso> cur=sService.listId(id);
		if(cur==null) {
			objRedir.addFlashAttribute("mensaje","ocurrio un error");
			return "/curso/curso";
		}else {
			model.addAttribute("curso",cur);
			return "/curso/curso";
		}
	}

}
