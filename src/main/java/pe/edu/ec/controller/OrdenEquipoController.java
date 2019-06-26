package pe.edu.ec.controller;

import java.util.ArrayList;
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

import pe.edu.ec.entity.Equipo;
import pe.edu.ec.entity.Orden;
import pe.edu.ec.entity.OrdenEquipo;

import pe.edu.ec.entity.Usuario;
import pe.edu.ec.service.EquipoService;
import pe.edu.ec.service.OrdenEquipoService;
import pe.edu.ec.service.OrdenService;

import pe.edu.ec.service.UsuarioService;

@Controller
@RequestMapping("/ordenequipo")
@SessionAttributes({"ordenequipo"})
public class OrdenEquipoController {

	@Autowired
	private OrdenEquipoService ordenEquipoService;
	@Autowired
	private EquipoService equipoService;
	@Autowired
	private OrdenService ordenService;
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/lista")
	public String ListaOrdenEquipo(Model model) {
		
		
		try {
			
			model.addAttribute("ordenequipo", ordenEquipoService.findAll());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			
		}
		return "/ordenequipo/lista";
		
	}
	
	

	
	@GetMapping("/buscar")
	public String buscarOrdenEquipo(@RequestParam("txtId") Integer id, Model model) {
		
		try {
			Optional<OrdenEquipo> buscado =  ordenEquipoService.findById(id);
			List<OrdenEquipo> solicitudes = new ArrayList<>();
			if (buscado.isPresent()) {
				solicitudes.add(buscado.get());	
			}
			model.addAttribute("ordenequipo", solicitudes);
			
		} catch (Exception e) {
			model.addAttribute("error", "Error en obtener la lista");
			return "redirect:/ordenequipo/lista";
		}
		return "/ordenequipo/lista";
	}
	

	 @GetMapping("/nuevo")
		public String nuevoOrdenEquipo(Model model) {
			try {
				OrdenEquipo ordenEquipo = new OrdenEquipo();
				model.addAttribute("ordenequipo", ordenEquipo);
				
				List<Equipo> equipos=equipoService.findAll();
				model.addAttribute("equipo",equipos);
				
				List<Orden> ordenes = ordenService.findAll();
				model.addAttribute("orden",ordenes);
				
				List<Usuario> usuarios = usuarioService.findAll();
				model.addAttribute("usuario",usuarios);

			} catch (Exception e) {
				model.addAttribute("Error", "No se pudo guardar el detalle");
			
			}
			return "/ordenequipo/nuevo"; 
		}
		
	 @PostMapping("/guardar")
		public String guardarOrdenEquipo(@ModelAttribute("ordenequipo") OrdenEquipo ordenequipo, Model model, SessionStatus status) {
			try {	
				ordenEquipoService.save(ordenequipo);
				status.setComplete();
				model.addAttribute("success", "Detalle guardado");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				model.addAttribute("error", "Detalle no guardado");
			} 
			return "redirect:/ordenequipo/lista";
		}
	  
	 @GetMapping("/eliminar/{id}")
		public String eliminarOrdenEquipo( @PathVariable("id") Integer id, Model model  ) {
			try {
				Optional<OrdenEquipo> buscado = ordenEquipoService.findById(id);
				if(buscado.isPresent()) {
					ordenEquipoService.deleteById(id);
				}
			} catch (Exception e) {
				model.addAttribute("error", "Detalle no eliminado");
			}
			return "redirect:/ordenequipo/lista";
		}
	 
	 @GetMapping("/editar/{id}")
		public String editarOrdenEquipo( @PathVariable("id") Integer id, Model model) {

			try {
				Optional<OrdenEquipo> buscado =  ordenEquipoService.findById(id);
				if (buscado.isPresent()) {
					model.addAttribute("ordenequipo", buscado.get());
					
					
					List<Equipo> equipos=equipoService.findAll();
					model.addAttribute("equipo",equipos);
					
					List<Orden> ordenes = ordenService.findAll();
					model.addAttribute("orden",ordenes);
					
					List<Usuario> usuarios = usuarioService.findAll();
					model.addAttribute("usuario",usuarios);
					
					
				} else {
					model.addAttribute("error", "Detalle no encontrado");
				}
			} catch (Exception e) {
				model.addAttribute("error", "Detalle no encontrado");
			}
			return "/ordenequipo/editar";	
		}
}
