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

import pe.edu.ec.entity.Solicitud;
import pe.edu.ec.service.SolicitudService;

@Controller
@RequestMapping("/solicitud")
@SessionAttributes({"solicitud"})
public class SolicitudController {
	
	@Autowired
	private SolicitudService solicitudService;
	
	@GetMapping("/lista")
	public String ListaSolicitud(Model model) {
		try {
			
			model.addAttribute("solicitud", solicitudService.findAll());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			
		}
		return "/solicitud/lista";
		
	}
	@GetMapping("/buscar")
	public String buscarSolicitud(@RequestParam("txtId") Integer id, Model model) {
		
		try {
			Optional<Solicitud> buscado =  solicitudService.findById(id);
			List<Solicitud> solicitudes = new ArrayList<>();
			if (buscado.isPresent()) {
				solicitudes.add(buscado.get());	
			}else {}
			model.addAttribute("solicitud", solicitudes);
			
		} catch (Exception e) {
			model.addAttribute("error", "Error en obtener la lista");
			return "redirect:/solicitud/lista";
		}
		return "/solicitud/lista";
	}
	
	
	
	@GetMapping("/nuevo")
	public String nuevaSolicitud(Model model) {
		try {
			Solicitud solicitud = new Solicitud();
			model.addAttribute("solicitud", solicitud);
			
			
		} catch (Exception e) {
			model.addAttribute("Error", "La solicitud no se ha guardado");
		}
		
		return "/solicitud/nuevo"; 
	}
	
	@GetMapping("/editar/{id}")
	public String editarSolicitud( @PathVariable("id") Integer id, Model model) {

		try {
			Optional<Solicitud> buscado =  solicitudService.findById(id);
			if (buscado.isPresent()) {
				model.addAttribute("solicitud", buscado.get());
			} else {
				model.addAttribute("error", "Solicitud no encontrada");
			}
		} catch (Exception e) {
			model.addAttribute("error", "Solicitud no encontrada");
		}
		return "/solicitud/editar";
		
	}
	
	@PostMapping("/guardar")
	public String guardarSolicitud(@ModelAttribute("solicitud") Solicitud solicitud, Model model, SessionStatus status) {
		try {
			
			solicitudService.save(solicitud);
			status.setComplete();
			model.addAttribute("success", "Solicitud guarada");
		} catch (Exception e) {
			model.addAttribute("error", "Solicitud no guardada");
		} 
		return "redirect:/solicitud/lista";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarSolicitud( @PathVariable("id") Integer id, Model model  ) {
		try {
			Optional<Solicitud> buscado = solicitudService.findById(id);
			if(buscado.isPresent()) {
				solicitudService.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("error", "Solicitud no eliminada");
		}
		return "redirect:/solicitud/lista";
	}

}
