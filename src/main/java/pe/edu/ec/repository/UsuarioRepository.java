package pe.edu.ec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.ec.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query("select e from Usuario e where e.nombre like %?1% ")
	List<Usuario> fetchUsuarioByNombre(String nombre);
}
