package br.org.soares.lcda.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.org.soares.lcda.CatalogoFactory;
import br.org.soares.lcda.model.Catalogo;
import br.org.soares.lcda.model.Servico;
import br.org.soares.lcda.model.Usuario;
import br.org.soares.lcda.persistence.RepositorioXML;
import br.org.soares.lcda.security.ChaveDeSegmento;
import br.org.soares.lcda.security.CriptografiaAES;

public class CatalogoTest {

	private CriptografiaAES criptografia;
	private String segmento;
	private Usuario washington;
	private Usuario wassoaresHotmail;
	private Servico hotmail;
	private Catalogo catalogo;

	@Before
	public void inicializar() {
		criptografia = new CriptografiaAES();
		segmento = ChaveDeSegmento.gerar();
		washington = CatalogoFactory.criarUsuario();
		washington.setLogin("Washington");
		washington.setSenha(criptografia.encriptar("was38", segmento));
		wassoaresHotmail = CatalogoFactory.criarUsuario();
		wassoaresHotmail.setLogin("wassoares@hotmail.com");
		wassoaresHotmail.setSenha(criptografia.encriptar("was35*12", segmento));
		hotmail = CatalogoFactory.criarServico();
		hotmail.setProvedor("hotmail.com");
		hotmail.setUsuario(wassoaresHotmail);
		hotmail.setObservacao("login de acesso ao hotmail.com");
		catalogo = CatalogoFactory.criarCatalogo();
		catalogo.setUsuario(washington);
		catalogo.getServicos().add(hotmail);
	}

	@Test
	public void verificarCriptografiaDeSenhaEmObjetoTest() {
		String senhaDesencriptada = criptografia.decriptar(washington.getSenha(), segmento);
		assertTrue(senhaDesencriptada.equals("was38"));
	}

	@Test
	public void verificarCriptografiaDeSenhaEmArquivoXmlTest() {
		RepositorioXML repositorio = new RepositorioXML("repository/catalogo.xml");
		repositorio.gravar(catalogo);
		Catalogo consulta = (Catalogo) repositorio.acessar();
		String senha = criptografia.decriptar(consulta.getUsuario().getSenha(), segmento);
		assertTrue(senha.equals("was38"));
	}

	// @Test
	public void test() {
		fail("Not yet implemented");
	}

}
