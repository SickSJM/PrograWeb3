package pe.edu.upc.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "profesores")
public class Profesor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Column(name="profesornombre",nullable = false,length = 15)
	private String profesornombre;

	public String getProfesornombre() {
		return profesornombre;
	}

	public void setProfesornombre(String profesornombre) {
		this.profesornombre = profesornombre;
	}

	
	@Column(name="profesorapellido",nullable = false,length = 15)
	private String profesorapellido;

	public String getProfesorapellido() {
		return profesorapellido;
	}

	public void setProfesorapellido(String profesorapellido) {
		this.profesorapellido = profesorapellido;
	}

	@Column(name="universidad",nullable = false,length = 30)
	private String universidad;

	public String getUniversidad() {
		return universidad;
	}

	public void setUniversidad(String universidad) {
		this.universidad = universidad;
	}
	
	@Column(name="curso",nullable = false,length = 30)
	private String curso;

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
	

}