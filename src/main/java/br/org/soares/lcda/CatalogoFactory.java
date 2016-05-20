package br.org.soares.lcda;

import br.org.soares.lcda.model.Catalogo;
import br.org.soares.lcda.model.Senha;
import br.org.soares.lcda.model.Servico;
import br.org.soares.lcda.model.Usuario;

public class CatalogoFactory {

	public static Senha criarSenha() {
		return new Senha();
	}
	
	public static Usuario criarUsuario() {
		return new Usuario();
	}
	
	public static Servico criarServico() {
		return new Servico();
	}
	
	public static Catalogo criarCatalogo() {
		return new Catalogo();
	}

}
