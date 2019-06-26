package pe.edu.ec.service;

import java.util.List;

import pe.edu.ec.entity.Marca;


public interface MarcaService  extends CrudService<Marca, Integer> {

	List<Marca> fetchMarcaByNombre(String nombre) throws Exception;
}
