package pe.edu.upc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Tipodeusuario;
import pe.edu.upc.repository.TipodeusuarioRepository;
import pe.edu.upc.service.ITipodeusuarioService;

@Service
public class TipodeUsuarioServiceImpl implements ITipodeusuarioService {
	@Autowired
	private TipodeusuarioRepository rR;

	@Override
	public void insert(Tipodeusuario role) {
		rR.save(role);
	}

	@Override
	public List<Tipodeusuario> list() {
		// TODO Auto-generated method stub
		return rR.findAll();
	}
}
