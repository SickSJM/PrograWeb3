package pe.edu.upc.controller;

//import java.text.ParseException;
//import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entity.Tipodeusuario;
import pe.edu.upc.service.ITipodeusuarioService;
import pe.edu.upc.service.IUserService;

@Controller
@RequestMapping("/tipodeusuarios")
public class TipoDeUsuarioController {

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
			int rpta = rService.insert(tipodeusuario);
			if (rpta > 0) {
				model.addAttribute("listaUsuarios", uService.list());
				model.addAttribute("mensaje", "Ya existe");
				return "/tipodeusuario/tipodeusuario";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
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
	
	@RequestMapping("/delete")
	public String delete(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				rService.delete(id);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar el rol");
		}
		model.put("listaTipodeusuarios", rService.list());

//		return "redirect:/categories/list";
		return "tipodeusuario/listTipodeusuario";
	}

	@GetMapping("/detalle/{id}")
	public String detailsRole(@PathVariable(value = "id") int id, Model model) {
		try {
			model.addAttribute("listaUsuarios", uService.list());
			Optional<Tipodeusuario> tipodeusuario = rService.listarId(id);
			if (!tipodeusuario.isPresent()) {
				model.addAttribute("info", "Usuario no existe");
				return "redirect:/tipodeusuarios/list";
			} else {
				model.addAttribute("tipodeusuario", tipodeusuario.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/tipodeusuario/update";
	}

}
