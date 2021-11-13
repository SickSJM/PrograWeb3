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

import pe.edu.upc.entity.Invitacion;
import pe.edu.upc.service.IInvitacionService;
import pe.edu.upc.service.IUserService;

@Controller
@RequestMapping("/invitaciones")
public class InvitacionController {

	@Autowired
	private IUserService uService;
	@Autowired
	private IInvitacionService sService;

	@GetMapping("/new")
	public String newInvitacion(Model model) {
		model.addAttribute("invitacion", new Invitacion());
		model.addAttribute("listaUsuarios", uService.list());
		return "invitacion/invitacion";
	}

	@PostMapping("/save")
	public String saveInvitacion(@Valid Invitacion invitacion, BindingResult result, Model model, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "/invitacion/invitacion";
		} else {
			model.addAttribute("listaUsuarios", uService.list());
			sService.insert(invitacion);
			model.addAttribute("mensaje", "Se guardó correctamente");
			status.setComplete();
		}
		model.addAttribute("listaInvitaciones", sService.list());

		return "/invitacion/invitacion";

	}

	@GetMapping("/list")
	public String listInvitaciones(Model model) {
		try {
			model.addAttribute("invitacion", new Invitacion());
			model.addAttribute("listaInvitaciones", sService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/invitacion/listInvitaciones";
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
			model.put("mensaje", "No se puede eliminar la invitacion");
		}
		model.put("listaInvitaciones", sService.list());

		return "invitacion/listInvitaciones";
	}
	@GetMapping("/detalle/{id}")
	public String detailsInvitacion(@PathVariable(value = "id") int id, Model model) {
		try {
			model.addAttribute("listaInvitaciones", uService.list());
			Optional<Invitacion> invitacion = sService.listarId(id);
			if (!invitacion.isPresent()) {
				model.addAttribute("info", "Invitacion no existe");
				return "redirect:/invitaciones/list";
			} else {
				
				model.addAttribute("invitacion", invitacion.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/invitacion/update";
	}

}
