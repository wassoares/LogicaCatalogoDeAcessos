package br.org.soares.lcda.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class RepositorioXML {

	private File arquivo;
	private XStream fluxoDeDados;

	public RepositorioXML(String local) {
		arquivo = new File(local);
		fluxoDeDados = new XStream(new DomDriver());
		fluxoDeDados.autodetectAnnotations(true);
	}

	public void gravar(Object objeto) {
		PrintWriter escritor = null;
		try {
			escritor = new PrintWriter(arquivo);
			escritor.write(fluxoDeDados.toXML(objeto));
			escritor.flush();
			escritor.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			escritor.close();
		}
	}

	public Object acessar() {
		return fluxoDeDados.fromXML(arquivo);
	}

}
