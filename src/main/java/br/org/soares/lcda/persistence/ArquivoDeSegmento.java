package br.org.soares.lcda.persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class ArquivoDeSegmento {

	private static final String LOCAL = "repository/segmento.dat";
	
	private File arquivo;
	private BufferedReader leitor;

	public ArquivoDeSegmento() {
		arquivo = new File(LOCAL);
	}

	public boolean existe() {		
		return arquivo.exists()? true: false;
	}
	
	public void gravar(String segmento) {
		FileOutputStream fluxoDeSaida;
		try {
			if (!existe()) {
				fluxoDeSaida = new FileOutputStream(arquivo);
				fluxoDeSaida.write(segmento.getBytes());
				fluxoDeSaida.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String ler() {
		FileReader arquivoDeLeitura;
		try {
			if (arquivo.exists()) {
				arquivoDeLeitura = new FileReader(arquivo);
				leitor = new BufferedReader(arquivoDeLeitura);
				return new String(leitor.readLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
