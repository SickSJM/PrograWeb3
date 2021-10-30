package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entities.Invitacion;

public interface IInvitacionService {
	public Integer insert(Invitacion invitacion);

	List<Invitacion> list();

}
