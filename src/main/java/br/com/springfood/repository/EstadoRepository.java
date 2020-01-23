package br.com.springfood.repository;

import java.util.List;

import br.com.springfood.entity.EstadoEntity;

public interface EstadoRepository {
	
	public EstadoEntity save(EstadoEntity estado);
	
	public void delete (Long id);
	
	public List<EstadoEntity> findAll();
	
	public EstadoEntity findById(Long id);

}
