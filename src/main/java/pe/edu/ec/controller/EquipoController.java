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


import pe.edu.ec.entity.Equipo;
import pe.edu.ec.entity.Modelo;
import pe.edu.ec.service.EquipoService;
import pe.edu.ec.service.ModeloService;



@Controller
@RequestMapping("/equipo")
@SessionAttributes({"equipo"})
public class EquipoController {


	@Autowired
	private EquipoService equipoService;
	
	@Autowired
	private ModeloService modeloService;
	
	
	@GetMapping("/lista")
	public String ListaEquipo(Model model) {
		try {
			
			model.addAttribute("equipo", equipoService.findAll());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			
		}
		return "/equipo/lista";
		
	}
	
	@GetMapping("/buscar")
	public String buscarEquipo(@RequestParam("nombre") String nombre, Model model) {
		
		try {
			
			if(!nombre.isEmpty()) {
				List<Equipo> equipos = equipoService.fetchEquipoByNombre(nombre);
				if(!equipos.isEmpty()) {
					model.addAttribute("equipo", equipos );
					}
					else {
						model.addAttribute("info", "No existe equipo");
						model.addAttribute("equipo",equipoService.findAll() );
				}
				
			}else {
				model.addAttribute("info", "Ingresar nombre del equipo");
				model.addAttribute("equipo",equipoService.findAll() );
			}
			
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return"/equipo/lista";
	}
	
	@GetMapping("/nuevo")
	public String nuevoEquipo(Model model) {
		try {
			Equipo equipo = new Equipo();
			Optional<Modelo> modelo = modeloService.findById(1);
			equipo.setModeloId(modelo.get());	
			model.addAttribute("equipo", equipo);	
			
			List<Modelo> modelos = modeloService.findAll();
			model.addAttribute("modelo", modelos);

		} catch (Exception e) {
			model.addAttribute("Error", "El equipo no se ha guardado");
		}
		
		return "/equipo/nuevo"; 
	}
	@GetMapping("/editar/{id}")
	public String editarEquipo( @PathVariable("id") Integer id, Model model) {

		try {
			Optional<Equipo> buscado =  equipoService.findById(id);
			if (buscado.isPresent()) {
				model.addAttribute("equipo", buscado.get());
				List<Modelo> modelos = modeloService.findAll();
				model.addAttribute("modelo", modelos);
			} else {
				model.addAttribute("error", "Equipo no encontrado");
			}
		} catch (Exception e) {
			model.addAttribute("error", "Equipo no encontrado");
		}
		return "/equipo/editar";
		
	}
	
	@PostMapping("/guardar")
	public String guardarEquipo(@ModelAttribute("equipo") Equipo equipo, Model model, SessionStatus status) {
		try {
			
			equipoService.save(equipo);
			status.setComplete();
			model.addAttribute("success", "Equipo guardado");
		} catch (Exception e) {
			model.addAttribute("error", "Equipo no guardado");
		} 
		return "redirect:/equipo/lista";
	}
	
	
	
	@GetMapping("/eliminar/{id}")
	public String eliminarEquipo( @PathVariable("id") Integer id, Model model  ) {
		try {
			Optional<Equipo> buscado = equipoService.findById(id);
			if(buscado.isPresent()) {
				equipoService.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("error", "Equipo no eliminado");
		}
		return "redirect:/equipo/lista";
	}
	
}
