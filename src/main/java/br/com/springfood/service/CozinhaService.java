package br.com.springfood.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.springfood.entity.CozinhaEntity;
import br.com.springfood.exception.EntidadeEmUsoException;
import br.com.springfood.exception.EntidadeNaoEncontradaException;
import br.com.springfood.repository.CozinhaRepository;

@Service
public class CozinhaService {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Transactional
	public void salve(CozinhaEntity cozinha) {
		cozinhaRepository.salve(cozinha);
	}

	/*
	 * @Transactional public ResponseEntity<CozinhaEntity> delete(CozinhaEntity
	 * cozinha) { try { if (cozinha != null) { cozinhaRepository.delete(cozinha);
	 * return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); } } catch
	 * (DataIntegrityViolationException e) { return
	 * ResponseEntity.status(HttpStatus.CONFLICT).build(); } return
	 * ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	 * 
	 * }
	 */
	@Transactional
	public void delete(Long id) {
		try {
			cozinhaRepository.delete(id);
		}catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException("Cozinha não encontrada!");
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("Existem restaurantes vinculados a este tipo de cozinha");
		}
		
	}

}
