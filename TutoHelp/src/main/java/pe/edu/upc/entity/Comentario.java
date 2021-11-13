package pe.edu.upc.entity;


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


@Entity
@Table(name = "Comentarios", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id", "consulta" }) })
public class Comentario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private Users user;
	
	@ManyToOne
	@JoinColumn(name = "consulta", nullable = false)
	private Consulta consulta;

	@Column(name="comentarioconsul",nullable = false,length = 200)
	private String comentarioconsul;

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

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public String getComentarioconsul() {
		return comentarioconsul;
	}

	public void setComentarioconsul(String comentarioconsul) {
		this.comentarioconsul = comentarioconsul;
	}
	
	
	
}