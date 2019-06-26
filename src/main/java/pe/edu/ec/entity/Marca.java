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
@Table(name = "marcas")
public class Marca {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "marca_id", nullable = false)
	private Integer id;

	@Column(name = "marca_nombre", nullable = false, length = 80)
	@NotEmpty(message = "Ingrese el nombre de la marca")
	private String nombre;

	@Column(name = "marca_descripcion", length = 200)
	@NotEmpty(message = "Ingrese la descripción de la marca equipo")
	private String descripcion;
	
	@OneToMany(mappedBy ="marcaId", fetch= FetchType.LAZY, cascade=CascadeType.ALL )
	private List<Modelo> modelos;
	
	
	public Marca() {
		modelos = new ArrayList<>();
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

	public List<Modelo> getModelos() {
		return modelos;
	}

	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}
	
	
	
}
