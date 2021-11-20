package pe.edu.upc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<Invitacion> list() {
		// TODO Auto-generated method stub
		return pR.findAll();
	}


}
