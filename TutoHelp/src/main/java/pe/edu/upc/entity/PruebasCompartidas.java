package pe.edu.upc.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Pruebascompartidas")
public class PruebasCompartidas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="NombreArchivo",nullable = false,length = 20)
	private String NombreArchivo;
	
	@Column(name="DecripcionArchivo",nullable = false,length = 100)
	private String DecripcionArchivo;
	
	@Column(name="url",nullable = false,length = 100)
	private String url;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private Users user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreArchivo() {
		return NombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		NombreArchivo = nombreArchivo;
	}

	public String getDecripcionArchivo() {
		return DecripcionArchivo;
	}

	public void setDecripcionArchivo(String decripcionArchivo) {
		DecripcionArchivo = decripcionArchivo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	
}