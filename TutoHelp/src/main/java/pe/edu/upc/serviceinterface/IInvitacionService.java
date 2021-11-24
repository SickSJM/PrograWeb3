package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Invitacion;

public interface IInvitacionService {
	public Integer insert(Invitacion invitacion);
	public void insert2(Invitacion invitacion);
	List<Invitacion> list();
	Optional<Invitacion> listarId(int idinv);
	public void delete(int idinv);
}
