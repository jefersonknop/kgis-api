package br.com.knopsistemas.service;

import java.awt.geom.Point2D.Float;
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

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;

import br.com.knopsistemas.entities.ResponseModel;
import br.com.knopsistemas.entities.Tipo_Equipamento;
import br.com.knopsistemas.entities.Ponto_geo;
import br.com.knopsistemas.entities.Registro;
import br.com.knopsistemas.repository.Ponto_geoRepository;
import br.com.knopsistemas.repository.RegistroRepository;

@RestController
@RequestMapping("/registros")
@CrossOrigin(origins = "*")

public class RegistroService {
	
	@Autowired
	private RegistroRepository registroRepository;
	
	@Autowired
	private Ponto_geoRepository ponto_geoRepository;
	


	@PostMapping
	public @ResponseBody ResponseModel save(@RequestBody Registro registro){ 
 
		try { 
			this.registroRepository.save(registro); 
			
			
			
			
			Ponto_geo ponto = new Ponto_geo();
			ponto.setInquilino_id(registro.getInquilino_id());
			ponto.setRegistro_id(registro);
			ponto.setDescricao(registro.getDescricao());
			ponto.setDatahora(registro.getDatahora());
			ponto.setVelocidade(registro.getVelocidade());
			ponto.setAltitude(registro.getAltitude());			
			ponto.setTemperatura(registro.getTemperatura());
			ponto.setUmidade(registro.getUmidade());
			
							
			int numDecPlaces = 7;
			double scale = Math.pow(10, numDecPlaces);
			double x= registro.getLatitude();
			double y = registro.getLongitude();			
			PrecisionModel pm = new PrecisionModel(scale);
	     	GeometryFactory gf = new GeometryFactory(pm);
		    Geometry point = gf.createPoint(new Coordinate(x, y));					  
			  
			ponto.setLocalizacao(point);
					
			
			
			this.ponto_geoRepository.save(ponto);
			
			
			
			return new ResponseModel(1,"Registro salvo com sucesso!");
 
		}catch(Exception e) { 
			return new ResponseModel(0,e.getMessage() + " - "+ e);			
		}
	}
	
	
	
	@PostMapping(path = "/embarcados", consumes = "application/x-www-form-urlencoded")
	public @ResponseBody ResponseModel saveEmbarcado(Registro registro) {
		try { 
	
			//registro.setEvento_id(null);	
			registro.setInquilino_id(new Long(1));	
			
			
			this.registroRepository.save(registro); 
			
			Ponto_geo ponto = new Ponto_geo();
			ponto.setInquilino_id(registro.getInquilino_id());
			ponto.setRegistro_id(registro);
			ponto.setDescricao(registro.getDescricao());
			ponto.setDatahora(registro.getDatahora());
			ponto.setVelocidade(registro.getVelocidade());
			ponto.setAltitude(registro.getAltitude());			
			ponto.setTemperatura(registro.getTemperatura());
			ponto.setUmidade(registro.getUmidade());
			
							
			int numDecPlaces = 7;
			double scale = Math.pow(10, numDecPlaces);
			double x= registro.getLatitude();
			double y = registro.getLongitude();			
			PrecisionModel pm = new PrecisionModel(scale);
	     	GeometryFactory gf = new GeometryFactory(pm);
		    Geometry point = gf.createPoint(new Coordinate(x, y));					  
			  
			ponto.setLocalizacao(point);
					
			
			
			this.ponto_geoRepository.save(ponto);
			
			
			
			return new ResponseModel(1,"Registro salvo com sucesso!");
 
		}catch(Exception e) { 
			return new ResponseModel(0,e.getMessage() + " - "+ e);			
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
