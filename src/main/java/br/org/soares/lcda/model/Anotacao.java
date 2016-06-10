package br.org.soares.lcda.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("anotacao")
public class Anotacao {

	@XStreamAlias("texto")
	private String texto;

	public Anotacao() {}
	
	public Anotacao(String arg) {
		setTexto(arg);
	}
	
	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public String toString() {
		return "Anotacao [texto=" + texto + "]";
	}
	
	
}
