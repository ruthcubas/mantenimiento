package pe.edu.ec.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.ec.entity.Equipo;
import pe.edu.ec.repository.EquipoRepository;
import pe.edu.ec.service.EquipoService;

@Service
public class EquipoServiceImpl implements EquipoService {
	
	@Autowired
	private EquipoRepository equipoRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Equipo> findAll() throws Exception {
		return equipoRepository.findAll();
	}
	@Transactional
	@Override
	public Equipo save(Equipo t) throws Exception {
		return equipoRepository.save(t);
	}
	@Transactional
	@Override
	public Equipo update(Equipo t) throws Exception {
		return equipoRepository.save(t);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Equipo> findById(Integer id) throws Exception {
		return equipoRepository.findById(id);
	}
	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		equipoRepository.deleteById(id);
		
	}
	@Transactional
	@Override
	public void deleteAll() throws Exception {
		equipoRepository.deleteAll();
		
	}

	@Transactional(readOnly = true)
	@Override
	public List<Equipo> fetchEquipoByNombre(String nombre) throws Exception {
		return equipoRepository.fetchEquipoByNombre(nombre);
	}
	

}
