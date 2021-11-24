package pe.edu.upc.controller;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import pe.edu.upc.entities.Usuario;
import pe.edu.upc.serviceinterface.IUsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private IUsuarioService pService;

	@Secured({"ROLE_ADMIN"})
	@GetMapping("/nuevo")
	public String newUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
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
	public String saveMarca(@Valid Usuario usuario, BindingResult binRes, Model model, SessionStatus status)
			throws Exception {
		if (binRes.hasErrors()) {
			return "usuario/usuario";
		} else {
			String bcryptPassword = passwordEncoder.encode(usuario.getContrasenaUsuario());
			usuario.setContrasenaUsuario(bcryptPassword);
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

	@RequestMapping("/delete")
	public String delete(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				pService.delete(id);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar el rol");
		}
		model.put("listaUsuarios", pService.list());

		return "usuario/listUsuarios";
	}

	@RequestMapping("/update/{id}")
	public String goUpdate(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Usuario> usuario = pService.listarId(id);
		if (usuario == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrio un error");
			return "usuario/usuarioMOD";
		} else {
			model.addAttribute("usuario", usuario);
			return "usuario/usuarioMOD";
		}
	}

	@PostMapping("/save2")
	public String save(@Valid Usuario usuario, BindingResult binRes, Model model, SessionStatus status)
			throws Exception {
		if (binRes.hasErrors()) {
			return "usuario/usuarioMOD";
		} else {
			String bcryptPassword = passwordEncoder.encode(usuario.getContrasenaUsuario());
			usuario.setContrasenaUsuario(bcryptPassword);
			pService.insert2(usuario);
			model.addAttribute("mensaje", "Se actualizó correctamente");
			status.setComplete();

		}
		model.addAttribute("usuario", new Usuario());
		return "redirect:/usuarios/list";
	}
	@RequestMapping("/reporte1")
	public String userTopQuantityPrueba(Map<String, Object> model) {
		model.put("listaUsuarios", pService.userTopQuantityPrueba());
		return "reports/userTopQuantity";
	}
	@GetMapping("/reportes")
	public String listReports(Model model) {

		return "/reports/reports";
	}

}
