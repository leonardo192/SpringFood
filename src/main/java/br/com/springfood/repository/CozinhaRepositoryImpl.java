package br.com.springfood.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import br.com.springfood.entity.CozinhaEntity;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<CozinhaEntity> listar() {
		
		return manager.createQuery("from CozinhaEntity", CozinhaEntity.class).getResultList();
	}

	@Override
	@Transactional
	public void salve(CozinhaEntity cozinha) {
		manager.persist(cozinha);
		
	}

	@Override
	@Transactional
	public void delete(Long id) {
		CozinhaEntity cozinha = findById(id);
		if(cozinha==null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(cozinha);
		
	}

	@Override
	public CozinhaEntity findById(Long id) {
		
		return manager.find(CozinhaEntity.class, id);
	}

}
