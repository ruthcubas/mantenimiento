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
import pe.edu.ec.service.MarcaService;



@Controller
@RequestMapping("/marca")
@SessionAttributes({"marca"})
public class MarcaController {

	@Autowired
	private MarcaService marcaService;
	
	
	@GetMapping("/lista")
	public String ListaMarca(Model model) {
		try {
			
			model.addAttribute("marcas", marcaService.findAll());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			
		}
		return "/marca/lista";
		
	}
	
	@GetMapping("/buscar")
	public String buscarMarca(@RequestParam("nombre") String nombre, Model model) {
		
		try {
			
			if(!nombre.isEmpty()) {
				List<Marca> marcas = marcaService.fetchMarcaByNombre(nombre);
				if(!marcas.isEmpty()) {
					model.addAttribute("marcas", marcas );
					}
					else {
						model.addAttribute("info", "No existe marca");
						model.addAttribute("marca",marcaService.findAll() );
				}
				
			}else {
				model.addAttribute("info", "Ingresar nombre de la marca");
				model.addAttribute("marcas",marcaService.findAll() );
			}
			
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return"/marca/lista";
	}
	
	
	@GetMapping("/nuevo")
	public String nuevaMarca(Model model) {
		try {
			Marca marca = new Marca();
			model.addAttribute("marca", marca);
			
			
		} catch (Exception e) {
			model.addAttribute("Error", "Marca no se ha guardado");
		}
		
		return "/marca/nuevo"; 
	}
	
	@GetMapping("/editar/{id}")
	public String editarMarca( @PathVariable("id") Integer id, Model model) {

		try {
			Optional<Marca> buscado =  marcaService.findById(id);
			if (buscado.isPresent()) {
				model.addAttribute("marca", buscado.get());
			} else {
				model.addAttribute("error", "Marca no encontrada");
			}
		} catch (Exception e) {
			model.addAttribute("error", "Marca no encontrada");
		}
		return "/marca/editar";
		
	}
	
	@PostMapping("/guardar")
	public String guardarMarca(@ModelAttribute("marca") Marca marca, Model model, SessionStatus status) {
		try {
			
			marcaService.save(marca);
			status.setComplete();
			model.addAttribute("success", "Marca guarada");
		} catch (Exception e) {
			model.addAttribute("error", "Marca no guardada");
		} 
		return "redirect:/marca/lista";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarMarca( @PathVariable("id") Integer id, Model model  ) {
		try {
			Optional<Marca> buscado = marcaService.findById(id);
			if(buscado.isPresent()) {
				marcaService.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("error", "Marca no eliminada");
		}
		return "redirect:/marca/lista";
	}
	
	
	
}
