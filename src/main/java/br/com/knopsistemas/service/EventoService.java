package br.com.knopsistemas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.knopsistemas.entities.ResponseModel;
import br.com.knopsistemas.entities.Evento;
import br.com.knopsistemas.repository.EventoRepository;

@RestController
@RequestMapping("/eventos")
@CrossOrigin(origins = "*")

public class EventoService {
	
	@Autowired
	private EventoRepository eventoRepository;
	


	@PostMapping
	public @ResponseBody ResponseModel save(@RequestBody Evento evento){ 
 
		try { 
			this.eventoRepository.save(evento); 
			return new ResponseModel(1,"Registro salvo com sucesso!");
 
		}catch(Exception e) { 
			return new ResponseModel(0,e.getMessage());			
		}
	}
	

	@PutMapping
	public @ResponseBody ResponseModel update(@RequestBody Evento evento){
 
		try {
 
			this.eventoRepository.save(evento);		
 
			return new ResponseModel(1,"Registro atualizado com sucesso!");
 
		}catch(Exception e) {
 
			return new ResponseModel(0,e.getMessage());
		}
	}
	
	
	@DeleteMapping("/{id}")	
	public @ResponseBody ResponseModel delete(@PathVariable("id") Long id){ 
		Optional <Evento> evento = eventoRepository.findById(id);
		if (!evento.isPresent()) {				
			return new ResponseModel(0, "Registro inexistente!");						
		
		}
		else { 		
			try {	 
				eventoRepository.delete(evento.get());	 
				return new ResponseModel(1, "Registro excluido com sucesso!");
	 
			}catch(Exception e) {
				return new ResponseModel(0, e.getMessage());
			}
		}
	}
	
	

	
	@GetMapping("/{id}")
	public ResponseEntity<Evento> findById (@PathVariable Long id){
		Optional<Evento> evento = eventoRepository.findById(id);
		
		if (evento == null) 
			return ResponseEntity.notFound().build();	 
		else
			return ResponseEntity.ok(evento.get());
					
	}
	

	@GetMapping("/inquilino/{inquilino}")
	public @ResponseBody List<Evento> findAll(@PathVariable Long inquilino){
		return this.eventoRepository.findByInquilino(inquilino);
		//return this.linhaRepository.findAll();
	}
	
	/*@GetMapping
	public @ResponseBody List<Evento> findAll(){ 
		return this.eventoRepository.findAll();
	}

*/
	
	
	

}
