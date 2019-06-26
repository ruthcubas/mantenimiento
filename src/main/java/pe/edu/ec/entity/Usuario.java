package pe.edu.ec.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usuario_id")
	private int id;

	@Column(name = "usuario_nombre", length = 30, nullable = false)
	@Size(min = 3, message = "El nonbre de usuario debe tener 3 caracteres como mínimo")
	@NotEmpty(message = "Ingrese el nombre de usuario")
	private String nombre;

	@Column(name = "usuario_apellido", length = 30, nullable = false)
	@Size(min = 3, message = "El apellido de usuario debe tener 3 caracteres como mínimo")
	@NotEmpty(message = "Ingrese el apellido de usuario")
	private String apellido;

	@Column(name = "usuario_dni", length = 10, nullable = false)
	@Size(min = 8, max = 8, message = "El DNI debe tener 8 caracteres")
	@NotEmpty(message = "Ingrese el DNI de usuario")
	private String dni;

	@Column(name = "usuario_celular", length = 9)
	@NotEmpty(message = "Ingrese el celular de usuario")
	private String celular;

	@Column(name = "usuario_direccion", length = 80)
	@Size(max = 40)
	@NotEmpty(message = "Ingrese la dirección de usuario")
	private String direccion;

	@ManyToOne
	@JoinColumn(name = "rol_id", nullable = false)
	private Rol rolId;
	
	@OneToMany(mappedBy ="usuarioId", fetch= FetchType.LAZY, cascade=CascadeType.ALL)
	private List<OrdenEquipo> ordenEquipos;
	
	public Usuario() {
		ordenEquipos = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public List<OrdenEquipo> getOrdenEquipos() {
		return ordenEquipos;
	}

	public void setOrdenEquipos(List<OrdenEquipo> ordenEquipos) {
		this.ordenEquipos = ordenEquipos;
	}

	public Rol getRolId() {
		return rolId;
	}

	public void setRolId(Rol rolId) {
		this.rolId = rolId;
	}
	
	
}
