package br.com.knopsistemas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.knopsistemas.entities.Veiculo;



public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
	@Query(value = "SELECT * FROM VEICULO v WHERE v.INQUILINO_ID =?1", nativeQuery = true)	  
	List<Veiculo> findByInquilino(Long id);
	 
	 
	//List<Veiculo> findByNomeStartingWith(String nome);

}
