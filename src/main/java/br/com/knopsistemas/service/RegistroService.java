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
import br.com.knopsistemas.entities.Registro;
import br.com.knopsistemas.repository.RegistroRepository;

@RestController
@RequestMapping("/registros")
@CrossOrigin(origins = "*")

public class RegistroService {
	
	@Autowired
	private RegistroRepository registroRepository;
	


	@PostMapping
	public @ResponseBody ResponseModel save(@RequestBody Registro registro){ 
 
		try { 
			this.registroRepository.save(registro); 
			return new ResponseModel(1,"Registro salvo com sucesso!");
 
		}catch(Exception e) { 
			return new ResponseModel(0,e.getMessage());			
		}
	}
	

	@PutMapping
	public @ResponseBody ResponseModel update(@RequestBody Registro registro){
 
		try {
 
			this.registroRepository.save(registro);		
 
			return new ResponseModel(1,"Registro atualizado com sucesso!");
 
		}catch(Exception e) {
 
			return new ResponseModel(0,e.getMessage());
		}
	}
	
	
	@DeleteMapping("/{id}")	
	public @ResponseBody ResponseModel delete(@PathVariable("id") Long id){ 
		Optional <Registro> registro = registroRepository.findById(id);
		if (!registro.isPresent()) {				
			return new ResponseModel(0, "Registro inexistente!");						
		
		}
		else { 		
			try {	 
				registroRepository.delete(registro.get());	 
				return new ResponseModel(1, "Registro excluido com sucesso!");
	 
			}catch(Exception e) {
				return new ResponseModel(0, e.getMessage());
			}
		}
	}
	
	

	
	@GetMapping("/{id}")
	public ResponseEntity<Registro> findById (@PathVariable Long id){
		Optional<Registro> registro = registroRepository.findById(id);
		
		if (registro == null) 
			return ResponseEntity.notFound().build();	 
		else
			return ResponseEntity.ok(registro.get());
					
	}
	

	@GetMapping("/inquilino/{inquilino}")
	public @ResponseBody List<Registro> findAll(@PathVariable Long inquilino){
		return this.registroRepository.findByInquilino(inquilino);
		//return this.linhaRepository.findAll();
	}
	
	/*@GetMapping
	public @ResponseBody List<Registro> findAll(){ 
		return this.registroRepository.findAll();
	}

*/
	
	
	

}
