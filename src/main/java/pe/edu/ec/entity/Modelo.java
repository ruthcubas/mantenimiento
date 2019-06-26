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
@Table(name = "modelos")
public class Modelo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "modelo_id", nullable = false)
	private Integer id;
	
	@Column(name = "modelo_nombre", length = 80, nullable = false)
	@NotEmpty(message = "Ingrese el nombre del modelo")
	private String nombre;
	
	@Column(name = "modelo_descripcion", length = 200)
	@NotEmpty(message = "Ingrese la descripción del modelo")
	private String descripcion;
	
	@OneToMany(mappedBy ="modeloId", fetch= FetchType.LAZY, cascade=CascadeType.ALL )
	private List<Equipo> equipos;
	
	@ManyToOne
	@JoinColumn(name = "marca_id", nullable = false)
	private Marca marcaId;
	
	public Modelo() {
		equipos = new ArrayList<>();
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

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

	public Marca getMarcaId() {
		return marcaId;
	}

	public void setMarcaId(Marca marcaId) {
		this.marcaId = marcaId;
	}
	
	
}
