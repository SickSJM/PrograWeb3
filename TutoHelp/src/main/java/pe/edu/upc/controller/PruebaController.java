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

import pe.edu.upc.entities.Prueba;
import pe.edu.upc.serviceinterface.IUsuarioService;
import pe.edu.upc.serviceinterface.IPruebaService;

@Controller
@RequestMapping("/pruebas")
public class PruebaController {
	@Autowired
	private IPruebaService pService;
	@Autowired
	private IUsuarioService cService;


	@GetMapping("/nuevo")
	public String newPrueba(Model model) {
		model.addAttribute("xprueba", new Prueba());
		model.addAttribute("listaUsuarios", cService.list());
		return "xprueba/xprueba";
	}

	@GetMapping("/list")
	public String listPruebas(Model model) {
		try {
			model.addAttribute("xprueba", new Prueba());
			model.addAttribute("listaPruebas", pService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "xprueba/listPruebas";
	}

	@PostMapping("/save")
	public String saveMarca(@Valid Prueba xprueba, BindingResult binRes, Model model,SessionStatus status)
			throws Exception {
		if (binRes.hasErrors()) {
			model.addAttribute("listaUsuarios", cService.list());
			return "xprueba/xprueba";
		} else {
			pService.insert(xprueba);
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
		}
		model.addAttribute("xprueba", new Prueba());
		return "redirect:/pruebas/list";
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
		model.put("listaPruebas", pService.list());

		return "xprueba/listPruebas";
	}

	@RequestMapping("/update/{id}")
	public String goUpdate(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		try {
		model.addAttribute("listaUsuarios", cService.list());
		Optional<Prueba> xprueba = pService.listarId(id);
		if (xprueba==null) {
			model.addAttribute("info", "Prueba no existe");
			return "redirect:/pruebas/list";
		} else {
			model.addAttribute("xprueba", xprueba.get());
		}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/xprueba/update";
	}

}
