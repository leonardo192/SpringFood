package br.com.springfood.repository;

import java.util.List;

import br.com.springfood.entity.PermissaoEntity;

public interface PermissaoRepository {
	
	public void save (PermissaoEntity permissao);
	
	public void delete (PermissaoEntity permissao);
	
	public List<PermissaoEntity> findAll();
	
	public PermissaoEntity findById(Long id);

}
