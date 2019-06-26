package pe.edu.ec.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.ec.entity.Solicitud;
import pe.edu.ec.repository.SolicitudRepository;
import pe.edu.ec.service.SolicitudService;

@Service
public class SolicitudServiceImpl implements  SolicitudService {

	@Autowired
	private SolicitudRepository solicitudRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Solicitud> findAll() throws Exception {
		// TODO Auto-generated method stub
		return solicitudRepository.findAll();
	}
	@Transactional
	@Override
	public Solicitud save(Solicitud t) throws Exception {
		// TODO Auto-generated method stub
		return solicitudRepository.save(t);
	}
	@Transactional
	@Override
	public Solicitud update(Solicitud t) throws Exception {
		// TODO Auto-generated method stub
		return solicitudRepository.save(t);
	}
	@Transactional(readOnly = true)
	@Override
	public Optional<Solicitud> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return solicitudRepository.findById(id);
	}
	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		solicitudRepository.deleteById(id);
		
	}
	@Transactional
	@Override
	public void deleteAll() throws Exception {
		solicitudRepository.deleteAll();
	}

}
