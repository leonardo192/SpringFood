package br.com.springfood.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.springfood.entity.CozinhaEntity;
import br.com.springfood.entity.RestauranteEntity;
import br.com.springfood.exception.EntidadeEmUsoException;
import br.com.springfood.exception.EntidadeNaoEncontradaException;
import br.com.springfood.repository.CozinhaRepository;
import br.com.springfood.repository.RestauranteRepository;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Transactional
	public RestauranteEntity salve(RestauranteEntity restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		CozinhaEntity cozinha = cozinhaRepository.findById(cozinhaId);

		if (cozinha == null) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe cadastro de cozinha com o código %d", cozinhaId));
		}
		restaurante.setCozinha(cozinha);
		return restauranteRepository.salve(restaurante);
		
	}

	@Transactional
	public void deletar(Long id) {
		try {
			restauranteRepository.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("O restaurande de id: %d está vinculado a uma cozinha", id));
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Restaurante de id : não encontrado", id));
		}

	}

}
