package br.com.knopsistemas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.knopsistemas.entities.Registro;





public interface RegistroRepository extends JpaRepository<Registro, Long> {
	@Query(value = "SELECT * FROM REGISTRO r WHERE r.INQUILINO_ID =?1", nativeQuery = true)	  
	List<Registro> findByInquilino(Long id);
	 
	 
	//List<Veiculo> findByNomeStartingWith(String nome);

}
