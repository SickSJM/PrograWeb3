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
@Table(name = "consulta")
public class Consulta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="textoconsulta",nullable = false,length = 200)
	private String textoconsulta;
	
	public String getTextoconsulta() {
		return textoconsulta;
	}

	public void setTextoconsulta(String textoconsulta) {
		this.textoconsulta = textoconsulta;
	}

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private Users user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

}