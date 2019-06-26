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

@Entity
@Table(name = "ordenes")
public class Orden {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orden_id", nullable = false)
	private Integer id;

	@Column(name = "orden_descripcion", length = 200)
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name = "solicitudequipo_id", nullable = false)
	private SolicitudEquipo solicitudEquipoId;
	

	@OneToMany(mappedBy ="ordenId", fetch= FetchType.LAZY, cascade=CascadeType.ALL)
	private List<OrdenEquipo> ordenEquipos;
	
	public Orden() {
		ordenEquipos=new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public SolicitudEquipo getSolicitudEquipoId() {
		return solicitudEquipoId;
	}

	public void setSolicitudEquipoId(SolicitudEquipo solicitudEquipoId) {
		this.solicitudEquipoId = solicitudEquipoId;
	}

	public List<OrdenEquipo> getOrdenEquipos() {
		return ordenEquipos;
	}

	public void setOrdenEquipos(List<OrdenEquipo> ordenEquipos) {
		this.ordenEquipos = ordenEquipos;
	}

	
	
}
