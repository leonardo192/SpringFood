package br.com.springfood.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName="cozinhas")
public class CozinhaXmlWrapper {
	
	@JsonProperty("cozinha")
	@JacksonXmlElementWrapper(useWrapping=false)
	private List<CozinhaEntity> cozinhas;
	

	public CozinhaXmlWrapper(List<CozinhaEntity> cozinhas) {
		this.cozinhas = cozinhas;
	}

	public List<CozinhaEntity> getCozinhas() {
		return cozinhas;
	}

	public void setCozinhas(List<CozinhaEntity> cozinhas) {
		this.cozinhas = cozinhas;
	}
	
	

}
