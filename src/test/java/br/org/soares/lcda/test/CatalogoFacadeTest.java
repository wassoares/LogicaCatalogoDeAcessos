package br.org.soares.lcda.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.org.soares.lcda.CatalogoFacade;
import br.org.soares.lcda.Factory;
import br.org.soares.lcda.exceptions.ViolacaoDeCampoUnicoException;
import br.org.soares.lcda.model.Catalogo;
import br.org.soares.lcda.model.Servico;
import br.org.soares.lcda.model.Usuario;

public class CatalogoFacadeTest {

	private Usuario usuario;
	private Servico servico;
	private Catalogo catalogo;
	private CatalogoFacade fachada;	

	@Before
	public void inicializar() {
		catalogo = Factory.createCatalogo();
		fachada = new CatalogoFacade(catalogo);
	}
	
	@Test
	public void cadastrarUsuarioTest() {
		usuario = fachada.cadastrarUsuario("Fulano", "1234");
		assertEquals("Fulano", usuario.getLogin());
		assertEquals("1234", usuario.getSenha().getPalavra());
	}
	
	@Test
	public void configurarEValidarUsuarioTest() {
		cadastrarUsuarioTest();
		fachada.configurarUsuario(usuario);
		assertTrue(fachada.usuarioValido("Fulano", "1234"));
	}
	
	@Test
	public void cadastrarServicoTest() {
		servico = fachada.cadastrarServico("Gmail", "login de acesso ao gmail.com", "fulano@gmail.com", "12345678");
		assertEquals("Gmail", servico.getProvedor());
		assertEquals("login de acesso ao gmail.com", servico.getDescricao().getTexto());
		assertEquals("fulano@gmail.com", servico.getUsuario().getLogin());
		assertEquals("12345678", servico.getUsuario().getSenha().getPalavra());
	}
	
	@Test
	public void adicionarServicoTest() {
		try {
			cadastrarServicoTest();
			fachada.adicionarServico(servico);
			assertNotNull(fachada.listarServicos().get(0));
		} catch (ViolacaoDeCampoUnicoException e) {
			fail(e.getMessage());
		}
	}
	
	@Test(expected = ViolacaoDeCampoUnicoException.class)
	public void lancarViolacaoDeCampoUnicoExceptionTest() throws ViolacaoDeCampoUnicoException {
		adicionarServicoTest();
		fachada.adicionarServico(servico);		
	}
	
	@Test
	public void alterarServicoTest() {
		adicionarServicoTest();
		fachada.alterarServico("Gmail", "alteração do login de acesso ao gmail.com", "fulanodetall@gmail.com", "87654321");
		servico = fachada.buscarServico("Gmail");
		assertEquals("Gmail", servico.getProvedor());
		assertEquals("alteração do login de acesso ao gmail.com", servico.getDescricao().getTexto());
		assertEquals("fulanodetall@gmail.com", servico.getUsuario().getLogin());
		assertEquals("87654321", servico.getUsuario().getSenha().getPalavra());
	}
	
	@Test
	public void removerServicoTest() {
		adicionarServicoTest();
		fachada.removerServico(servico);
		assertEquals(0, fachada.listarServicos().size());	
	}
	
	@Test
	public void gravarEAcessarCatalogoTest() {
		configurarEValidarUsuarioTest();
		adicionarServicoTest();
		fachada.gravarCatalogo();
		assertEquals("Fulano", fachada.acessarCatalogo().getUsuario().getLogin());
		assertEquals("1234", fachada.acessarCatalogo().getUsuario().getSenha().getPalavra());
		assertEquals("Gmail", fachada.acessarCatalogo().getServicos().get(0).getProvedor());
		assertEquals("login de acesso ao gmail.com",
				fachada.acessarCatalogo().getServicos().get(0).getDescricao().getTexto());
		assertEquals("fulano@gmail.com", fachada.acessarCatalogo().getServicos().get(0).getUsuario().getLogin());
		assertEquals("12345678", fachada.acessarCatalogo().getServicos().get(0).getUsuario().getSenha().getPalavra());
	}

	@After
	public void finalizar() {
		fachada = null;
	}
}
