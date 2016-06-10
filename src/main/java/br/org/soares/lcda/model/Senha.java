package br.org.soares.lcda.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("senha")
public class Senha {

	@XStreamAlias("criptograma")
	private String palavra;
	
	public Senha() {}

	public Senha(String arg) {
		setPalavra(arg);
	}

	public String getPalavra() {
		return palavra;
	}

	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}

	@Override
	public String toString() {
		return "Senha [palavra=" + palavra + "]";
	}

}
