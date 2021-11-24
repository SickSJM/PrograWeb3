package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "Profesor")
public class Profesor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ProfesorID;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre del Profesor no puede contener un caracter especial")
	@Pattern(regexp = "[^0-9]+", message = "El nombre del Profesor no puede contener un número")
	@Length (min = 3, max = 15, message = "La longitud del nombre es de 3 a 15 letras")
	@Column(name = "ProfesorNombre", nullable = false, length = 15)
	private String ProfesorNombre;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El apellido del Profesor no puede contener un caracter especial")
	@Pattern(regexp = "[^0-9]+", message = "El apellido del Profesor no puede contener un número")
	@Length (min = 3, max = 15, message = "La longitud del apellido es de 3 a 15 letras")
	@Column(name = "ProfesorApellido", nullable = false, length = 15)
	private String ProfesorApellido;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre de la Universidad no puede contener un caracter especial")
	@Pattern(regexp = "[^0-9]+", message = "El nombre de la Universidad no puede contener un número")
	@Length (min = 3, max = 30, message = "La longitud del nombre es de 3 a 30 letras")
	@Column(name = "Universidad", nullable = false, length = 30)
	private String Universidad;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El Curso no puede contener un caracter especial")
	@Length (min = 3, max = 20, message = "La longitud del curso es de 3 a 20")
	@Column(name = "Curso", nullable = false, length = 20)
	private String Curso;

	public Profesor(int profesorID,String profesorNombre,String profesorApellido,String universidad,String curso) {
		super();
		ProfesorID = profesorID;
		ProfesorNombre = profesorNombre;
		ProfesorApellido = profesorApellido;
		Universidad = universidad;
		Curso = curso;
	}

	public Profesor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getProfesorID() {
		return ProfesorID;
	}

	public void setProfesorID(int profesorID) {
		ProfesorID = profesorID;
	}

	public String getProfesorNombre() {
		return ProfesorNombre;
	}

	public void setProfesorNombre(String profesorNombre) {
		ProfesorNombre = profesorNombre;
	}

	public String getProfesorApellido() {
		return ProfesorApellido;
	}

	public void setProfesorApellido(String profesorApellido) {
		ProfesorApellido = profesorApellido;
	}

	public String getUniversidad() {
		return Universidad;
	}

	public void setUniversidad(String universidad) {
		Universidad = universidad;
	}

	public String getCurso() {
		return Curso;
	}

	public void setCurso(String curso) {
		Curso = curso;
	}

	
	

}
