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
@Table(name = "Consulta")
public class Consulta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ConsultaID;

	@ManyToOne
	@JoinColumn(name = "UsuarioID", nullable = false)
	private Usuario usuario;
	
	@Length (min = 1, max = 200, message = "La longitud del texto es de 1 a 200 letras")
	@Column(name = "TextoConsulta", nullable = false, length = 200)
	private String TextoConsulta;

	public Consulta(int consultaID, Usuario usuario,String textoConsulta) {
		super();
		ConsultaID = consultaID;
		this.usuario = usuario;
		TextoConsulta = textoConsulta;
	}

	public Consulta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getConsultaID() {
		return ConsultaID;
	}

	public void setConsultaID(int consultaID) {
		ConsultaID = consultaID;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getTextoConsulta() {
		return TextoConsulta;
	}

	public void setTextoConsulta(String textoConsulta) {
		TextoConsulta = textoConsulta;
	}

	
	
}
