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

import pe.edu.upc.entity.PruebasCompartidas;
import pe.edu.upc.service.IPruebasCompartidasService;
import pe.edu.upc.service.IUserService;

@Controller
@RequestMapping("/pruebas")
public class PruebasCompartidasController {

	@Autowired
	private IUserService uService;
	@Autowired
	private IPruebasCompartidasService sService;

	@GetMapping("/new")
	public String newInvitacion(Model model) {
		model.addAttribute("prueba", new PruebasCompartidas());
		model.addAttribute("listaUsuarios", uService.list());
		return "prueba/prueba";
	}

	@PostMapping("/save")
	public String savePruebasCompartidas(@Valid PruebasCompartidas prueba, BindingResult result, Model model, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "/prueba/prueba";
		} else {
			model.addAttribute("listaUsuarios", uService.list());
			sService.insert(prueba);
			model.addAttribute("mensaje", "Se guardó correctamente");
			status.setComplete();
		}
		model.addAttribute("listaPruebas", sService.list());

		return "/prueba/prueba";

	}

	@GetMapping("/list")
	public String listPruebas(Model model) {
		try {
			model.addAttribute("prueba", new PruebasCompartidas());
			model.addAttribute("listaPruebas", sService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/prueba/listPruebas";
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
			model.put("mensaje", "No se puede eliminar la prueba");
		}
		model.put("listaPruebas", sService.list());

		return "prueba/listPruebas";
	}
	@GetMapping("/detalle/{id}")
	public String detailsPruebasCompartidas(@PathVariable(value = "id") int id, Model model) {
		try {
			model.addAttribute("listaPruebas", uService.list());
			Optional<PruebasCompartidas> prueba = sService.listarId(id);
			if (!prueba.isPresent()) {
				model.addAttribute("info", "Prueba no existe");
				return "redirect:/pruebas/list";
			} else {
				
				model.addAttribute("prueba", prueba.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/prueba/update";
	}

}
