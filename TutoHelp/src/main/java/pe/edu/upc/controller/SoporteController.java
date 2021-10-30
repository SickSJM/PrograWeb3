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
			model.addAttribute("listaUsuarios", cService.list());
			return "soporte/soporte";
		} else {
			int rpta = pService.insert(soporte);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "soporte/soporte";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("soporte", new Soporte());
		return "redirect:/soportes/list";
	}

}
