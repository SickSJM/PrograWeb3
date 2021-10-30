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
@Table(name = "Prueba")
public class Prueba {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int PruebaID;

	@ManyToOne
	@JoinColumn(name = "UsuarioID", nullable = false)
	private Usuario usuario;
	
	@Length (min = 1, max = 20, message = "La longitud del nombre de archivo es de 1 a 20 letras")
	@Column(name = "NombreArchivo", nullable = false, length = 20)
	private String NombreArchivo;
	
	@Length (min = 5, max = 100, message = "La longitud de la descripción es de 5 a 100 letras")
	@Column(name = "DescripcionArchivo", nullable = false, length = 10)
	private String DescripcionArchivo;
	
	@Length (min = 1, max = 100, message = "La longitud del url es de 1 a 100 letras")
	@Column(name = "Url", nullable = false, length = 10)
	private String Url;

	public Prueba(int pruebaID, Usuario usuario,String nombreArchivo,String descripcionArchivo,String url) {
		super();
		PruebaID = pruebaID;
		this.usuario = usuario;
		NombreArchivo = nombreArchivo;
		DescripcionArchivo = descripcionArchivo;
		Url = url;
	}

	public Prueba() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getPruebaID() {
		return PruebaID;
	}

	public void setPruebaID(int pruebaID) {
		PruebaID = pruebaID;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNombreArchivo() {
		return NombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		NombreArchivo = nombreArchivo;
	}

	public String getDescripcionArchivo() {
		return DescripcionArchivo;
	}

	public void setDescripcionArchivo(String descripcionArchivo) {
		DescripcionArchivo = descripcionArchivo;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}


	
}
