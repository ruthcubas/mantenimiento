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

@Entity
@Table(name = "equipos")
public class Equipo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "equipo_id", nullable = false)
	private Integer id;
	
	@Column(name = "equipo_nombre", nullable = false, length = 30)
	@NotEmpty(message = "Ingrese el nombre del equipo")
	private String nombre;
	
	@Column(name = "equipo_descripcion", nullable = false, length = 30)
	@NotEmpty(message = "Ingrese la descripción del equipo")
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name = "modelo_id", nullable = false)
	private Modelo modeloId;
	

	@OneToMany(mappedBy ="equipoId", fetch= FetchType.LAZY, cascade=CascadeType.ALL)
	private List<SolicitudEquipo> solicitudEquipos;
	
	@OneToMany(mappedBy ="equipoId", fetch= FetchType.LAZY, cascade=CascadeType.ALL)
	private List<OrdenEquipo> ordenEquipos;
	
	public Equipo() {
		solicitudEquipos= new ArrayList<>();
		ordenEquipos= new ArrayList<>();
	}

	
	public List<SolicitudEquipo> getSolicitudEquipos() {
		return solicitudEquipos;
	}

	public void setSolicitudEquipos(List<SolicitudEquipo> solicitudEquipos) {
		this.solicitudEquipos = solicitudEquipos;
	}

	public List<OrdenEquipo> getOrdenEquipos() {
		return ordenEquipos;
	}

	public void setOrdenEquipos(List<OrdenEquipo> ordenEquipos) {
		this.ordenEquipos = ordenEquipos;
	}

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Modelo getModeloId() {
		return modeloId;
	}

	public void setModeloId(Modelo modeloId) {
		this.modeloId = modeloId;
	}


	
	
}
