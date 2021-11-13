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

import pe.edu.upc.entity.Soporte;
import pe.edu.upc.service.ISoporteService;
import pe.edu.upc.service.IUserService;

@Controller
@RequestMapping("/soportes")
public class SoporteController {

	@Autowired
	private IUserService uService;
	@Autowired
	private ISoporteService sService;

	@GetMapping("/new")
	public String newSoporte(Model model) {
		model.addAttribute("soporte", new Soporte());
		model.addAttribute("listaUsuarios", uService.list());
		return "soporte/soporte";
	}

	@PostMapping("/save")
	public String saveSoporte(@Valid Soporte soporte, BindingResult result, Model model, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "/soporte/soporte";
		} else {
			model.addAttribute("listaUsuarios", uService.list());
			sService.insert(soporte);
			model.addAttribute("mensaje", "Se guardó correctamente");
			status.setComplete();
		}
		model.addAttribute("listaSoportes", sService.list());

		return "/soporte/soporte";

	}

	@GetMapping("/list")
	public String listSoporte(Model model) {
		try {
			model.addAttribute("soporte", new Soporte());
			model.addAttribute("listaSoportes", sService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/soporte/listSoporte";
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
		model.put("listaSoportes", sService.list());

//		return "redirect:/categories/list";
		return "soporte/listSoporte";
	}
	@GetMapping("/detalle/{id}")
	public String detailsSoporte(@PathVariable(value = "id") int id, Model model) {
		try {
			model.addAttribute("listaUsuarios", uService.list());
			Optional<Soporte> soporte = sService.listarId(id);
			if (!soporte.isPresent()) {
				model.addAttribute("info", "Usuario no existe");
				return "redirect:/soportes/list";
			} else {
				
				model.addAttribute("soporte", soporte.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/soporte/update";
	}

}
