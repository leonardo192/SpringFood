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

import br.com.springfood.entity.CozinhaEntity;
import br.com.springfood.entity.CozinhaXmlWrapper;
import br.com.springfood.exception.EntidadeEmUsoException;
import br.com.springfood.exception.EntidadeNaoEncontradaException;
import br.com.springfood.repository.CozinhaRepository;
import br.com.springfood.service.CozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CozinhaService cozinhaService;

	@GetMapping
	public List<CozinhaEntity> listar() {
		return cozinhaRepository.listar();
	}

	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public CozinhaXmlWrapper listarXml() {
		return new CozinhaXmlWrapper(cozinhaRepository.listar());

	}

	/*
	 * @ResponseStatus
	 * 
	 * @GetMapping("{id}") public CozinhaEntity buscarPorId(@PathVariable Long id) {
	 * return cozinhaRepository.findById(id); }
	 */

	@GetMapping("{id}")
	public ResponseEntity<CozinhaEntity> buscarPorId(@PathVariable Long id) {
		CozinhaEntity cozinha = cozinhaRepository.findById(id);

		/*
		 * HttpHeaders headers = new HttpHeaders();
		 * 
		 * headers.add(HttpHeaders.LOCATION, "localhost:8080/cozinhas");
		 * 
		 * 
		 * return ResponseEntity .status(HttpStatus.NOT_FOUND) .headers(headers)
		 * .body(cozinha);
		 */

		if (cozinha != null) {
			return ResponseEntity.ok(cozinha);
		}

		return ResponseEntity.notFound().build();

	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void add(@RequestBody CozinhaEntity cozinha) {
		cozinhaService.salve(cozinha);
	}

	@PutMapping("{id}")
	public ResponseEntity<CozinhaEntity> atualizar(@PathVariable Long id, @RequestBody CozinhaEntity cozinha) {
		CozinhaEntity cozinhaAtual = cozinhaRepository.findById(id);

		if (cozinhaAtual != null) {
			cozinhaAtual.setNome(cozinha.getNome());
			cozinhaService.salve(cozinhaAtual);
			return ResponseEntity.status(HttpStatus.OK).body(cozinhaAtual);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}

	@DeleteMapping("{id}")
	public ResponseEntity<CozinhaEntity> excluir(@PathVariable Long id) {
		/*
		 * try { CozinhaEntity cozinha = cozinhaRepository.findById(id);
		 * 
		 * if (cozinha != null) { cozinhaService.delete(cozinha); return
		 * ResponseEntity.status(HttpStatus.NO_CONTENT).build(); } return
		 * ResponseEntity.status(HttpStatus.NOT_FOUND).build(); } catch
		 * (DataIntegrityViolationException e) { return
		 * ResponseEntity.status(HttpStatus.CONFLICT).build(); }
		 */
		try {
			cozinhaService.delete(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}
}
