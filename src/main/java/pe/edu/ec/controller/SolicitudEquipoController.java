package pe.edu.ec.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

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
			SolicitudEquipo solicitudequipo = new SolicitudEquipo();
			
			
			Optional<Equipo> equipo = equipoService.findById(0);
			solicitudequipo.setEquipoId(equipo.get());	
			
			Optional<Solicitud> solicitud = solicitudService.findById(0);
			solicitudequipo.setSolicitudId(solicitud.get());
			
			model.addAttribute("solicitudequipo", solicitudequipo );	
			
			List<Equipo> equipos = equipoService.findAll();
			model.addAttribute("equipo", equipos);
			
			List<Solicitud> solicituds= solicitudService.findAll();
			model.addAttribute("solicitud", solicituds);

		} catch (Exception e) {
			model.addAttribute("Error", "El modelo no se ha guardado");
		}
		
		return "/solicitudequipo/nuevo"; 
	}
}
