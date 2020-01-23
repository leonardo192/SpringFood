package br.com.springfood.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("cozinha")
@Entity
@Table(name="COZINHA")
public class CozinhaEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	/* @JsonIgnore */
	private Long id;
	
	@JsonProperty("titulo")
	private String nome;
	
	@OneToMany
	private List<RestauranteEntity> restaurantes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

	public List<RestauranteEntity> getRestaurantes() {
		return restaurantes;
	}

	public void setRestaurantes(List<RestauranteEntity> restaurantes) {
		this.restaurantes = restaurantes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CozinhaEntity other = (CozinhaEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
