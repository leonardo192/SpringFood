package br.com.springfood.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.springfood.entity.EstadoEntity;
import br.com.springfood.exception.EntidadeEmUsoException;
import br.com.springfood.exception.EntidadeNaoEncontradaException;
import br.com.springfood.repository.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Transactional
	public EstadoEntity salve(EstadoEntity estado) {
		return estadoRepository.save(estado);
	}
	
	@Transactional
	public void delete (Long id) {
		try {
			estadoRepository.delete(id);
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("Estado vinculado a uma cidade");
		}catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException("Entidade não localizada");
		}
	}
	
}
