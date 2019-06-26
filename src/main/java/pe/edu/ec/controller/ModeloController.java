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

import pe.edu.ec.entity.Marca;
import pe.edu.ec.entity.Modelo;

import pe.edu.ec.service.MarcaService;
import pe.edu.ec.service.ModeloService;




@Controller
@RequestMapping("/modelo")
@SessionAttributes({"modelo"})
public class ModeloController {

	@Autowired
	private ModeloService modeloService;
	
	@Autowired
	private MarcaService marcaService;
	
	@GetMapping
	public String ListaModelo(Model model) {
		try {
			
			model.addAttribute("modelos", modeloService.findAll());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			
		}
		return "/modelo/lista";
		
	}
	
	@GetMapping("/buscar")
	public String buscarModelo(@RequestParam("inputNombre") String nombre, Model model) {
		
		try {
			List<Modelo > modelos =  modeloService.fetchModeloByNombre(nombre);
			model.addAttribute("modelos",modelos);
			
		} catch (Exception e) {
			model.addAttribute("error", "Error en obtener la lista");
		}
		return "/modelo/lista";
	}
	
	@GetMapping("/nuevo")
	public String nuevoModelo(Model model) {
		try {
			Modelo modelo = new Modelo();
			model.addAttribute("modelo", modelo);
			List<Marca> marcas =marcaService.findAll();
			model.addAttribute("marcas", marcas);
		} catch (Exception e) {
			model.addAttribute("Error", "No se pudo guardar el modelo");
		
		}
		return "/modelo/nuevo"; 
	}
	@GetMapping("/editar/{id}")
	public String editarModelo( @PathVariable("id") Integer id, Model model) {

		try {
			Optional<Modelo> buscado =  modeloService.findById(id);
			if (buscado.isPresent()) {
				model.addAttribute("modelo", buscado.get());
				List<Marca> marcas = marcaService.findAll();
				model.addAttribute("marca", marcas);
			} else {
				model.addAttribute("error", "Modelo no encontrado");
			}
		} catch (Exception e) {
			model.addAttribute("error", "Modelo no encontrado");
		}
		return "/modelo/editar";	
	}
	
	@PostMapping("/guardar")
	public String guardarModelo(@ModelAttribute("modelo") Modelo modelo, Model model, SessionStatus status) {
		try {
			
			modeloService.save(modelo);
			status.setComplete();
			model.addAttribute("success", "Modelo guardado");
		} catch (Exception e) {
			model.addAttribute("error", "Modelo no guardado");
		} 
		return "redirect:/modelo";
	}
	
	
	
	@GetMapping("/eliminar/{id}")
	public String eliminarModelo( @PathVariable("id") Integer id, Model model  ) {
		try {
			Optional<Modelo> buscado = modeloService.findById(id);
			if(buscado.isPresent()) {
				modeloService.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("error", "Modelo no eliminado");
		}
		return "redirect:/modelo";
	}
	
}
