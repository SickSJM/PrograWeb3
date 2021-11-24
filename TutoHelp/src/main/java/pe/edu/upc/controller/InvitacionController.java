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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entities.Invitacion;
import pe.edu.upc.serviceinterface.IUsuarioService;
import pe.edu.upc.serviceinterface.IInvitacionService;

@Controller
@RequestMapping("/invitaciones")
public class InvitacionController {
	@Autowired
	private IInvitacionService pService;
	@Autowired
	private IUsuarioService cService;


	@GetMapping("/nuevo")
	public String newInvitacion(Model model) {
		model.addAttribute("invitacion", new Invitacion());
		model.addAttribute("listaUsuarios", cService.list());
		return "invitacion/invitacion";
	}

	@GetMapping("/list")
	public String listInvitaciones(Model model) {
		try {
			model.addAttribute("invitacion", new Invitacion());
			model.addAttribute("listaInvitaciones", pService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "invitacion/listInvitaciones";
	}

	@PostMapping("/save")
	public String saveMarca(@Valid Invitacion invitacion, BindingResult binRes, Model model,SessionStatus status)
			throws Exception {
		if (binRes.hasErrors()) {
			model.addAttribute("listaUsuarios", cService.list());
			return "invitacion/invitacion";
		} else {
			int rpta = pService.insert(invitacion);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "invitacion/invitacion";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("invitacion", new Invitacion());
		return "redirect:/invitaciones/list";
	}
	
	@RequestMapping("/delete")
	public String delete(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				pService.delete(id);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar");
		}
		model.put("listaInvitaciones", pService.list());

		return "invitacion/listInvitaciones";
	}

	@RequestMapping("/update/{id}")
	public String goUpdate(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		try {
		model.addAttribute("listaUsuarios", cService.list());
		Optional<Invitacion> invitacion = pService.listarId(id);
		if (invitacion==null) {
			model.addAttribute("info", "Invitación no existe");
			return "redirect:/invitaciones/list";
		} else {
			model.addAttribute("invitacion", invitacion.get());
		}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/invitacion/invitacionMOD";
	}
	
	@PostMapping("/save2")
	public String save(@Valid Invitacion invitacion, BindingResult result, Model model,SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			//model.addAttribute("listaUsuarios", cService.list());
			return "/invitacion/invitacionMOD";
		} else {
			pService.insert2(invitacion);
			
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
		}
		model.addAttribute("invitacion", new Invitacion());
		return "redirect:/invitaciones/list";
	}

}
