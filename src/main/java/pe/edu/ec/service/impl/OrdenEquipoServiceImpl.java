package pe.edu.ec.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.ec.entity.OrdenEquipo;
import pe.edu.ec.repository.OrdenEquipoRepository;
import pe.edu.ec.service.OrdenEquipoService;


@Service
public class OrdenEquipoServiceImpl implements OrdenEquipoService {
	
	@Autowired
	private OrdenEquipoRepository ordenEquipoRepository;
	@Transactional(readOnly = true)
	@Override
	public List<OrdenEquipo> findAll() throws Exception {
		// TODO Auto-generated method stub
		return ordenEquipoRepository.findAll();
	}
	@Transactional
	@Override
	public OrdenEquipo save(OrdenEquipo t) throws Exception {
		// TODO Auto-generated method stub
		return ordenEquipoRepository.save(t);
	}
	@Transactional
	@Override
	public OrdenEquipo update(OrdenEquipo t) throws Exception {
		// TODO Auto-generated method stub
		return ordenEquipoRepository.save(t);
	}
	@Transactional(readOnly = true)
	@Override
	public Optional<OrdenEquipo> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return ordenEquipoRepository.findById(id);
	}
	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		ordenEquipoRepository.deleteById(id);
		
	}
	@Transactional
	@Override
	public void deleteAll() throws Exception {
		ordenEquipoRepository.deleteAll();
		
	}

}
