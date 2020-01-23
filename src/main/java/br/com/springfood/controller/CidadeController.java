package br.com.springfood.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import br.com.springfood.entity.CidadeEntity;
import br.com.springfood.exception.EntidadeEmUsoException;
import br.com.springfood.exception.EntidadeNaoEncontradaException;
import br.com.springfood.repository.CidadeRepository;
import br.com.springfood.service.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CidadeService cidadeService;
	
	@GetMapping
	public List<CidadeEntity> listar(){
		return cidadeRepository.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<CidadeEntity> buscarPorId(@PathVariable Long id){
		CidadeEntity cidade = cidadeRepository.findById(id);
		
		if(cidade != null) {
			return ResponseEntity.status(HttpStatus.OK).body(cidade);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		
	}
	
	@PostMapping
	@ResponseStatus(code=HttpStatus.CREATED)
	public ResponseEntity<?> salvar (@RequestBody CidadeEntity cidade) {
		try {
			cidadeService.salvar(cidade);
			return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
	
	
	
	@PutMapping("{id}")
	public ResponseEntity<CidadeEntity> atualizar(@PathVariable Long id , @RequestBody CidadeEntity cidade){
		CidadeEntity cidadeAtual = cidadeRepository.findById(id);
		
		if(cidadeAtual != null) {
			cidadeAtual.setNome(cidade.getNome());
			cidadeService.salvar(cidadeAtual);
			return ResponseEntity.status(HttpStatus.OK).body(cidadeAtual);
			
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> excluir (@PathVariable Long id){
		try {
			cidadeService.excluir(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			
		}catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	

}
