package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Usuario;
import pe.edu.upc.repository.IUsuarioRepository;
import pe.edu.upc.serviceinterface.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
	@Autowired
	private IUsuarioRepository pR;

	@Override
	public Integer insert(Usuario usuario) {
		int rpta = pR.buscarUsername(usuario.getUsername());
		if (rpta == 0) {
			pR.save(usuario);
		}
		return rpta;
	}

	@Override
	public void insert2(Usuario usuario) {

			pR.save(usuario);
	}
	
	@Override
	public List<Usuario> list() {
		// TODO Auto-generated method stub
		return pR.findAll();
	}

	@Override
	@Transactional
	public void delete(int iduser) {
		pR.deleteById(iduser);
	}
	
	@Override
	public Optional<Usuario> listarId(int iduser) {
		// TODO Auto-generated method stub
		return pR.findById(iduser);
	}
}
