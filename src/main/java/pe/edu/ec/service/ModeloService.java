package pe.edu.ec.service;

import java.util.List;

import pe.edu.ec.entity.Modelo;


public interface ModeloService  extends CrudService<Modelo, Integer>{
	
	List<Modelo> fetchModeloByNombre(String nombre) throws Exception;

}
