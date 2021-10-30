package pe.edu.upc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Profesor;
import pe.edu.upc.repository.IProfesorRepository;
import pe.edu.upc.serviceinterface.IProfesorService;

@Service
public class ProfesorServiceImpl implements IProfesorService {
	@Autowired
	private IProfesorRepository cS;

	/* @Transactional */
	@Override
	public Integer insert(Profesor profesor) {

		int rpta = cS.buscarProfesor(profesor.getProfesorNombre());
		if (rpta == 0) {
			cS.save(profesor);
		}
		return rpta;

	}

	@Override
	public List<Profesor> list() {
		// return cR.findAll(Sort.by(Sort.Direction.ASC, "categoryName"));
		return cS.findAll();

	}

}
