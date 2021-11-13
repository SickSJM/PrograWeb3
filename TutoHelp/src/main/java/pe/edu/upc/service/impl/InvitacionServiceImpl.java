package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Invitacion;
import pe.edu.upc.repository.IInvitacionRepository;
import pe.edu.upc.service.IInvitacionService;

@Service
public class InvitacionServiceImpl implements IInvitacionService {
	@Autowired
	private IInvitacionRepository rR;

	@Override
	public void insert(Invitacion invitacion) {
		rR.save(invitacion);
	}
	
	@Override
	@Transactional
	public void delete(long id) {
		rR.deleteById(id);
	}

	@Override
	public List<Invitacion> list() {
		// TODO Auto-generated method stub
		return rR.findAll();
	}

	@Override
	public Optional<Invitacion> listarId(long id) {
		// TODO Auto-generated method stub
		return rR.findById(id);
	}


	
}
