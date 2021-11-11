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

}
