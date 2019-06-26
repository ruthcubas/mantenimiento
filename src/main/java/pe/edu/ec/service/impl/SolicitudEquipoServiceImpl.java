package pe.edu.ec.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.ec.entity.SolicitudEquipo;
import pe.edu.ec.repository.SolicitudEquipoRepository;
import pe.edu.ec.service.SolicitudEquipoService;

@Service
public class SolicitudEquipoServiceImpl implements SolicitudEquipoService {

	@Autowired
	private SolicitudEquipoRepository  solicitudEquipoRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<SolicitudEquipo> findAll() throws Exception {
		// TODO Auto-generated method stub
		return solicitudEquipoRepository.findAll();
	}
	@Transactional
	@Override
	public SolicitudEquipo save(SolicitudEquipo t) throws Exception {
		// TODO Auto-generated method stub
		return solicitudEquipoRepository.save(t);
	}
	@Transactional
	@Override
	public SolicitudEquipo update(SolicitudEquipo t) throws Exception {
		// TODO Auto-generated method stub
		return solicitudEquipoRepository.save(t);
	}
	@Transactional(readOnly = true)
	@Override
	public Optional<SolicitudEquipo> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return solicitudEquipoRepository.findById(id);
	}
	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		solicitudEquipoRepository.deleteById(id);
		
	}
	@Transactional
	@Override
	public void deleteAll() throws Exception {
		solicitudEquipoRepository.deleteAll();
		
	}

}
