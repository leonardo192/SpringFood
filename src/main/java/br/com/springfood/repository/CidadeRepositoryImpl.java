package br.com.springfood.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import br.com.springfood.entity.CidadeEntity;

@Repository
public class CidadeRepositoryImpl implements CidadeRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public CidadeEntity save(CidadeEntity cidade) {
		manager.persist(cidade);
		return cidade;
		
	}

	@Override
	public void delete(Long id) {
		CidadeEntity cidade =  findById(id);
		if(cidade == null) {
			throw new EmptyResultDataAccessException(1);
		}
		manager.remove(cidade);
		
	}

	@Override
	public List<CidadeEntity> findAll() {
		
		return manager.createQuery("from CidadeEntity", CidadeEntity.class).getResultList();
	}

	@Override
	public CidadeEntity findById(Long id) {
		
		return manager.find(CidadeEntity.class, id);
	}

}
