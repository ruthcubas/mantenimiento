package pe.edu.ec.service;

import java.util.List;

import pe.edu.ec.entity.Usuario;


public interface UsuarioService  extends CrudService<Usuario, Integer> {

	List<Usuario> fetchUsuarioByNombre(String nombre) throws Exception;
}
