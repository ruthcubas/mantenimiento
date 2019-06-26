package pe.edu.ec.service;

import java.util.List;

import pe.edu.ec.entity.Rol;

public interface RolService  extends CrudService<Rol, Integer> {
	
	List<Rol> fetchRolByNombre(String nombre) throws Exception;

}
