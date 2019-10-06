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
import br.com.knopsistemas.entities.Veiculo;
import br.com.knopsistemas.repository.VeiculoRepository;

@RestController
@RequestMapping("/veiculos")
@CrossOrigin(origins = "*")

public class VeiculoService {
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	


	@PostMapping
	public @ResponseBody ResponseModel save(@RequestBody Veiculo veiculo){ 
 
		try { 
			this.veiculoRepository.save(veiculo); 
			return new ResponseModel(1,"Registro salvo com sucesso!");
 
		}catch(Exception e) { 
			return new ResponseModel(0,e.getMessage());			
		}
	}
	

	@PutMapping
	public @ResponseBody ResponseModel update(@RequestBody Veiculo veiculo){
 
		try {
 
			this.veiculoRepository.save(veiculo);		
 
			return new ResponseModel(1,"Registro atualizado com sucesso!");
 
		}catch(Exception e) {
 
			return new ResponseModel(0,e.getMessage());
		}
	}
	
	
	@DeleteMapping("/{id}")	
	public @ResponseBody ResponseModel delete(@PathVariable("id") Long id){ 
		Optional <Veiculo> veiculo = veiculoRepository.findById(id);
		if (!veiculo.isPresent()) {				
			return new ResponseModel(0, "Registro inexistente!");						
		
		}
		else { 		
			try {	 
				veiculoRepository.delete(veiculo.get());	 
				return new ResponseModel(1, "Registro excluido com sucesso!");
	 
			}catch(Exception e) {
				return new ResponseModel(0, e.getMessage());
			}
		}
	}
	
	

	
	@GetMapping("/{id}")
	public ResponseEntity<Veiculo> findById (@PathVariable Long id){
		Optional<Veiculo> veiculo = veiculoRepository.findById(id);
		
		if (veiculo == null) 
			return ResponseEntity.notFound().build();	 
		else
			return ResponseEntity.ok(veiculo.get());
					
	}
	

	@GetMapping("/inquilino/{inquilino}")
	public @ResponseBody List<Veiculo> findAll(@PathVariable Long inquilino){
		return this.veiculoRepository.findByInquilino(inquilino);
		//return this.linhaRepository.findAll();
	}
	
	/*@GetMapping
	public @ResponseBody List<Veiculo> findAll(){ 
		return this.veiculoRepository.findAll();
	}

*/
	
	
	

}
