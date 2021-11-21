package pe.edu.upc.entities;

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
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;



@Entity
@Table(name = "Usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int UsuarioID;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre de usuario no puede contener caracteres especiales")
	@Pattern(regexp = "[^0-9]+", message = "El nombre de usuario no puede contener un número")
	@Length (min = 3, max = 20, message = "La longitud del nombre es de 3 a 20 letras")
	@Column(name = "NombreUsuario", nullable = false, length = 20)
	private String NombreUsuario;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El apellido del usuario no puede contener caracteres especiales")
	@Pattern(regexp = "[^0-9]+", message = "El apellido del usuario no puede contener un número")
	@Length (min = 3, max = 20, message = "La longitud del apellido es de 3 a 20 letras")
	@Column(name = "ApellidoUsuario", nullable = false, length = 20)
	private String ApellidoUsuario;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El DNI no puede contener caracteres especiales")
	@Pattern(regexp = "[^A-Za-z]+", message = "El DNI no puede contener letras")
	@Length (min = 8, max = 8, message = "La longitud del DNI es de 8 dígitos")
	@Column(name = "DNIUsuario", nullable = false, length = 8)
	private String DNIUsuario;
	
	@Email (message = "Error de formato de correo electrónico")
	@Length (min = 9, max = 20, message = "La longitud del correo es de 9 a 20")
	@Column(name = "CorreoUsuario", nullable = false, length = 20)
	private String CorreoUsuario;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El número de telefono no puede contener caracteres especiales")
	@Pattern(regexp = "[^A-Za-z]+", message = "El número de telefono no puede contener letras")
	@Length (min = 9, max = 9, message = "La longitud del telefono es de 9 dígitos")
	@Column(name = "TelefonoUsuario", nullable = false, length = 9)
	private String TelefonoUsuario;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "La dirección no puede contener caracteres especiales")
	@Length (min = 5, max = 20, message = "La longitud de la dirección es de 5 a 20")
	@Column(name = "DireccionUsuario", nullable = false, length = 20)
	private String DireccionUsuario;
	
	@Length (min = 1, max = 10, message = "La longitud del nickname es de 1 a 10")
	@Column(name = "username", nullable = false, length = 10)
	private String username;
	
//	@Length (min = 3, max = 200, message = "La longitud de la contraseña es de 3 a 15")
	@Column(name = "ContrasenaUsuario", nullable = false, length = 200)
	private String ContrasenaUsuario;
	
	private Boolean enabled;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private List<Tipodeusuario> roles;
	
	

	public Usuario(int usuarioID,String nombreUsuario,String apellidoUsuario,String dNIUsuario,String correoUsuario,String telefonoUsuario,
			String direccionUsuario,String username,String contrasenaUsuario,
			Boolean enabled, List<Tipodeusuario> roles) {
		super();
		UsuarioID = usuarioID;
		NombreUsuario = nombreUsuario;
		ApellidoUsuario = apellidoUsuario;
		DNIUsuario = dNIUsuario;
		CorreoUsuario = correoUsuario;
		TelefonoUsuario = telefonoUsuario;
		DireccionUsuario = direccionUsuario;
		this.username = username;
		ContrasenaUsuario = contrasenaUsuario;
		this.enabled = enabled;
		this.roles = roles;
	}

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getUsuarioID() {
		return UsuarioID;
	}

	public void setUsuarioID(int usuarioID) {
		UsuarioID = usuarioID;
	}


	public String getNombreUsuario() {
		return NombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		NombreUsuario = nombreUsuario;
	}

	public String getApellidoUsuario() {
		return ApellidoUsuario;
	}

	public void setApellidoUsuario(String apellidoUsuario) {
		ApellidoUsuario = apellidoUsuario;
	}

	public String getDNIUsuario() {
		return DNIUsuario;
	}

	public void setDNIUsuario(String dNIUsuario) {
		DNIUsuario = dNIUsuario;
	}

	public String getCorreoUsuario() {
		return CorreoUsuario;
	}

	public void setCorreoUsuario(String correoUsuario) {
		CorreoUsuario = correoUsuario;
	}

	public String getTelefonoUsuario() {
		return TelefonoUsuario;
	}

	public void setTelefonoUsuario(String telefonoUsuario) {
		TelefonoUsuario = telefonoUsuario;
	}

	public String getDireccionUsuario() {
		return DireccionUsuario;
	}

	public void setDireccionUsuario(String direccionUsuario) {
		DireccionUsuario = direccionUsuario;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContrasenaUsuario() {
		return ContrasenaUsuario;
	}

	public void setContrasenaUsuario(String contrasenaUsuario) {
		ContrasenaUsuario = contrasenaUsuario;
	}

	public List<Tipodeusuario> getRoles() {
		return roles;
	}

	public void setRoles(List<Tipodeusuario> roles) {
		this.roles = roles;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	

}
