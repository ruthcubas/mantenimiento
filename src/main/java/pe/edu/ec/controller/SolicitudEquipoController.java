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

import pe.edu.ec.entity.Solicitud;
import pe.edu.ec.entity.SolicitudEquipo;
import pe.edu.ec.service.EquipoService;
import pe.edu.ec.service.SolicitudEquipoService;
import pe.edu.ec.service.SolicitudService;

@Controller
@RequestMapping("/solicitudequipo")
@SessionAttributes({"solicitudequipo"})
public class SolicitudEquipoController {

	@Autowired
	private SolicitudEquipoService solicitudEquipoService;
	@Autowired
	private EquipoService equipoService;
	@Autowired
	private SolicitudService solicitudService;
	
	@GetMapping("/lista")
	public String ListaSolicitudEquipo(Model model) {
		
		
		try {
			
			model.addAttribute("solicitudequipo", solicitudEquipoService.findAll());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			
		}
		return "/solicitudequipo/lista";
		
	}
	
	

	
	@GetMapping("/buscar")
	public String buscarSolicitudEquipo(@RequestParam("txtId") Integer id, Model model) {
		
		try {
			Optional<SolicitudEquipo> buscado =  solicitudEquipoService.findById(id);
			List<SolicitudEquipo> solicitudes = new ArrayList<>();
			if (buscado.isPresent()) {
				solicitudes.add(buscado.get());	
			}
			model.addAttribute("solicitudequipo", solicitudes);
			
		} catch (Exception e) {
			model.addAttribute("error", "Error en obtener la lista");
			return "redirect:/solicitudequipo/lista";
		}
		return "/solicitudequipo/lista";
	}
	

	 @GetMapping("/nuevo")
		public String nuevoSolicitudEquipo(Model model) {
			try {
				SolicitudEquipo solicitudEquipo = new SolicitudEquipo();
				model.addAttribute("solicitudequipo", solicitudEquipo);
				
				List<Equipo> equipos=equipoService.findAll();
				model.addAttribute("equipo",equipos);
				
				List<Solicitud> solicitudes = solicitudService.findAll();
				model.addAttribute("solicitud",solicitudes);

			} catch (Exception e) {
				model.addAttribute("Error", "No se pudo guardar el detalle");
			
			}
			return "/solicitudequipo/nuevo"; 
		}
		
	 @PostMapping("/guardar")
		public String guardarSolicitudEquipo(@ModelAttribute("solicitudequipo") SolicitudEquipo solicitudequipo, Model model, SessionStatus status) {
			try {	
				solicitudEquipoService.save(solicitudequipo);
				status.setComplete();
				model.addAttribute("success", "Detalle guardado");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				model.addAttribute("error", "Detalle no guardado");
			} 
			return "redirect:/solicitudequipo/lista";
		}
	  
	 @GetMapping("/eliminar/{id}")
		public String eliminarSolicitudEquipo( @PathVariable("id") Integer id, Model model  ) {
			try {
				Optional<SolicitudEquipo> buscado = solicitudEquipoService.findById(id);
				if(buscado.isPresent()) {
					solicitudEquipoService.deleteById(id);
				}
			} catch (Exception e) {
				model.addAttribute("error", "Detalle no eliminado");
			}
			return "redirect:/solicitudequipo/lista";
		}
	 
	 @GetMapping("/editar/{id}")
		public String editarSolicitudEquipo( @PathVariable("id") Integer id, Model model) {

			try {
				Optional<SolicitudEquipo> buscado =  solicitudEquipoService.findById(id);
				if (buscado.isPresent()) {
					model.addAttribute("solicitudequipo", buscado.get());
					
					
					List<Equipo> equipos=equipoService.findAll();
					model.addAttribute("equipo",equipos);
					
					List<Solicitud> solicitudes = solicitudService.findAll();
					model.addAttribute("solicitud",solicitudes);
					
					
				} else {
					model.addAttribute("error", "Detalle no encontrado");
				}
			} catch (Exception e) {
				model.addAttribute("error", "Detalle no encontrado");
			}
			return "/solicitudequipo/editar";	
		}
		
	}




	

