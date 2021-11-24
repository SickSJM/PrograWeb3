package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Invitacion;
import pe.edu.upc.repository.IInvitacionRepository;
import pe.edu.upc.serviceinterface.IInvitacionService;

@Service
public class InvitacionServiceImpl implements IInvitacionService {
	@Autowired
	private IInvitacionRepository pR;

	@Override
	public Integer insert(Invitacion invitacion) {
		int rpta = pR.buscarInvitacion(invitacion.getLinkReunion());
		if (rpta == 0) {
			pR.save(invitacion);
		}
		return rpta;
	}
	
	@Override
	public void insert2(Invitacion invitacion) {
		
			pR.save(invitacion);
		
	}

	@Override
	public List<Invitacion> list() {
		// TODO Auto-generated method stub
		return pR.findAll();
	}

	@Override
	@Transactional
	public void delete(int idinv) {
		pR.deleteById(idinv);
	}
	
	@Override
	public Optional<Invitacion> listarId(int idinv) {
		// TODO Auto-generated method stub
		return pR.findById(idinv);
	}

}
