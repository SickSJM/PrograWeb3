package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Detallecurso;
import pe.edu.upc.repository.IDetallecursoRepository;
import pe.edu.upc.service.IDetallecursoService;

@Service
public class DetallecursoServiceImpl implements IDetallecursoService {
	@Autowired
	private IDetallecursoRepository dR;

	@Override
	public void insert(Detallecurso dcurso) {
		dR.save(dcurso);
	}

	@Override
	@Transactional
	public void delete(long iddcurso) {
		dR.deleteById(iddcurso);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Detallecurso> list() {
		// TODO Auto-generated method stub
		return dR.findAll(Sort.by(Sort.Direction.DESC, "curso"));
	}

	@Override
	public Optional<Detallecurso> listarId(long iddcurso) {
		// TODO Auto-generated method stub
		return dR.findById(iddcurso);
	}

}
