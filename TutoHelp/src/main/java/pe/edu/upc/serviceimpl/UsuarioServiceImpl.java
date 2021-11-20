package pe.edu.upc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Usuario;
import pe.edu.upc.repository.IUsuarioRepository;
import pe.edu.upc.serviceinterface.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
	@Autowired
	private IUsuarioRepository pR;

	@Override
	public Integer insert(Usuario usuario) {
		int rpta = pR.buscarUsuario(usuario.getNombreUsuario());
		if (rpta == 0) {
			pR.save(usuario);
		}
		return rpta;
	}

	@Override
	public List<Usuario> list() {
		// TODO Auto-generated method stub
		return pR.findAll();
	}


}
