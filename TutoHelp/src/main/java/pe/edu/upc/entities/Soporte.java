package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;


@Entity
@Table(name = "Soporte")
public class Soporte {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int SoporteID;

	@ManyToOne
	@JoinColumn(name = "UsuarioID", nullable = false)
	private Usuario usuario;
	
	@Length (min = 1, max = 200, message = "La longitud del mensaje es de 1 a 200 letras")
	@Column(name = "ReporteMensaje", nullable = false, length = 200)
	private String ReporteMensaje;

	public Soporte(int soporteID, Usuario usuario,String reporteMensaje) {
		super();
		SoporteID = soporteID;
		this.usuario = usuario;
		ReporteMensaje = reporteMensaje;
	}

	public Soporte() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getSoporteID() {
		return SoporteID;
	}

	public void setSoporteID(int soporteID) {
		SoporteID = soporteID;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getReporteMensaje() {
		return ReporteMensaje;
	}

	public void setReporteMensaje(String reporteMensaje) {
		ReporteMensaje = reporteMensaje;
	}
	
	
}
