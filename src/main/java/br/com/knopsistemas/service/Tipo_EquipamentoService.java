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
import br.com.knopsistemas.entities.Tipo_Equipamento;
import br.com.knopsistemas.repository.Tipo_EquipamentoRepository;

@RestController
@RequestMapping("/tipo_equipamento")
@CrossOrigin(origins = "*")

public class Tipo_EquipamentoService {
	
	@Autowired
	private Tipo_EquipamentoRepository tipo_equipamentoRepository;
	


	@PostMapping
	public @ResponseBody ResponseModel save(@RequestBody Tipo_Equipamento tipo_equipamento){ 
 
		try { 
			this.tipo_equipamentoRepository.save(tipo_equipamento); 
			return new ResponseModel(1,"Registro salvo com sucesso!");
 
		}catch(Exception e) { 
			return new ResponseModel(0,e.getMessage());			
		}
	}
	

	@PutMapping
	public @ResponseBody ResponseModel update(@RequestBody Tipo_Equipamento tipo_equipamento){
 
		try {
 
			this.tipo_equipamentoRepository.save(tipo_equipamento);		
 
			return new ResponseModel(1,"Registro atualizado com sucesso!");
 
		}catch(Exception e) {
 
			return new ResponseModel(0,e.getMessage());
		}
	}
	
	
	@DeleteMapping("/{id}")	
	public @ResponseBody ResponseModel delete(@PathVariable("id") Long id){ 
		Optional <Tipo_Equipamento> tipo_equipamento = tipo_equipamentoRepository.findById(id);
		if (!tipo_equipamento.isPresent()) {				
			return new ResponseModel(0, "Registro inexistente!");						
		
		}
		else { 		
			try {	 
				tipo_equipamentoRepository.delete(tipo_equipamento.get());	 
				return new ResponseModel(1, "Registro excluido com sucesso!");
	 
			}catch(Exception e) {
				return new ResponseModel(0, e.getMessage());
			}
		}
	}
	
	

	
	@GetMapping("/{id}")
	public ResponseEntity<Tipo_Equipamento> findById (@PathVariable Long id){
		Optional<Tipo_Equipamento> tipo_equipamento = tipo_equipamentoRepository.findById(id);
		
		if (tipo_equipamento == null) 
			return ResponseEntity.notFound().build();	 
		else
			return ResponseEntity.ok(tipo_equipamento.get());
					
	}
	

	@GetMapping
	public @ResponseBody List<Tipo_Equipamento> findAll(){ 
		return this.tipo_equipamentoRepository.findAll();
	}
	
	

}
