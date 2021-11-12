package pe.edu.upc.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name = "users")
public class Users implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 20, nullable = true)
	private String Nombre;

	@Column(length = 20, nullable = true)
	private String Apellido;

	@Column(nullable = true)
	private String DNI;

	@Column(length = 20, nullable = true)
	@Email(message = "Ingresar un correo valido.")
	private String Correo;

	@Column(nullable = true)
	private String Telefono;

	@Column(length = 20, nullable = true)
	private String Direccion;

	@Column(length = 10, unique = true)
	private String username;

	@Column(length = 200)
	private String Contraseña;

	private Boolean enabled;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private List<Tipodeusuario> roles;

	public List<Tipodeusuario> getRoles() {
		return roles;
	}

	public void setRoles(List<Tipodeusuario> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getCorreo() {
		return Correo;
	}

	public void setCorreo(String correo) {
		Correo = correo;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContraseña() {
		return Contraseña;
	}

	public void setContraseña(String contraseña) {
		Contraseña = contraseña;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	

}
