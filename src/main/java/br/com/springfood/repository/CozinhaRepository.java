package br.com.springfood.repository;

import java.util.List;

import br.com.springfood.entity.CozinhaEntity;


public interface CozinhaRepository {

	public List<CozinhaEntity> listar();

	public void salve(CozinhaEntity cozinha);

	public void delete(Long id);

	public CozinhaEntity findById(Long id);
}
