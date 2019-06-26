package pe.edu.ec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.ec.entity.Modelo;


@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Integer> {

	@Query("select e from Modelo e where e.nombre like %?1% ")
	List<Modelo> fetchModeloByNombre(String nombre);
}
