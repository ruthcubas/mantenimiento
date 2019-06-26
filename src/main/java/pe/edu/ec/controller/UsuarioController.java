package pe.edu.ec.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.ec.entity.Rol;
import pe.edu.ec.entity.Usuario;
import pe.edu.ec.service.RolService;
import pe.edu.ec.service.UsuarioService;


@Controller
@RequestMapping("/usuario")
@SessionAttributes("usuario")// este usuario es el objeto de la sesion y se usa en guardar
public class UsuarioController {

	@Autowired
	private  UsuarioService usuarioService;
	
	@Autowired
	private RolService rolservice;

	@GetMapping
	public String ListaUsuario(Model model) {
		try {
			List<Usuario> usuarios = usuarioService.findAll();
			model.addAttribute("usuarios", usuarios);
		} catch (Exception e) {
			model.addAttribute("error", "Error en obtener la lista");
		}
		return "/usuario/lista";
	}
	
  @GetMapping("/buscar")
    
    public String buscarUsuario(@RequestParam("txtNombre") String nombre, Model model) {
		/*try {
			List<Usuario > usuarios =  usuarioService.fetchUsuarioByNombre(nombre);
			model.addAttribute("usuarios",usuarios);
			
		} catch (Exception e) {
			model.addAttribute("error", "Error en obtener la lista");
		}
		return "/usuario/lista";
		*/
		

		try {

			if (!nombre.isEmpty()) {
				List<Usuario> usuarios = usuarioService.fetchUsuarioByNombre(nombre);
				if (!usuarios.isEmpty()) {
					model.addAttribute("usuarios", usuarios);
				} else {
					model.addAttribute("info", "No existe usuario");
					model.addAttribute("usuarios", usuarioService.findAll());
				}

			} else {
				model.addAttribute("info", "Ingresar nombre del usuario");
				model.addAttribute("usuarios", usuarioService.findAll());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/usuario/lista";
	}
  
  @GetMapping("/nuevo")
	public String nuevoUsuario(Model model) {
		try {
			Usuario usuario = new Usuario();
			model.addAttribute("usuario", usuario);
			List<Rol> roles=rolservice.findAll();
			model.addAttribute("roles", roles);
		} catch (Exception e) {
			model.addAttribute("error", "No se pudo guardar al usuario");
		
		}
		return "/usuario/nuevo"; 
	}
	
  
  @GetMapping("/editar/{id}")
	public String editarUsuario( @PathVariable("id") Integer id, Model model) {

		try {
			Optional<Usuario> buscado =  usuarioService.findById(id);
			List<Rol> roles=rolservice.findAll();
			model.addAttribute("roles", roles);
			if (buscado.isPresent()) {
				model.addAttribute("usuario", buscado.get());
			} else {
				model.addAttribute("error", "Usuario no encontrado");
			}
		} catch (Exception e) {
			model.addAttribute("error", "Usuario no encontrado");
		}
		return "/usuario/editar";
		
	}
  
  @PostMapping("/guardar")
	public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario, Model model, SessionStatus status) {
		try {	
			usuarioService.save(usuario);
			status.setComplete();
			model.addAttribute("success", "Usuario guardado");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "Usuario no guardado");
		} 
		return "redirect:/usuario";
	}
  
  @GetMapping("/eliminar/{id}")
	public String eliminaUsuario( @PathVariable("id") Integer id, Model model  ) {
		try {
			Optional<Usuario> buscado = usuarioService.findById(id);
			if(buscado.isPresent()) {
				usuarioService.deleteById(id);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "Usuario no eliminado");
		}
		return "redirect:/usuario";
	}
  
  
    
	
	
}
