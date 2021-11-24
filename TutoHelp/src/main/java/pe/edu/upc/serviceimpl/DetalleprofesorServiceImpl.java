package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.entities.Detalleprofesor;
import pe.edu.upc.repository.IDetalleprofesorRepository;
import pe.edu.upc.serviceinterface.IDetalleprofesorService;

@Service
public class DetalleprofesorServiceImpl implements IDetalleprofesorService {
	@Autowired
	private IDetalleprofesorRepository rR;

	@Override
	public void insert(Detalleprofesor role) {
		rR.save(role);
	}
	@Override
	@Transactional
	public void delete(long idDetalle) {
		rR.deleteById(idDetalle);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Detalleprofesor> list() {
		// TODO Auto-generated method stub
		return rR.findAll();
	}



	@Override
	public Optional<Detalleprofesor> listarId(long iddetalle) {
		// TODO Auto-generated method stub
		return rR.findById(iddetalle);
	}

	
}
