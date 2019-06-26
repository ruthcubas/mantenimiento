package pe.edu.ec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.ec.entity.Marca;


@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {
	
	@Query("select e from Marca e where e.nombre like %?1% ")
	List<Marca> fetchMarcaByNombre(String nombre);

}
