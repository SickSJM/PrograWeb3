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

import pe.edu.upc.entities.Consulta;
import pe.edu.upc.serviceinterface.IUsuarioService;
import pe.edu.upc.serviceinterface.IConsultaService;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {
	@Autowired
	private IConsultaService pService;
	@Autowired
	private IUsuarioService cService;


	@GetMapping("/nuevo")
	public String newConsulta(Model model) {
		model.addAttribute("consulta", new Consulta());
		model.addAttribute("listaUsuarios", cService.list());
		return "consulta/consulta";
	}

	@GetMapping("/list")
	public String listConsultas(Model model) {
		try {
			model.addAttribute("consulta", new Consulta());
			model.addAttribute("listaConsultas", pService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "consulta/listConsultas";
	}

	@PostMapping("/save")
	public String saveMarca(@Valid Consulta consulta, BindingResult binRes, Model model,SessionStatus status)
			throws Exception {
		if (binRes.hasErrors()) {
			model.addAttribute("listaUsuarios", cService.list());
			return "consulta/consulta";
		} else {
			pService.insert(consulta);
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
		}
		model.addAttribute("consulta", new Consulta());
		return "redirect:/consultas/list";
	}

	@RequestMapping("/delete")
	public String deleteConsulta(Map<String, Object> model,@RequestParam(value="id") Integer id) {
		try {
			if (id != null && id > 0) {
				pService.delete(id);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar");
		}
		model.put("listaConsultas", pService.list());

//		return "redirect:/categories/list";
		return "consulta/listConsultas";
	}
	
	@RequestMapping("/detalle/{id}")
	public String detailsConsulta(@PathVariable(value = "id") int id, Model model) {
		try {
			model.addAttribute("listaUsuarios", cService.list());
			Optional<Consulta> consulta = pService.listId(id);
			if (!consulta.isPresent()) {
				model.addAttribute("info", "Consulta no existe");
				return "redirect:/consultas/list";
			} else {
				
				model.addAttribute("consulta", consulta.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "consulta/update";
	}
}
