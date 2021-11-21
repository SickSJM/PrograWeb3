package pe.edu.upc.controller;

//import java.text.ParseException;
//import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.annotation.Secured;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entities.Tipodeusuario;
import pe.edu.upc.serviceinterface.ITipodeusuarioService;
import pe.edu.upc.serviceinterface.IUsuarioService;

@Controller
@RequestMapping("/tipodeusuarios")
public class TipoDeUsuarioController {

	@Autowired
	private IUsuarioService uService;
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
				model.addAttribute("listaUsuarios", uService.list());
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

	@RequestMapping("/update/{id}")
	public String goUpdate(@PathVariable int id,Model model, RedirectAttributes objRedir) {
		Optional<Tipodeusuario> tipodeusuario=rService.listarId(id);
		model.addAttribute("listaUsuarios", uService.list());
		if(tipodeusuario==null) {
			objRedir.addFlashAttribute("mensaje","ocurrio un error");
			return "tipodeusuario/tipodeusuario";
		}else {
			model.addAttribute("tipodeusuario",tipodeusuario);
			return "tipodeusuario/tipodeusuario";
		}
	}

}
