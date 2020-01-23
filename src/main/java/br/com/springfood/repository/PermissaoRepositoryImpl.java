package br.com.springfood.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.springfood.entity.PermissaoEntity;

public class PermissaoRepositoryImpl implements PermissaoRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	@Transactional
	public void save(PermissaoEntity permissao) {
		manager.persist(permissao);
		
	}

	@Override
	@Transactional
	public void delete(PermissaoEntity permissao) {
		manager.remove(permissao);
		
	}

	@Override
	public List<PermissaoEntity> findAll() {
		
		return manager.createQuery("from PermissaoEntity", PermissaoEntity.class).getResultList();
	}

	@Override
	public PermissaoEntity findById(Long id) {
		
		return manager.find(PermissaoEntity.class, id);
	}

}
