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
@SessionAttributes({"roles"})	
public class RolController {
	
	@Autowired 
	private RolService rolService;
	
	@GetMapping("/lista")
	public String ListaRol(Model model) {
		try {
			model.addAttribute("roles", rolService.findAll());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/rol/lista";
	}
	
	
	@GetMapping("/buscar")
	public String buscarRol(@RequestParam("nombre") String nombre, Model model) {
		
		try {
			if (!nombre.isEmpty()) {
				List<Rol> roles=rolService.fetchRolByNombre(nombre);
				if (roles.isEmpty()) {
					model.addAttribute("roles", roles);
				}else {
					model.addAttribute("info", "El rol no existe");
					model.addAttribute("roles", rolService.findAll());
				}
				
			}else {
				model.addAttribute("info", "Ingrese nombre del rol");
				model.addAttribute("roles", rolService.findAll());
			}
			
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/marca/lista";
	}
	
	@GetMapping("nuevo")
	public String nuevoRol(Model model) {
		try {
			Rol rol= new Rol();
			model.addAttribute("rol", rol);
		} catch (Exception e) {
			model.addAttribute("Error", "Rol no se ha guardado");
		}
		return "/rol/nuevo";
	}
	
	
	@PostMapping("/guardar")
	public String guardarRol(@ModelAttribute("rol") Rol rol, Model model, SessionStatus status) {
		try {
			rolService.save(rol);
			status.setComplete();
			model.addAttribute("succes", "Rol guardado");
		} catch (Exception e) {
			model.addAttribute("error", "Rol no guardad");
		}
		return "redirect:/rol/lista";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarRol(@PathVariable("id") Integer id, Model model) {
		try {
			Optional<Rol> buscado = rolService.findById(id);
			if (buscado.isPresent()) {
				rolService.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("error", "Rol no eliminado");
		}
		return "redirect:/rol/lista";
	}
	
	@GetMapping("/editar/{id}")
	public String editarRol(@PathVariable("id") Integer id, Model model) {
		try {
			Optional<Rol> buscado = rolService.findById(id);
			if (buscado.isPresent()) {
				model.addAttribute("rol", buscado.get());
			}else {
				model.addAttribute("error", "Rol no encontrado");
			}
		} catch (Exception e) {
			model.addAttribute("error", "Rol no encontrado");
		}
		return "/rol/editar";
	}

}
