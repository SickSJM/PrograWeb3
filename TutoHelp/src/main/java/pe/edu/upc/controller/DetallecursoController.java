package pe.edu.upc.controller;

//import java.text.ParseException;
//import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entities.Detallecurso;
import pe.edu.upc.serviceinterface.IDetallecursoService;
import pe.edu.upc.serviceinterface.IUsuarioService;
import pe.edu.upc.serviceinterface.ICursoService;
@Controller
@RequestMapping("/detallecursos")
public class DetallecursoController {

	@Autowired
	private IUsuarioService uService;
	@Autowired
	private ICursoService cService;
	
	@Autowired
	private IDetallecursoService dService;

	@GetMapping("/new")
	public String newRole(Model model) {
		model.addAttribute("detallecurso", new Detallecurso());
		model.addAttribute("listaUsuarios", uService.list());
		model.addAttribute("listaCursos", cService.list());
		return "detallecurso/detallecurso";
	}

	@PostMapping("/save")
	public String saveRole(@Valid Detallecurso detallecurso, BindingResult result, Model model, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "/detallecurso/detallecurso";
		} else {
			model.addAttribute("listaUsuarios", uService.list());
			model.addAttribute("listaCursos", cService.list());
			dService.insert(detallecurso);
			model.addAttribute("mensaje", "Se guardó correctamente");
			status.setComplete();
		}
		model.addAttribute("listaDetallecursos", dService.list());

		return "/detallecurso/listDetallecurso";
	}

	@GetMapping("/list")
	public String listRole(Model model) {
		try {
			model.addAttribute("detallecurso", new Detallecurso());
			model.addAttribute("listaDetallecursos", dService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/detallecurso/listDetallecurso";
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
			model.put("mensaje", "No se puede eliminar el rol");
		}
		model.put("listaDetallecursos", dService.list());

//		return "redirect:/categories/list";
		return "detallecurso/listDetallecurso";
	}

	@GetMapping("/detalle/{id}")
	public String detailsRole(@PathVariable(value = "id") int id, Model model) {
		try {
			model.addAttribute("listaUsuarios", uService.list());
			model.addAttribute("listaCursos", cService.list());
			Optional<Detallecurso> detallecurso = dService.listarId(id);
			if (!detallecurso.isPresent()) {
				model.addAttribute("info", "Usuario no existe");
				return "redirect:/detallecursos/list";
			} else {
				model.addAttribute("detallecurso", detallecurso.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/detallecurso/update";
	}
	
	@RequestMapping("/reporte1")
	public String categoryTopFrquency(Map<String, Object> model) {
		model.put("listaDetallecursos", dService.Reporte1());
		return "reports/reporte1";
	}
}
