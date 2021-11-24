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

import pe.edu.upc.entities.Comentario;
import pe.edu.upc.serviceinterface.IComentarioService;
import pe.edu.upc.serviceinterface.IUsuarioService;
import pe.edu.upc.serviceinterface.IConsultaService;
@Controller
@RequestMapping("/comentarios")
public class ComentarioController {

	@Autowired
	private IUsuarioService uService;
	@Autowired
	private IConsultaService cService;
	
	@Autowired
	private IComentarioService dService;

	@GetMapping("/new")
	public String newRole(Model model) {
		model.addAttribute("comentario", new Comentario());
		model.addAttribute("listaUsuarios", uService.list());
		model.addAttribute("listaConsultas", cService.list());
		return "comentario/comentario";
	}

	@PostMapping("/save")
	public String saveComentario(@Valid Comentario comentario, BindingResult result, Model model, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "/comentario/comentario";
		} else {
			model.addAttribute("listaUsuarios", uService.list());
			model.addAttribute("listaConsultas", cService.list());
			dService.insert(comentario);
			model.addAttribute("mensaje", "Se guardó correctamente");
			status.setComplete();
		}
		model.addAttribute("listaComentarios", dService.list());

		return "/comentario/listComentarios";
	}

	@GetMapping("/list")
	public String listComentarios(Model model) {
		try {
			model.addAttribute("comentario", new Comentario());
			model.addAttribute("listaComentarios", dService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/comentario/listComentarios";
	}
	
	@RequestMapping("/delete")
	public String delete(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				dService.delete(id);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar el comentario");
		}
		model.put("listaComentarios", dService.list());

		return "comentario/listComentarios";
	}

	@GetMapping("/detalle/{id}")
	public String detailsComentario(@PathVariable(value = "id") int id, Model model) {
		try {
			model.addAttribute("listaUsuarios", uService.list());
			model.addAttribute("listaConsultas", cService.list());
			Optional<Comentario> comentario = dService.listarId(id);
			if (!comentario.isPresent()) {
				model.addAttribute("info", "Comentario no existe");
				return "redirect:/comentarios/list";
			} else {
				model.addAttribute("comentario", comentario.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "comentario/update";
	}

}
