package br.com.compasso.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import br.com.compasso.apirest.models.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findById(long id);

	User findByName(String name);


}
