package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Consulta;
import pe.edu.upc.repository.IConsultaRepository;
import pe.edu.upc.service.IConsultaService;

@Service
public class ConsultaServiceImpl implements IConsultaService {
	@Autowired
	private IConsultaRepository rR;

	@Override
	public void insert(Consulta consul) {
		rR.save(consul);
	}

	@Override
	public List<Consulta> list() {
		// TODO Auto-generated method stub
		return rR.findAll();
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		rR.deleteById(id);
	}

	@Override
	public Optional<Consulta> listId(long id) {
		// TODO Auto-generated method stub
		return rR.findById(id);
	}
	
}
