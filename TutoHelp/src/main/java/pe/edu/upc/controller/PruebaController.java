package pe.edu.upc.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entities.Prueba;
import pe.edu.upc.serviceinterface.IUsuarioService;
import pe.edu.upc.serviceinterface.IPruebaService;

@Controller
@RequestMapping("/pruebas")
public class PruebaController {
	@Autowired
	private IPruebaService pService;
	@Autowired
	private IUsuarioService cService;


	@GetMapping("/nuevo")
	public String newPrueba(Model model) {
		model.addAttribute("xprueba", new Prueba());
		model.addAttribute("listaUsuarios", cService.list());
		return "xprueba/xprueba";
	}

	@GetMapping("/list")
	public String listPruebas(Model model) {
		try {
			model.addAttribute("xprueba", new Prueba());
			model.addAttribute("listaPruebas", pService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "xprueba/listPruebas";
	}

	@PostMapping("/save")
	public String saveMarca(@Valid Prueba xprueba, BindingResult binRes, Model model,SessionStatus status)
			throws Exception {
		if (binRes.hasErrors()) {
			model.addAttribute("listaUsuarios", cService.list());
			return "xprueba/xprueba";
		} else {
			int rpta = pService.insert(xprueba);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "xprueba/xprueba";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("xprueba", new Prueba());
		return "redirect:/pruebas/list";
	}

}
