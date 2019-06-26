package pe.edu.ec.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.ec.entity.Orden;
import pe.edu.ec.repository.OrdenRepository;
import pe.edu.ec.service.OrdenService;

@Service
public class OrdenServiceImpl implements OrdenService {

	@Autowired
	private OrdenRepository ordenRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Orden> findAll() throws Exception {
		// TODO Auto-generated method stub
		return ordenRepository.findAll();
	}
	@Transactional
	@Override
	public Orden save(Orden t) throws Exception {
		// TODO Auto-generated method stub
		return ordenRepository.save(t);
	}
	@Transactional
	@Override
	public Orden update(Orden t) throws Exception {
		// TODO Auto-generated method stub
		return ordenRepository.save(t);
	}
	@Transactional(readOnly = true)
	@Override
	public Optional<Orden> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return ordenRepository.findById(id);
	}
	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		ordenRepository.deleteById(id);
		
	}
	@Transactional
	@Override
	public void deleteAll() throws Exception {
		ordenRepository.deleteAll();
		
	}

}
