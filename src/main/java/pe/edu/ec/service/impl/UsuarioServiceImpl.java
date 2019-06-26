package pe.edu.ec.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.ec.entity.Usuario;
import pe.edu.ec.repository.UsuarioRepository;
import pe.edu.ec.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Usuario> findAll() throws Exception {
		// TODO Auto-generated method stub
		return usuarioRepository.findAll();
	}
	@Transactional
	@Override
	public Usuario save(Usuario t) throws Exception {
		// TODO Auto-generated method stub
		return usuarioRepository.save(t);
	}
	@Transactional
	@Override
	public Usuario update(Usuario t) throws Exception {
		// TODO Auto-generated method stub
		return usuarioRepository.save(t);
	}
	@Transactional
	@Override
	public Optional<Usuario> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(id);
	}
	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		usuarioRepository.deleteById(id);
		
	}
	@Transactional
	@Override
	public void deleteAll() throws Exception {
		usuarioRepository.deleteAll();
		
	}
	@Transactional(readOnly = true)
	@Override
	public List<Usuario> fetchUsuarioByNombre(String nombre) throws Exception {
		// TODO Auto-generated method stub
		return usuarioRepository.fetchUsuarioByNombre(nombre);
	}

}
