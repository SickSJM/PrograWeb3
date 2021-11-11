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

import pe.edu.upc.entity.Tipodeusuario;
import pe.edu.upc.service.ITipodeusuarioService;
import pe.edu.upc.service.IUserService;

@Controller
@RequestMapping("/tipodeusuarios")
public class TipodeusuarioController {

	@Autowired
	private IUserService uService;
	@Autowired
	private ITipodeusuarioService rService;

	@GetMapping("/new")
	public String newRole(Model model) {
		model.addAttribute("tipodeusuario", new Tipodeusuario());
		model.addAttribute("listaUsuarios", uService.list());
		return "tipodeusuario/tipodeusuario";
	}

	@PostMapping("/save")
	public String saveRole(@Valid Tipodeusuario tipodeusuario, BindingResult result, Model model, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "/tipodeusuario/tipodeusuario";
		} else {
			rService.insert(tipodeusuario);
			model.addAttribute("mensaje", "Se guardó correctamente");
			status.setComplete();
		}
		model.addAttribute("listaTipodeusuarios", rService.list());

		return "/tipodeusuario/tipodeusuario";

	}

	@GetMapping("/list")
	public String listRole(Model model) {
		try {
			model.addAttribute("tipodeusuario", new Tipodeusuario());
			model.addAttribute("listaTipodeusuarios", rService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/tipodeusuario/listTipodeusuario";
	}

}
