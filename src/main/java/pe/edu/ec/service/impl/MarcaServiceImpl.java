package pe.edu.ec.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.ec.entity.Marca;
import pe.edu.ec.repository.MarcaRepository;
import pe.edu.ec.service.MarcaService;

@Service
public class MarcaServiceImpl implements MarcaService {
	@Autowired
	private MarcaRepository marcaRepository ;
	
	@Transactional(readOnly = true)
	@Override
	public List<Marca> findAll() throws Exception {
		// TODO Auto-generated method stub
		return marcaRepository.findAll();
	}
	@Transactional
	@Override
	public Marca save(Marca t) throws Exception {
		// TODO Auto-generated method stub
		return marcaRepository.save(t);
	}
	@Transactional
	@Override
	public Marca update(Marca t) throws Exception {
		// TODO Auto-generated method stub
		return marcaRepository.save(t);
	}
	
	@Transactional(readOnly = true)
	@Override
	public Optional<Marca> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return marcaRepository.findById(id);
	}
	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		marcaRepository.deleteById(id);
		
	}
	@Transactional
	@Override
	public void deleteAll() throws Exception {
		marcaRepository.deleteAll();
		
	}

	@Transactional(readOnly = true)
	@Override
	public List<Marca> fetchMarcaByNombre(String nombre) throws Exception {
		// TODO Auto-generated method stub
		return marcaRepository.fetchMarcaByNombre(nombre);
	}

}
