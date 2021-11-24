package pe.edu.upc.entities;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;


@Entity
@Table(name = "detalleprofesores", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id", "profesor" }) })
public class Detalleprofesor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "La calificación no puede contener caracteres especiales")
	@Pattern(regexp = "[^A-Za-z]+", message = "La calificación no puede contener letras")
	@Min(value = 0 , message = "La calificación minima es 0")
    @Max(value = 10 , message = "La calificación máxima es 10")
	@Column(name="calificacion",nullable = false,length = 2)
	private int calificacion;
	
	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	
	@Length (min = 1, max = 100, message = "La longitud del comentario es de 1 a 100 letras")
	@Column(name="comentario",nullable = false,length = 100)
	private String comentario;

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	@ManyToOne
	@JoinColumn(name = "profesor", nullable = false)
	private Profesor profesor;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private Usuario user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	
}