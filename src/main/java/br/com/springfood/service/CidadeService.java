package br.com.springfood.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.springfood.entity.CidadeEntity;
import br.com.springfood.entity.EstadoEntity;
import br.com.springfood.exception.EntidadeEmUsoException;
import br.com.springfood.exception.EntidadeNaoEncontradaException;
import br.com.springfood.repository.CidadeRepository;
import br.com.springfood.repository.EstadoRepository;

@Service
public class CidadeService {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Transactional
	public CidadeEntity salvar(CidadeEntity cidade) {
		Long estadoId= cidade.getEstado().getId();
		EstadoEntity estado = estadoRepository.findById(estadoId);
		
		if (estado == null) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe estado de id: %d", estadoId));
		}
		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);
		
	}
	
	@Transactional
	public void excluir(Long id) {
		try {
			cidadeRepository.delete(id);
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Cidade de id: %d vinculada a um estado", id));
		}catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Cidade de id: %d não localizada ", id));
		}
		
	}
	
	

}
