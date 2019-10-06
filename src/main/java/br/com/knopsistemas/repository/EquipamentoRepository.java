package br.com.knopsistemas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.knopsistemas.entities.Equipamento;



public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
	@Query(value = "SELECT * FROM EQUIPAMENTO e WHERE e.INQUILINO_ID =?1", nativeQuery = true)	  
	List<Equipamento> findByInquilino(Long id);
	 
	 
	//List<Veiculo> findByNomeStartingWith(String nome);

}
