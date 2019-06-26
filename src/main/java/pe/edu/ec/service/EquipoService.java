package pe.edu.ec.service;

import java.util.List;

import pe.edu.ec.entity.Equipo;

public interface EquipoService  extends CrudService<Equipo, Integer> {

	List<Equipo> fetchEquipoByNombre(String nombre) throws Exception;
}
