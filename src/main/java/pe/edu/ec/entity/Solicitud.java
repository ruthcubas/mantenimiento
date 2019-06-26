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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "solicitudes")
public class Solicitud {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "solicitud_id")
	private Integer id;

	@Column(name = "solicitud_descripcion", length = 200, nullable = false)
	@NotEmpty(message = "Ingrese la descipción")
	private String descripcion;
	
	@OneToMany(mappedBy ="solicitudId", fetch= FetchType.LAZY, cascade=CascadeType.ALL)
	private List<SolicitudEquipo> solicitudEquipos;
	
	public Solicitud() {
		solicitudEquipos= new ArrayList<>();
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

	public List<SolicitudEquipo> getSolicitudEquipos() {
		return solicitudEquipos;
	}

	public void setSolicitudEquipos(List<SolicitudEquipo> solicitudEquipos) {
		this.solicitudEquipos = solicitudEquipos;
	}
	
	
	
}
