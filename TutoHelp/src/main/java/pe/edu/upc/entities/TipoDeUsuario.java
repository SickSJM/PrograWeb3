package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.validation.constraints.Pattern;

@Entity
@Table(name = "TipoDeUsuario")
public class TipoDeUsuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTipoDeUsuario;

	@Column(name = "descripcionTipoDeUsuario", nullable = false, length = 10)
	private String descripcionTipoDeUsuario;

	public TipoDeUsuario(int idTipoDeUsuario, String descripcionTipoDeUsuario) {
		super();
		this.idTipoDeUsuario = idTipoDeUsuario;
		this.descripcionTipoDeUsuario = descripcionTipoDeUsuario;
	}

	public TipoDeUsuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdTipoDeUsuario() {
		return idTipoDeUsuario;
	}

	public void setIdTipoDeUsuario(int idTipoDeUsuario) {
		this.idTipoDeUsuario = idTipoDeUsuario;
	}

	public String getDescripcionTipoDeUsuario() {
		return descripcionTipoDeUsuario;
	}

	public void setDescripcionTipoDeUsuario(String descripcionTipoDeUsuario) {
		this.descripcionTipoDeUsuario = descripcionTipoDeUsuario;
	}

	
}
