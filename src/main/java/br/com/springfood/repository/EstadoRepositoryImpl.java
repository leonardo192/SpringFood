package br.com.springfood.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import br.com.springfood.entity.EstadoEntity;

@Repository
public class EstadoRepositoryImpl implements EstadoRepository{

	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public EstadoEntity save(EstadoEntity estado) {
		manager.persist(estado);
		return estado;
	}

	@Override
	public void delete(Long id) {
		EstadoEntity estado = findById(id);
		
		if(estado==null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(estado);
		
	}

	@Override
	public List<EstadoEntity> findAll() {
	
		return manager.createQuery("from EstadoEntity", EstadoEntity.class).getResultList();
	}

	@Override
	public EstadoEntity findById(Long id) {
		
		return manager.find(EstadoEntity.class, id);
	}

}
