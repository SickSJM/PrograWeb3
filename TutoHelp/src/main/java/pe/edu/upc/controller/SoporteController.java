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

import pe.edu.upc.entities.Soporte;
import pe.edu.upc.serviceinterface.IUsuarioService;
import pe.edu.upc.serviceinterface.ISoporteService;

@Controller
@RequestMapping("/soportes")
public class SoporteController {
	@Autowired
	private ISoporteService pService;
	@Autowired
	private IUsuarioService cService;


	@GetMapping("/nuevo")
	public String newSoporte(Model model) {
		model.addAttribute("soporte", new Soporte());
		model.addAttribute("listaUsuarios", cService.list());
		return "soporte/soporte";
	}

	@GetMapping("/list")
	public String listSoportes(Model model) {
		try {
			model.addAttribute("soporte", new Soporte());
			model.addAttribute("listaSoportes", pService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "soporte/listSoportes";
	}

	@PostMapping("/save")
	public String saveMarca(@Valid Soporte soporte, BindingResult binRes, Model model,SessionStatus status)
			throws Exception {
		if (binRes.hasErrors()) {
			return "/soporte/soporte";
		} else {
			model.addAttribute("listaUsuarios", cService.list());
			pService.insert(soporte);
			model.addAttribute("mensaje", "Se guardó correctamente");
			status.setComplete();
		}
		model.addAttribute("listaSoportes", pService.list());

		return "/soporte/soporte";
	}
	@RequestMapping("/delete")
	public String deleteSoporte(Map<String, Object> model,@RequestParam(value="id") Integer id) {
		try {
			if (id != null && id > 0) {
				pService.delete(id);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar el rol");
		}
		model.put("listaSoportes", pService.list());

//		return "redirect:/categories/list";
		return "soporte/listSoportes";
	}
	
	@RequestMapping("/detalle/{id}")
	public String detailsSoporte(@PathVariable(value = "id") int id, Model model) {
		try {
			model.addAttribute("listaUsuarios", cService.list());
			Optional<Soporte> soporte = pService.listId(id);
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
