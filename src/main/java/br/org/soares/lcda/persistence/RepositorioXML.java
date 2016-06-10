package br.org.soares.lcda.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import com.thoughtworks.xstream.XStream;
import br.org.soares.lcda.model.Catalogo;
import br.org.soares.lcda.model.Senha;
import br.org.soares.lcda.model.Servico;
import br.org.soares.lcda.model.Usuario;
import br.org.soares.lcda.security.ChaveDeSegmento;

public class RepositorioXML {

	private static final String LOCAL = "repository/catalogo.xml";

	private static RepositorioXML instance;

	private File arquivo;
	private XStream fluxoDeDados;
	private ChaveDeSegmento chaveDeSegmento;

	private RepositorioXML() {
		arquivo = new File(LOCAL);
		chaveDeSegmento = new ChaveDeSegmento();
		fluxoDeDados = new XStream();
		fluxoDeDados.alias("catalogo", Catalogo.class);
		fluxoDeDados.alias("servico", Servico.class);
		fluxoDeDados.alias("usuario", Usuario.class);
		fluxoDeDados.alias("senha", Senha.class);
		fluxoDeDados.autodetectAnnotations(true);
		fluxoDeDados.registerConverter(new ConversorDeSenha(chaveDeSegmento.obter()));
		fluxoDeDados.registerConverter(new ConversorDeAnotacao());
	}

	public static RepositorioXML getInstance() {
		if (instance == null) {
			instance = new RepositorioXML();
		}
		return instance;
	}
	
	public void gravar(Object objeto) {		
		PrintWriter escritor;
		try {
			escritor = new PrintWriter(arquivo);
			escritor.write(fluxoDeDados.toXML(objeto));
			escritor.flush();
			escritor.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean existe() {
		return arquivo.exists() ? true : false;
	}

	public Object acessar() {
		return existe() ? fluxoDeDados.fromXML(arquivo) : null;
	}

}
