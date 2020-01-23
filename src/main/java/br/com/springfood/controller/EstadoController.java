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

import br.com.springfood.entity.EstadoEntity;
import br.com.springfood.exception.EntidadeEmUsoException;
import br.com.springfood.exception.EntidadeNaoEncontradaException;
import br.com.springfood.repository.EstadoRepository;
import br.com.springfood.service.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EstadoService estadoService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<EstadoEntity> listar(){
		return estadoRepository.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<EstadoEntity> buscarPorId(@PathVariable Long id) {
		EstadoEntity estado=estadoRepository.findById(id);
		
		if(estado!= null) {
			return ResponseEntity.status(HttpStatus.OK).body(estado);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(value=HttpStatus.CREATED)
	public ResponseEntity<?> adicionar(@RequestBody EstadoEntity estado){
		try {
			estadoService.salve(estado);
			return ResponseEntity.status(HttpStatus.CREATED).body(estado);
			
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	
	@PutMapping("{id}")
	public ResponseEntity<?> atualizar (@PathVariable Long id , @RequestBody EstadoEntity estado){
	
		try {
			EstadoEntity estadoAtual = estadoRepository.findById(id);
			
			
			if (estadoAtual != null) {
				estadoAtual.setNome(estado.getNome());
				estadoService.salve(estadoAtual);
				return ResponseEntity.status(HttpStatus.OK).build();

			}

		}catch (EntidadeNaoEncontradaException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
						
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
   }
	
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> excluir (@PathVariable Long id){
		try {
			estadoService.delete(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
		
		
	}
	
	
}
