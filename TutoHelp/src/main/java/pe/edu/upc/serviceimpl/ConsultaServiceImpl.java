package pe.edu.upc.serviceimpl;

import java.util.List;

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
	public Integer insert(Consulta consulta) {
		int rpta = pR.buscarConsulta(consulta.getTextoConsulta());
		if (rpta == 0) {
			pR.save(consulta);
		}
		return rpta;
	}

	@Override
	public List<Consulta> list() {
		// TODO Auto-generated method stub
		return pR.findAll();
	}


}
