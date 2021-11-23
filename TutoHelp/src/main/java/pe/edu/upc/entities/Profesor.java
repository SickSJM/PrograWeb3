package pe.edu.upc.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;


@Entity
@Table(name = "profesores")
public class Profesor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre del Profesor no puede contener un caracter especial")
	@Pattern(regexp = "[^0-9]+", message = "El nombre del Profesor no puede contener un número")
	@Length (min = 3, max = 15, message = "La longitud del nombre es de 3 a 15 letras")
	@Column(name="profesornombre",nullable = false,length = 15)
	private String profesornombre;

	public String getProfesornombre() {
		return profesornombre;
	}

	public void setProfesornombre(String profesornombre) {
		this.profesornombre = profesornombre;
	}

	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El apellido del Profesor no puede contener un caracter especial")
	@Pattern(regexp = "[^0-9]+", message = "El apellido del Profesor no puede contener un número")
	@Column(name="profesorapellido",nullable = false,length = 15)
	private String profesorapellido;

	public String getProfesorapellido() {
		return profesorapellido;
	}

	public void setProfesorapellido(String profesorapellido) {
		this.profesorapellido = profesorapellido;
	}

	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre de la universidad no puede contener un caracter especial")
	@Pattern(regexp = "[^0-9]+", message = "El nombre de la universidad no puede contener un número")
	@Column(name="universidad",nullable = false,length = 30)
	private String universidad;

	public String getUniversidad() {
		return universidad;
	}

	public void setUniversidad(String universidad) {
		this.universidad = universidad;
	}
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El curso no puede contener un caracter especial")
	@Column(name="curso",nullable = false,length = 30)
	private String curso;

	public String getCurso() {
		return universidad;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
	
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

}