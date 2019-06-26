package pe.edu.ec.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.ec.entity.Rol;
import pe.edu.ec.repository.RolRepository;
import pe.edu.ec.service.RolService;

@Service
public class RolServiceImpl implements  RolService {
	
	@Autowired
	private RolRepository rolRepository;
	@Transactional(readOnly = true)
	@Override
	public List<Rol> findAll() throws Exception {
		// TODO Auto-generated method stub
		return rolRepository.findAll();
	}
	@Transactional
	@Override
	public Rol save(Rol t) throws Exception {
		// TODO Auto-generated method stub
		return rolRepository.save(t);
	}
	@Transactional
	@Override
	public Rol update(Rol t) throws Exception {
		// TODO Auto-generated method stub
		return rolRepository.save(t);
	}
	@Transactional(readOnly = true)
	@Override
	public Optional<Rol> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return rolRepository.findById(id);
	}
	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		rolRepository.deleteById(id);
		
	}
	@Transactional
	@Override
	public void deleteAll() throws Exception {
		rolRepository.deleteAll();
		
	}
	@Transactional(readOnly = true)
	@Override
	public List<Rol> fetchRolByNombre(String nombre) throws Exception {
		// TODO Auto-generated method stub
		return rolRepository.fetchRolByNombre(nombre);
	}

}
