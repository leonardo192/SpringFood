package br.com.springfood.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import br.com.springfood.entity.RestauranteEntity;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<RestauranteEntity> listar() {
		
		return manager.createQuery("from RestauranteEntity ",RestauranteEntity.class ).getResultList();
	}

	@Override
	public RestauranteEntity salve(RestauranteEntity restaurante) {
		manager.persist(restaurante);
		return restaurante;
		
	}

	@Override
	public void delete(Long id) {
		RestauranteEntity restaurante= findById(id);
		
		if(restaurante == null) {
			throw new EmptyResultDataAccessException(1);
		}
		manager.remove(restaurante);
	}

	@Override
	public RestauranteEntity findById(Long id) {
		
		return manager.find(RestauranteEntity.class, id);
	}

}
