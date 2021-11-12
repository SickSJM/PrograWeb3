package pe.edu.upc.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import pe.edu.upc.entity.Users;
import pe.edu.upc.service.IUserService;

@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private IUserService uService;

	@GetMapping("/new")
	public String newUser(Model model) {
		model.addAttribute("user", new Users());
		return "usersecurity/user";
	}

	@PostMapping("/save")
	public String saveUser(@Valid Users user, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "usersecurity/user";
		} else {
			String bcryptPassword = passwordEncoder.encode(user.getContraseña());
			user.setContraseña(bcryptPassword);
			int rpta = uService.insert(user);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "usersecurity/user";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listaUsuarios", uService.list());

		return "usersecurity/listUser";
	}

	@GetMapping("/list")
	public String listUser(Model model) {
		try {
			model.addAttribute("user", new Users());
			model.addAttribute("listaUsuarios", uService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "usersecurity/listUser";
	}

	@RequestMapping("/delete")
	public String deleteUser(@RequestParam(value="id") Integer id, Model model) {
		uService.delete(id);
		return "redirect:/users/list";
	}
	@RequestMapping("/update/{id}")
	public String goUpdate(@PathVariable int id,Model model, RedirectAttributes objRedir) {
		Optional<Users> user=uService.listId(id);
		if(user==null) {
			objRedir.addFlashAttribute("mensaje","ocurrio un error");
			return "usersecurity/user";
		}else {
			model.addAttribute("user",user);
			return "usersecurity/user";
		}
	}
}
