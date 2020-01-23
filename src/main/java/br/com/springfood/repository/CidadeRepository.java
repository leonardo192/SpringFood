package br.com.springfood.repository;

import java.util.List;

import br.com.springfood.entity.CidadeEntity;

public interface CidadeRepository {
	
	public CidadeEntity save(CidadeEntity cidade);
	
	public void delete (Long id);
	
	public List<CidadeEntity> findAll();
	
	public CidadeEntity findById(Long id);

}
