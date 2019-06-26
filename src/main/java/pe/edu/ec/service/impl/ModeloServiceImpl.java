package pe.edu.ec.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.ec.entity.Modelo;
import pe.edu.ec.repository.ModeloRepository;
import pe.edu.ec.service.ModeloService;

@Service
public class ModeloServiceImpl implements  ModeloService{

	@Autowired
	private ModeloRepository  modeloRepository;
	@Transactional(readOnly = true)
	@Override
	public List<Modelo> findAll() throws Exception {
		// TODO Auto-generated method stub
		return modeloRepository.findAll();
	}
	@Transactional
	@Override
	public Modelo save(Modelo t) throws Exception {
		// TODO Auto-generated method stub
		return modeloRepository.save(t);
	}
	@Transactional
	@Override
	public Modelo update(Modelo t) throws Exception {
		// TODO Auto-generated method stub
		return modeloRepository.save(t);
	}
	@Transactional(readOnly = true)
	@Override
	public Optional<Modelo> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return modeloRepository.findById(id);
	}
	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		modeloRepository.deleteById(id);
		
	}
	@Transactional
	@Override
	public void deleteAll() throws Exception {
		modeloRepository.deleteAll();
		
	}
	@Transactional(readOnly = true)
	@Override
	public List<Modelo> fetchModeloByNombre(String nombre) throws Exception {
		// TODO Auto-generated method stub
		return modeloRepository.fetchModeloByNombre(nombre);
	}

}
