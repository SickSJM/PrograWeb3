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

import pe.edu.upc.entities.TipoDeUsuario;
import pe.edu.upc.serviceinterface.ITipoDeUsuarioService;

@Controller
@RequestMapping("/tipodeusuarios")
public class TipoDeUsuarioController {
	
	@Autowired
	private ITipoDeUsuarioService cService;

	@GetMapping("/nuevo")
	public String newTipoDeUsuario(Model model) {
		model.addAttribute("tipodeusuario", new TipoDeUsuario());
		return "tipodeusuario/tipodeusuario";
	}

	@GetMapping("/list")
	public String listTipoDeUsuarios(Model model) {
		try {
			model.addAttribute("tipodeusuario", new TipoDeUsuario());
			model.addAttribute("listaTipoDeUsuario", cService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "tipodeusuario/listTipoDeUsuario";
	}

	@PostMapping("/save")
	public String saveMarca(@Valid TipoDeUsuario tipodeusuario, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "tipodeusuario/tipodeusuario";
		} else {
			int rpta = cService.insert(tipodeusuario);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "tipodeusuario/tipodeusuario";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("tipodeusuario", new TipoDeUsuario());
		return "redirect:/tipodeusuarios/list";
	}
}
