package br.org.soares.lcda;

import java.util.List;

import br.org.soares.lcda.exceptions.ViolacaoDeCampoUnicoException;
import br.org.soares.lcda.model.Catalogo;
import br.org.soares.lcda.model.Servico;
import br.org.soares.lcda.model.Usuario;

public interface Facade {

	public Usuario cadastrarUsuario(String login, String senha);

	public void configurarUsuario(Usuario usuario);

	public boolean usuarioValido(String login, String senha);

	public Servico cadastrarServico(String provedor, String descricao, String login, String senha);

	public void adicionarServico(Servico servico) throws ViolacaoDeCampoUnicoException;

	public Servico buscarServico(String provedor);

	public List<Servico> listarServicos();

	public void alterarServico(String provedor, String descricao, String login, String senha);

	public void removerServico(Servico servico);

	public void gravarCatalogo();

	public Catalogo acessarCatalogo();

}
