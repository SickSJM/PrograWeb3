package pe.edu.upc.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Tipodeusuario;
import pe.edu.upc.entities.Usuario;
import pe.edu.upc.repository.IUsuarioRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private IUsuarioRepository userRepository;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = userRepository.findByUsername(username);
 
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (Tipodeusuario role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getDescripcion()));
		}

		return new User(user.getUsername(), user.getContrasenaUsuario(), user.getEnabled(), true, true, true, authorities);
	}

}
