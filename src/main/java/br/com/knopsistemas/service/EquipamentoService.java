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
import br.com.knopsistemas.entities.Equipamento;
import br.com.knopsistemas.repository.EquipamentoRepository;

@RestController
@RequestMapping("/equipamentos")
@CrossOrigin(origins = "*")

public class EquipamentoService {
	
	@Autowired
	private EquipamentoRepository equipamentoRepository;
	


	@PostMapping
	public @ResponseBody ResponseModel save(@RequestBody Equipamento equipamento){ 
 
		try { 
			this.equipamentoRepository.save(equipamento); 
			return new ResponseModel(1,"Registro salvo com sucesso!");
 
		}catch(Exception e) { 
			return new ResponseModel(0,e.getMessage());			
		}
	}
	

	@PutMapping
	public @ResponseBody ResponseModel update(@RequestBody Equipamento equipamento){
 
		try {
 
			this.equipamentoRepository.save(equipamento);		
 
			return new ResponseModel(1,"Registro atualizado com sucesso!");
 
		}catch(Exception e) {
 
			return new ResponseModel(0,e.getMessage());
		}
	}
	
	
	@DeleteMapping("/{id}")	
	public @ResponseBody ResponseModel delete(@PathVariable("id") Long id){ 
		Optional <Equipamento> equipamento = equipamentoRepository.findById(id);
		if (!equipamento.isPresent()) {				
			return new ResponseModel(0, "Registro inexistente!");						
		
		}
		else { 		
			try {	 
				equipamentoRepository.delete(equipamento.get());	 
				return new ResponseModel(1, "Registro excluido com sucesso!");
	 
			}catch(Exception e) {
				return new ResponseModel(0, e.getMessage());
			}
		}
	}
	
	

	
	@GetMapping("/{id}")
	public ResponseEntity<Equipamento> findById (@PathVariable Long id){
		Optional<Equipamento> equipamento = equipamentoRepository.findById(id);
		
		if (equipamento == null) 
			return ResponseEntity.notFound().build();	 
		else
			return ResponseEntity.ok(equipamento.get());
					
	}
	

	@GetMapping("/inquilino/{inquilino}")
	public @ResponseBody List<Equipamento> findAll(@PathVariable Long inquilino){
		return this.equipamentoRepository.findByInquilino(inquilino);
		//return this.linhaRepository.findAll();
	}
	
	/*@GetMapping
	public @ResponseBody List<Equipamento> findAll(){ 
		return this.equipamentoRepository.findAll();
	}

*/
	
	
	

}
