package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Invitacion;

public interface IInvitacionService {
	public void insert(Invitacion invitacion);
	public void delete(long id);
	Optional<Invitacion> listarId(long id);
	List<Invitacion> list();

}
