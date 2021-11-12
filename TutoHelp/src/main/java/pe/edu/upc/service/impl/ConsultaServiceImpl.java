package pe.edu.upc.service.impl;

import java.util.List;

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


	
}
