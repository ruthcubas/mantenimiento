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


import pe.edu.ec.entity.Orden;

import pe.edu.ec.entity.SolicitudEquipo;

import pe.edu.ec.service.OrdenService;
import pe.edu.ec.service.SolicitudEquipoService;


@Controller
@RequestMapping("/orden")
@SessionAttributes({"orden"})
public class OrdenController {

	@Autowired
	private OrdenService ordenService;
	@Autowired
	private SolicitudEquipoService solicitudEquipoService;
	
	
	@GetMapping("/lista")
	public String ListaOrden(Model model) {
		
		
		try {
			
			model.addAttribute("orden", ordenService.findAll());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			
		}
		return "/orden/lista";
		
	}
	
	

	
	@GetMapping("/buscar")
	public String buscarOrden(@RequestParam("txtId") Integer id, Model model) {
		
		try {
			Optional<Orden> buscado =  ordenService.findById(id);
			List<Orden> solicitudes = new ArrayList<>();
			if (buscado.isPresent()) {
				solicitudes.add(buscado.get());	
			}
			model.addAttribute("orden", solicitudes);
			
		} catch (Exception e) {
			model.addAttribute("error", "Error en obtener la lista");
			return "redirect:/orden/lista";
		}
		return "/orden/lista";
	}
	

	 @GetMapping("/nuevo")
		public String nuevoOrden(Model model) {
			try {
				Orden orden = new Orden();
				model.addAttribute("orden", orden);
				
				List<SolicitudEquipo> solicitudequipos=solicitudEquipoService.findAll();
				model.addAttribute("solicitudequipo",solicitudequipos);
				
			

			} catch (Exception e) {
				model.addAttribute("Error", "No se pudo guardar el detalle");
			
			}
			return "/orden/nuevo"; 
		}
		
	 @PostMapping("/guardar")
		public String guardarOrden(@ModelAttribute("orden") Orden orden, Model model, SessionStatus status) {
			try {	
				ordenService.save(orden);
				status.setComplete();
				model.addAttribute("success", "Orden guardado");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				model.addAttribute("error", "Orden no guardado");
			} 
			return "redirect:/orden/lista";
		}
	  
	 @GetMapping("/eliminar/{id}")
		public String eliminarOrden( @PathVariable("id") Integer id, Model model  ) {
			try {
				Optional<Orden> buscado = ordenService.findById(id);
				if(buscado.isPresent()) {
					ordenService.deleteById(id);
				}
			} catch (Exception e) {
				model.addAttribute("error", "Orden no eliminado");
			}
			return "redirect:/orden/lista";
		}
	 
	 @GetMapping("/editar/{id}")
		public String editarOrden( @PathVariable("id") Integer id, Model model) {

			try {
				Optional<Orden> buscado =  ordenService.findById(id);
				if (buscado.isPresent()) {
					model.addAttribute("orden", buscado.get());
					
					
					List<SolicitudEquipo> solicitudequipos=solicitudEquipoService.findAll();
					model.addAttribute("solicitudequipo",solicitudequipos);
					
					
					
					
				} else {
					model.addAttribute("error", "Orden no encontrado");
				}
			} catch (Exception e) {
				model.addAttribute("error", "Orden no encontrado");
			}
			return "/orden/editar";	
		}
}
