package br.com.compasso.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import br.com.compasso.apirest.models.Media;

public interface MediaRepository extends JpaRepository<Media, Long> {

	Media findById(long id);

	List<Media> findByNome(String nome);




	
	


}
