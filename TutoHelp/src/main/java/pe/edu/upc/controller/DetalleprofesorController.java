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

import pe.edu.upc.entities.Detalleprofesor;
import pe.edu.upc.serviceinterface.IDetalleprofesorService;
import pe.edu.upc.serviceinterface.IProfesorService;
import pe.edu.upc.serviceinterface.IUsuarioService;

@Controller
@RequestMapping("/detalleprofesores")
public class DetalleprofesorController {

	@Autowired
	private IUsuarioService uService;
	@Autowired
	private IProfesorService pService;
	@Autowired
	private IDetalleprofesorService sService;

	@GetMapping("/new")
	public String newDetalleprofesor(Model model) {
		model.addAttribute("detalleprofesor", new Detalleprofesor());
		model.addAttribute("listaUsuarios", uService.list());
		model.addAttribute("listaProfesores", pService.list());
		return "detalleprofesor/detalleprofesor";
	}

	@PostMapping("/save")
	public String saveDetalleprofesor(@Valid Detalleprofesor detalleprofesor, BindingResult result, Model model, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaUsuarios", uService.list());
			model.addAttribute("listaProfesores", pService.list());
			return "/detalleprofesor/detalleprofesor";
		} else {
			model.addAttribute("listaUsuarios", uService.list());
			model.addAttribute("listaProfesores", pService.list());
			sService.insert(detalleprofesor);
			model.addAttribute("mensaje", "Se guardó correctamente");
			status.setComplete();
		}
		model.addAttribute("listaDetalleprofesores", sService.list());

		return "/detalleprofesor/listDetalleprofesor";

	}

	@GetMapping("/list")
	public String listDetalleprofesor(Model model) {
		try {
			model.addAttribute("detalleprofesor", new Detalleprofesor());
			model.addAttribute("listaDetalleprofesores", sService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/detalleprofesor/listDetalleprofesor";
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
		model.put("listaDetalleprofesores", sService.list());


		return "detalleprofesor/listDetalleprofesor";
	}
	@GetMapping("/detalle/{id}")
	public String detailsPrf(@PathVariable(value = "id") int id, Model model) {
		try {
			model.addAttribute("listaUsuarios", uService.list());
			model.addAttribute("listaProfesores", pService.list());
			Optional<Detalleprofesor> detalleprofesor = sService.listarId(id);
			if (!detalleprofesor.isPresent()) {
				model.addAttribute("info", "DetalleProfesor no existe");
				return "redirect:/detalleprofesores/list";
			} else {
				
				model.addAttribute("detalleprofesor", detalleprofesor.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/detalleprofesor/update";
	}
}
