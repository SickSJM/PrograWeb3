package pe.edu.upc.serviceimpl;

import java.util.List;

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
	public Integer insert(Soporte soporte) {
		int rpta = pR.buscarSoporte(soporte.getReporteMensaje());
		if (rpta == 0) {
			pR.save(soporte);
		}
		return rpta;
	}

	@Override
	public List<Soporte> list() {
		// TODO Auto-generated method stub
		return pR.findAll();
	}


}
