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

import pe.edu.upc.entities.Usuario;
import pe.edu.upc.serviceinterface.ITipoDeUsuarioService;
import pe.edu.upc.serviceinterface.IUsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	private IUsuarioService pService;
	@Autowired
	private ITipoDeUsuarioService cService;


	@GetMapping("/nuevo")
	public String newUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("listaTipoDeUsuarios", cService.list());
		return "usuario/usuario";
	}

	@GetMapping("/list")
	public String listUsuarios(Model model) {
		try {
			model.addAttribute("usuario", new Usuario());
			model.addAttribute("listaUsuarios", pService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "usuario/listUsuarios";
	}

	@PostMapping("/save")
	public String saveMarca(@Valid Usuario usuario, BindingResult binRes, Model model,SessionStatus status)
			throws Exception {
		if (binRes.hasErrors()) {
			model.addAttribute("listaTipoDeUsuarios", cService.list());
			return "usuario/usuario";
		} else {
			int rpta = pService.insert(usuario);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "usuario/usuario";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("usuario", new Usuario());
		return "redirect:/usuarios/list";
	}

}
