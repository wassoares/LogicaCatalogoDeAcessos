package br.org.soares.lcda.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.org.soares.lcda.security.ChaveDeSegmento;
import br.org.soares.lcda.security.ConverteSenha;

public class RepositorioXML {

	private File arquivo;
	private XStream fluxoDeDados;
	private ChaveDeSegmento chaveDeSegmento;

	public RepositorioXML(String local) {
		chaveDeSegmento = new ChaveDeSegmento();
		arquivo = new File(local);
		fluxoDeDados = new XStream(new DomDriver());
		fluxoDeDados.autodetectAnnotations(true);
		fluxoDeDados.registerConverter(new ConverteSenha(chaveDeSegmento.obter()));
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
