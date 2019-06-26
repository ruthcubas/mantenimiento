package pe.edu.ec.entity;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "solicitudequipos")
public class SolicitudEquipo {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "solicitudequipo_id")
	private Integer id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "solicitudequipo_fecha")
	private Date fecha;

	@ManyToOne
	@JoinColumn(name = "solicitud_id", nullable = false)
	private Solicitud solicitudId;

	@ManyToOne
	@JoinColumn(name = "equipo_id", nullable = false)
	private Equipo equipoId;
	
	@OneToMany(mappedBy ="solicitudEquipoId", fetch= FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Orden> ordenes;
	
	
	public SolicitudEquipo() {
		
		ordenes= new ArrayList<>();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public Solicitud getSolicitudId() {
		return solicitudId;
	}


	public void setSolicitudId(Solicitud solicitudId) {
		this.solicitudId = solicitudId;
	}


	public Equipo getEquipoId() {
		return equipoId;
	}


	public void setEquipoId(Equipo equipoId) {
		this.equipoId = equipoId;
	}


	public List<Orden> getOrdenes() {
		return ordenes;
	}


	public void setOrdenes(List<Orden> ordenes) {
		this.ordenes = ordenes;
	}
	
	

	
}
