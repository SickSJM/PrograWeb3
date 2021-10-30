package pe.edu.upc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.TipoDeUsuario;
import pe.edu.upc.repository.ITipoDeUsuarioRepository;
import pe.edu.upc.serviceinterface.ITipoDeUsuarioService;

@Service
public class TipoDeUsuarioServiceImpl implements ITipoDeUsuarioService {
	@Autowired
	private ITipoDeUsuarioRepository cR;

	/* @Transactional */
	@Override
	public Integer insert(TipoDeUsuario tipodeusuario) {

		int rpta = cR.buscarTipoDeUsuario(tipodeusuario.getDescripcionTipoDeUsuario());
		if (rpta == 0) {
			cR.save(tipodeusuario);
		}
		return rpta;

	}

	@Override
	public List<TipoDeUsuario> list() {
		// return cR.findAll(Sort.by(Sort.Direction.ASC, "categoryName"));
		return cR.findAll();

	}

}
