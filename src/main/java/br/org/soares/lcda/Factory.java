package br.org.soares.lcda;

import br.org.soares.lcda.model.Anotacao;
import br.org.soares.lcda.model.Catalogo;
import br.org.soares.lcda.model.Senha;
import br.org.soares.lcda.model.Servico;
import br.org.soares.lcda.model.Usuario;

public class Factory {

	public static Senha createSenha(String arg) {
		return new Senha(arg);
	}
	
	public static Usuario createUsuario() {
		return new Usuario();
	}
	
	public static Anotacao createAnotacao(String arg) {
		return new Anotacao(arg);
	}
	
	public static Servico createServico() {
		return new Servico();
	}
	
	public static Catalogo createCatalogo() {
		return new Catalogo();
	}

}
