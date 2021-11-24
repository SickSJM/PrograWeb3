package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Consulta;
import pe.edu.upc.repository.IConsultaRepository;
import pe.edu.upc.serviceinterface.IConsultaService;

@Service
public class ConsultaServiceImpl implements IConsultaService {
	@Autowired
	private IConsultaRepository pR;

	@Override
	public void insert(Consulta consulta) {
			pR.save(consulta);
	}

	@Override
	public List<Consulta> list() {
		// TODO Auto-generated method stub
		return pR.findAll();
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		pR.deleteById(id);
	}

	@Override
	public Optional<Consulta> listId(int id) {
		// TODO Auto-generated method stub
		return pR.findById(id);
	}


}
