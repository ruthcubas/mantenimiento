package pe.edu.ec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.ec.entity.Orden;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Integer> {

}
