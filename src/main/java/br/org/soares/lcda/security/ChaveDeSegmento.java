package br.org.soares.lcda.security;

import java.security.SecureRandom;

import br.org.soares.lcda.persistence.ArquivoDeSegmento;

public class ChaveDeSegmento {

	private SecureRandom garantia;
	private ArquivoDeSegmento arquivo;
	
	public ChaveDeSegmento() {
		garantia = new SecureRandom();
		arquivo = new ArquivoDeSegmento();
	}	
	
	public String obter() {
		return arquivo.existe()? arquivo.ler(): this.gerar();
	}
	
	private String gerar() {
		byte bytes[] = new byte[16];
		garantia.nextBytes(bytes);
		arquivo.gravar(new String(bytes));
		return arquivo.ler();
	}

}
