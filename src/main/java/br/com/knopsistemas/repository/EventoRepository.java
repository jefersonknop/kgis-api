package br.com.knopsistemas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.knopsistemas.entities.Evento;





public interface EventoRepository extends JpaRepository<Evento, Long> {
	@Query(value = "SELECT * FROM EVENTO e WHERE e.INQUILINO_ID =?1", nativeQuery = true)	  
	List<Evento> findByInquilino(Long id);
	 
	 
	//List<Veiculo> findByNomeStartingWith(String nome);

}
