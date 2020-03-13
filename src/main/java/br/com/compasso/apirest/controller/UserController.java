package br.com.compasso.apirest.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.apirest.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import br.com.compasso.apirest.models.User;

@Slf4j
@RestController
@RequestMapping(value = "/")
@Api(value = "API REST Usuarios")
@CrossOrigin(origins = "*")
public class UserController {

	
		
	
	@Autowired
	UserRepository userRepository;

	@GetMapping("/users")
	@ApiOperation(value = "Return user by name")
	public ResponseEntity<Iterable<User>> findAll(String name) {
		return ResponseEntity.ok(userRepository.findAll());
	}

	@GetMapping("/users/{id}")
	@ApiOperation(value = "Retorn a user by your id.")
	public User listUser(@PathVariable(value = "id") long id) {
		return userRepository.findById(id);
	}

	@PostMapping("/users")
	@ApiOperation(value = "Save a user.")
	public ResponseEntity<?> saveUser(@RequestBody @Valid User user) {
		try {
			userRepository.save(user);
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}

	}

	@DeleteMapping("/users/{id}")
	@ApiOperation(value = "Delet a user by your id.")
	public String deletUserById(@PathVariable(value = "id") long id) {
		try {
			userRepository.deleteById(id);
			return "User with ID " + id + " deleted";
		} catch (Exception e) {
			String error = "Id " + id + " does not exist!";
			return error.toUpperCase();
		}
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
		if (!userRepository.findById(id).isPresent()) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(this.userRepository.save(user));
	}

	


}
