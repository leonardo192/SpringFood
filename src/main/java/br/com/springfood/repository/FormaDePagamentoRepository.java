package br.com.springfood.repository;

import java.util.List;

import br.com.springfood.entity.FormaDePagamentoEntity;

public interface FormaDePagamentoRepository {
	
	public void save(FormaDePagamentoEntity formaDePagamento);
	
	public void delete (FormaDePagamentoEntity formaDePagamento);
	
	public List<FormaDePagamentoEntity> findAll();
	
	public FormaDePagamentoEntity findById(Long id);

}
