package br.com.springfood.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springfood.entity.RestauranteEntity;
import br.com.springfood.exception.EntidadeEmUsoException;
import br.com.springfood.exception.EntidadeNaoEncontradaException;
import br.com.springfood.repository.RestauranteRepository;
import br.com.springfood.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private RestauranteService restauranteService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RestauranteEntity> listar() {
		return restauranteRepository.listar();
	}

	@GetMapping("{id}")
	public ResponseEntity<RestauranteEntity> findById(@PathVariable Long id) {
		RestauranteEntity restaurante = restauranteRepository.findById(id);

		if (restaurante != null) {
			return ResponseEntity.status(HttpStatus.OK).body(restaurante);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<?> add(@RequestBody RestauranteEntity restaurante) {
		try {
			restauranteService.salve(restaurante);
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody RestauranteEntity restaurante) {
		try {
		RestauranteEntity restauranteAtual = restauranteRepository.findById(id);
		
		if (restauranteAtual != null) {
			restauranteAtual.setNome(restaurante.getNome());
			restauranteService.salve(restauranteAtual);
			return ResponseEntity.status(HttpStatus.OK).body(restauranteAtual);
			}
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}

	@DeleteMapping("{id}")
	public ResponseEntity<RestauranteEntity> excluir(@PathVariable Long id) {
		try {
			restauranteService.deletar(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

	}

}
