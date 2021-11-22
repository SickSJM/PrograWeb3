package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Soporte;
import pe.edu.upc.repository.ISoporteRepository;
import pe.edu.upc.serviceinterface.ISoporteService;


@Service
public class SoporteServiceImpl implements ISoporteService {
	@Autowired
	private ISoporteRepository pR;

	@Override
	public void insert(Soporte soporte) {
		
			pR.save(soporte);

	}

	@Override
	public List<Soporte> list() {
		// TODO Auto-generated method stub
		return pR.findAll();
	}

	@Override
	public void delete(int idCategory) {
		// TODO Auto-generated method stub
		pR.deleteById(idCategory);
	}

	@Override
	public Optional<Soporte> listId(int idCategory) {
		// TODO Auto-generated method stub
		return pR.findById(idCategory);
	}


}
