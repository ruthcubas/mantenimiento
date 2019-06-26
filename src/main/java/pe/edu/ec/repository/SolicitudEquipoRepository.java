package pe.edu.ec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.ec.entity.SolicitudEquipo;


@Repository
public interface SolicitudEquipoRepository extends JpaRepository<SolicitudEquipo, Integer> {

}
