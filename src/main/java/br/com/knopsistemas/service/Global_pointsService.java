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
import br.com.knopsistemas.entities.Global_points;
import br.com.knopsistemas.repository.Global_pointsRepository;

@RestController
@RequestMapping("/points")
@CrossOrigin(origins = "*")

public class Global_pointsService {
	
	@Autowired
	private Global_pointsRepository global_pointsRepository;
	


	@PostMapping
	public @ResponseBody ResponseModel save(@RequestBody Global_points global_points){ 
 
		try { 
			//this.global_pointsRepository.save(global_points); 
			return new ResponseModel(1,"Registro salvo com sucesso!");
 
		}catch(Exception e) { 
			return new ResponseModel(0,e.getMessage());			
		}
	}
	

	@PutMapping
	public @ResponseBody ResponseModel update(@RequestBody Global_points global_points){
 
		try {
 
			this.global_pointsRepository.save(global_points);		
 
			return new ResponseModel(1,"Registro atualizado com sucesso!");
 
		}catch(Exception e) {
 
			return new ResponseModel(0,e.getMessage());
		}
	}
	
	
	@DeleteMapping("/{id}")	
	public @ResponseBody ResponseModel delete(@PathVariable("id") Long id){ 
		Optional <Global_points> global_points = global_pointsRepository.findById(id);
		if (!global_points.isPresent()) {				
			return new ResponseModel(0, "Registro inexistente!");						
		
		}
		else { 		
			try {	 
				global_pointsRepository.delete(global_points.get());	 
				return new ResponseModel(1, "Registro excluido com sucesso!");
	 
			}catch(Exception e) {
				return new ResponseModel(0, e.getMessage());
			}
		}
	}
	
	

	
	@GetMapping("/{id}")
	public ResponseEntity<Global_points> findById (@PathVariable Long id){
		Optional<Global_points> global_points = global_pointsRepository.findById(id);
		
		if (global_points == null) 
			return ResponseEntity.notFound().build();	 
		else
			return ResponseEntity.ok(global_points.get());
					
	}
	


	
    @GetMapping
	public @ResponseBody List<Global_points> findAll(){ 
		return this.global_pointsRepository.findAll();
	}


	
	
	

}
