package br.com.compasso.apirest.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.apirest.repository.MediaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import br.com.compasso.apirest.models.Media;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Medias")
@CrossOrigin(origins = "*")
public class MediaResource {

	@Autowired
	MediaRepository mediaRepository;

	@GetMapping("/medias")
	@ApiOperation(value = "Retorna uma lista de medias.")
	public List<Media> listaMedias(String nome) {
		if (nome == null) {
			return mediaRepository.findAll();
		} else {
			return mediaRepository.findByNome(nome);
		}
	}

	@GetMapping("/medias/{id}")
	@ApiOperation(value = "Retorna uma media pela id.")
	public Media listaMedia(@PathVariable(value = "id") long id) {
		return mediaRepository.findById(id);
	}

	@PostMapping("/medias")
	@ApiOperation(value = "Salva uma media.")
	public Media salvaMedia(@RequestBody Media media) {
		return mediaRepository.save(media);
	}

	@DeleteMapping("/medias/{id}")
	@ApiOperation(value = "Deleta uma media pelo seu id.")
	public void deletaMediaComId(@PathVariable(value = "id") long id) {
		mediaRepository.deleteById(id);
	}

	@PutMapping("/medias")
	@ApiOperation(value = "Atualiza uma media.")
	public void atualizaMunicipio(@RequestBody Media media) {
		mediaRepository.save(media);
	}

}
