package br.com.springfood.repository;

import java.util.List;

import br.com.springfood.entity.RestauranteEntity;

public interface RestauranteRepository {
	
	public List<RestauranteEntity> listar();
	
	public RestauranteEntity salve (RestauranteEntity restaurante);
	
	public void delete (Long id);
	
	public RestauranteEntity findById(Long id);
	
	
	

}
