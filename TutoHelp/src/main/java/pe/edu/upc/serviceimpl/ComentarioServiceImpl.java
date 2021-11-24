package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Comentario;
import pe.edu.upc.repository.IComentarioRepository;
import pe.edu.upc.serviceinterface.IComentarioService;

@Service
public class ComentarioServiceImpl implements IComentarioService {
	@Autowired
	private IComentarioRepository dR;

	@Override
	public void insert(Comentario comentario) {
		dR.save(comentario);
	}

	@Override
	@Transactional
	public void delete(long id) {
		dR.deleteById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Comentario> list() {
		// TODO Auto-generated method stub
		return dR.findAll();
	}

	@Override
	public Optional<Comentario> listarId(long id) {
		// TODO Auto-generated method stub
		return dR.findById(id);
	}

}
