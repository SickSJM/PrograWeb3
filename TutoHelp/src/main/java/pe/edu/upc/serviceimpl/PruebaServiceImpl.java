package pe.edu.upc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Prueba;
import pe.edu.upc.repository.IPruebaRepository;
import pe.edu.upc.serviceinterface.IPruebaService;

@Service
public class PruebaServiceImpl implements IPruebaService {
	@Autowired
	private IPruebaRepository pR;

	@Override
	public Integer insert(Prueba xprueba) {
		int rpta = pR.buscarPrueba(xprueba.getNombreArchivo());
		if (rpta == 0) {
			pR.save(xprueba);
		}
		return rpta;
	}

	@Override
	public List<Prueba> list() {
		// TODO Auto-generated method stub
		return pR.findAll();
	}


}
