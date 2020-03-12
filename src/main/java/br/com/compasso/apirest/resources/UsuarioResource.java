package br.com.compasso.apirest.resources;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
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

import br.com.compasso.apirest.repository.UsuarioRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import br.com.compasso.apirest.models.Usuario;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Usuarios")
@CrossOrigin(origins = "*")
public class UsuarioResource {

	@Autowired
	UsuarioRepository usuarioRepository;

	@GetMapping("/usuarios")
	@ApiOperation(value = "Retorna todos os usuarios")
	public List<Usuario> listaTodosUsuarios(String nome) {
		return usuarioRepository.findAll();

	}

//	@GetMapping("/usuarios")
//	@ApiOperation(value = "Retorna um usuário pelo nome.")
//	public Usuario nomeUsuario(String nome) {
//		return usuarioRepository.findByNome(nome);
//	}

	@GetMapping("/usuarios/{id}")
	@ApiOperation(value = "Retorna um usuário pelo seu id.")
	public Usuario listaUsuarios(@PathVariable(value = "id") long id) {
		return usuarioRepository.findById(id);
	}

	@PostMapping("/usuarios")
	@ApiOperation(value = "Salva um usuário.")
	public ResponseEntity<?> salvaUsuario(@RequestBody @Valid Usuario usuario) {
		try {
			usuarioRepository.save(usuario);
			return new ResponseEntity<>(usuario, HttpStatus.CREATED);

		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);

		}

	}

	@DeleteMapping("/usuarios/{id}")
	@ApiOperation(value = "Deleta um usuário pelo seu id.")
	public String deletaUsuarioComId(@PathVariable(value = "id") long id) {
		try {
			usuarioRepository.deleteById(id);
			return "Usuario com ID " + id + " deletado";
		} catch (Exception e) {
			String error = "Id " + id + " não existe ou tem mideas associadas!";
			return error;
		}
	}

	@PutMapping("/usuarios")
	@ApiOperation(value = "Atualiza um usuário.")
	public void atualizaUsuario(@RequestBody Usuario usuario) {
		usuarioRepository.save(usuario);
	}

}
