package br.com.knopsistemas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.knopsistemas.entities.Ponto_geo;





public interface Ponto_geoRepository extends JpaRepository<Ponto_geo, Long> {
	@Query(value = "SELECT * FROM PONTO_GEO p WHERE p.INQUILINO_ID =?1", nativeQuery = true)	  
	List<Ponto_geo> findByInquilino(Long id);
	 
	 
	//List<Veiculo> findByNomeStartingWith(String nome);

}
