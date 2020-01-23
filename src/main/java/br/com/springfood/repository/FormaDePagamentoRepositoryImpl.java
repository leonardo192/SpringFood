package br.com.springfood.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.springfood.entity.FormaDePagamentoEntity;

public class FormaDePagamentoRepositoryImpl implements FormaDePagamentoRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	@Transactional
	public void save(FormaDePagamentoEntity formaDePagamento) {
		manager.persist(formaDePagamento);
		
	}

	@Override
	@Transactional
	public void delete(FormaDePagamentoEntity formaDePagamento) {
		manager.remove(formaDePagamento);
		
	}

	@Override
	public List<FormaDePagamentoEntity> findAll() {
		
		return manager.createQuery("from FormaDePagamentoEntity", FormaDePagamentoEntity.class).getResultList();
	}

	@Override
	public FormaDePagamentoEntity findById(Long id) {
		
		return manager.find(FormaDePagamentoEntity.class, id);
	}

}
