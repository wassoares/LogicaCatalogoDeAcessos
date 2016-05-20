package br.org.soares.lcda.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.org.soares.lcda.CatalogoFactory;
import br.org.soares.lcda.model.Catalogo;
import br.org.soares.lcda.model.Senha;
import br.org.soares.lcda.model.Servico;
import br.org.soares.lcda.model.Usuario;
import br.org.soares.lcda.persistence.RepositorioXML;

public class CatalogoTest {

	private Senha senhaWashington;
	private Senha senhaWassoaresHotmail;
	private Usuario washington;
	private Usuario wassoaresHotmail;
	private Servico hotmail;
	private Catalogo catalogo;

	@Before
	public void inicializar() {
		senhaWashington = CatalogoFactory.criarSenha();
		senhaWashington.setPalavra("was38");
		
		senhaWassoaresHotmail = CatalogoFactory.criarSenha();
		senhaWassoaresHotmail.setPalavra("was35*12");
		
		washington = CatalogoFactory.criarUsuario();
		washington.setLogin("Washington");
		washington.setSenha(senhaWashington);
		
		wassoaresHotmail = CatalogoFactory.criarUsuario();
		wassoaresHotmail.setLogin("wassoares@hotmail.com");
		wassoaresHotmail.setSenha(senhaWassoaresHotmail);
		
		hotmail = CatalogoFactory.criarServico();
		hotmail.setProvedor("hotmail.com");
		hotmail.setUsuario(wassoaresHotmail);
		hotmail.setObservacao("login de acesso ao hotmail.com");
		
		catalogo = CatalogoFactory.criarCatalogo();
		catalogo.setUsuario(washington);
		catalogo.getServicos().add(hotmail);
	}

	@Test
	public void verificarCriptografiaDeSenhaEmArquivoXmlTest() {
		RepositorioXML repositorio = new RepositorioXML("repository/catalogo.xml");
		repositorio.gravar(catalogo);
		Catalogo consulta = (Catalogo) repositorio.acessar();
		String senha = consulta.getUsuario().getSenha().getPalavra();
		assertTrue(senha.equals("was38"));
	}

	//@Test
	public void test() {
		fail("Not yet implemented");
	}

}