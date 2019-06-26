package pe.edu.ec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.ec.entity.Equipo;


@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer> {

	@Query("select e from Equipo e where e.nombre like %?1% ")
	List<Equipo> fetchEquipoByNombre(String nombre);
}
