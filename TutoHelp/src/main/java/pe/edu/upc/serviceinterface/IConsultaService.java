package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entities.Consulta;

public interface IConsultaService {
	public Integer insert(Consulta consulta);

	List<Consulta> list();

}
