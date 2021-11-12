package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.Consulta;

public interface IConsultaService {
	public void insert(Consulta consul);

	List<Consulta> list();

}
