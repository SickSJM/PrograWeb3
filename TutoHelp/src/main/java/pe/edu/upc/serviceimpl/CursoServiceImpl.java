package pe.edu.upc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Curso;
import pe.edu.upc.repository.ICursoRepository;
import pe.edu.upc.serviceinterface.ICursoService;

@Service
public class CursoServiceImpl implements ICursoService {
	@Autowired
	private ICursoRepository cR;

	/* @Transactional */
	@Override
	public Integer insert(Curso curso) {

		int rpta = cR.buscarCurso(curso.getCursoNombre());
		if (rpta == 0) {
			cR.save(curso);
		}
		return rpta;

	}

	@Override
	public List<Curso> list() {
		// return cR.findAll(Sort.by(Sort.Direction.ASC, "categoryName"));
		return cR.findAll();

	}

}
