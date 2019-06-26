package pe.edu.ec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import pe.edu.ec.entity.Rol;


@Repository
public interface RolRepository  extends JpaRepository<Rol, Integer>  {

	
	@Query("select e from Rol e where e.nombre like %?1% ")
	List<Rol> fetchRolByNombre(String nombre);
}
