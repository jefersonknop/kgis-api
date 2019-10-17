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
import br.com.knopsistemas.entities.Ponto_geo;
import br.com.knopsistemas.repository.Ponto_geoRepository;

@RestController
@RequestMapping("/pontos")
@CrossOrigin(origins = "*")

public class Ponto_geoService {
	
	@Autowired
	private Ponto_geoRepository ponto_geoRepository;
	


	@PostMapping
	public @ResponseBody ResponseModel save(@RequestBody Ponto_geo ponto_geo){ 
 
		try { 
			this.ponto_geoRepository.save(ponto_geo); 
			return new ResponseModel(1,"Ponto_geo salvo com sucesso!");
 
		}catch(Exception e) { 
			return new ResponseModel(0,e.getMessage());			
		}
	}
	

	@PutMapping
	public @ResponseBody ResponseModel update(@RequestBody Ponto_geo ponto_geo){
 
		try {
 
			this.ponto_geoRepository.save(ponto_geo);		
 
			return new ResponseModel(1,"Ponto_geo atualizado com sucesso!");
 
		}catch(Exception e) {
 
			return new ResponseModel(0,e.getMessage());
		}
	}
	
	
	@DeleteMapping("/{id}")	
	public @ResponseBody ResponseModel delete(@PathVariable("id") Long id){ 
		Optional <Ponto_geo> ponto_geo = ponto_geoRepository.findById(id);
		if (!ponto_geo.isPresent()) {				
			return new ResponseModel(0, "Ponto_geo inexistente!");						
		
		}
		else { 		
			try {	 
				ponto_geoRepository.delete(ponto_geo.get());	 
				return new ResponseModel(1, "Ponto_geo excluido com sucesso!");
	 
			}catch(Exception e) {
				return new ResponseModel(0, e.getMessage());
			}
		}
	}
	
	

	
	@GetMapping("/{id}")
	public ResponseEntity<Ponto_geo> findById (@PathVariable Long id){
		Optional<Ponto_geo> ponto_geo = ponto_geoRepository.findById(id);
		
		if (ponto_geo == null) 
			return ResponseEntity.notFound().build();	 
		else
			return ResponseEntity.ok(ponto_geo.get());
					
	}
	

	@GetMapping("/inquilino/{inquilino}")
	public @ResponseBody List<Ponto_geo> findAll(@PathVariable Long inquilino){
		return this.ponto_geoRepository.findByInquilino(inquilino);
		//return this.linhaRepository.findAll()
	}
	
	/*@GetMapping
	public @ResponseBody List<Ponto_geo> findAll(){ 
		return this.ponto_geoRepository.findAll();
	}

*/
	
	
	

}
