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
import pe.edu.ec.service.RolService;

@Controller
@RequestMapping("/rol")
@SessionAttributes("rol")
public class RolController {

	@Autowired
	private RolService rolService;
	
	@GetMapping
	public String listado(Model model) {
		try {
			List<Rol> roles = rolService.findAll();
			model.addAttribute("roles", roles);
		} catch (Exception e) {
			model.addAttribute("error", "Error al obtener la lista");
		}
		return "rol/lista";
	}
	
	@GetMapping("/buscar")
	public String buscar(@RequestParam("txtId") String nombre, Model model) {
	
		
		
		try {

			if (!nombre.isEmpty()) {
				List<Rol> roles = rolService.fetchRolByNombre(nombre);
				if (!roles.isEmpty()) {
					model.addAttribute("roles", roles);
				} else {
					model.addAttribute("info", "No existe rol");
					model.addAttribute("roles", rolService.findAll());
				}

			} else {
				model.addAttribute("info", "Ingresar nombre del rol");
				model.addAttribute("roles", rolService.findAll());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "rol/lista";
	}
	
	@GetMapping("/nuevo")
	public String nuevaMarca(Model model) {
		try {
			Rol rol = new Rol();
			model.addAttribute("rol", rol);
			
			
		} catch (Exception e) {
			model.addAttribute("Error", "Rol no se ha guardado");
		}
		
		return "rol/nuevooo"; 
	}
		
	@PostMapping("/guardar")
	public String guardarRol(@ModelAttribute("rol") Rol rol, Model model, SessionStatus status) {
		try {
			
			rolService.save(rol);
			status.setComplete();
			model.addAttribute("success", "Rol guardado");
		} catch (Exception e) {
			model.addAttribute("error", "Rol no guardado");
		} 
		return "redirect:/rol";
	}

	@GetMapping("/editar/{id}")
	public String editarMarca( @PathVariable("id") Integer id, Model model) {

		try {
			Optional<Rol> buscado =  rolService.findById(id);
			if (buscado.isPresent()) {
				model.addAttribute("rol", buscado.get());
			} else {
				model.addAttribute("error", "Rol no encontrado");
			}
		} catch (Exception e) {
			model.addAttribute("error", "Rol no encontrada");
		}
		return "rol/editar";
		
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarRol( @PathVariable("id") Integer id, Model model  ) {
		try {
			Optional<Rol> buscado = rolService.findById(id);
			if(buscado.isPresent()) {
				rolService.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("error", "rol no eliminado");
		}
		return "redirect:/rol";
	}
	
	
	
	
}
