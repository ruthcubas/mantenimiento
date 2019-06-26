package pe.edu.ec.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ordenesequipo")
public class OrdenEquipo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ordenequipo_id", nullable = false)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name = "ordenequipo_fecha", nullable = false)
	private Date fecha;

	@ManyToOne
	@JoinColumn(name = "equipo_id", nullable = false)
	private Equipo equipoId;

	@ManyToOne
	@JoinColumn(name = "orden_id", nullable = false)
	private Orden ordenId;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuarioId;

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

	public Equipo getEquipoId() {
		return equipoId;
	}

	public void setEquipoId(Equipo equipoId) {
		this.equipoId = equipoId;
	}

	public Orden getOrdenId() {
		return ordenId;
	}

	public void setOrdenId(Orden ordenId) {
		this.ordenId = ordenId;
	}

	public Usuario getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Usuario usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	
}
