package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Tipodeusuario;
import pe.edu.upc.repository.TipodeusuarioRepository;
import pe.edu.upc.serviceinterface.ITipodeusuarioService;

@Service
public class TipodeUsuarioServiceImpl implements ITipodeusuarioService {
	@Autowired
	private TipodeusuarioRepository rR;

	@Override
	public Integer insert(Tipodeusuario role) {
		int rpta = rR.buscarNombreTipousuario(role.getUser());
		if (rpta == 0) {
			rR.save(role);
		}
		return rpta;
	}
	
	@Override
	public void insert2(Tipodeusuario role) {
		
		rR.save(role);
	
	}
	
	@Override
	@Transactional
	public void delete(long idrole) {
		rR.deleteById(idrole);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Tipodeusuario> list() {
		// TODO Auto-generated method stub
		return rR.findAll(Sort.by(Sort.Direction.DESC, "descripcion"));
	}

	@Override
	public Optional<Tipodeusuario> listarId(long idrole) {
		// TODO Auto-generated method stub
		return rR.findById(idrole);
	}
/*
	@Override
	public List<Tipodeusuario> findByName(String name) {
		// TODO Auto-generated method stub
		return rR.findByName(name);
	}

	@Override
	public List<Tipodeusuario> findByNameLikeIgnoreCase(String name) {
		// TODO Auto-generated method stub
		return rR.findByNameLikeIgnoreCase(name);
	}*/
}
