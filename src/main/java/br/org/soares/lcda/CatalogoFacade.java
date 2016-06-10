package br.org.soares.lcda;

import java.util.List;

import br.org.soares.lcda.exceptions.ViolacaoDeCampoUnicoException;
import br.org.soares.lcda.model.Catalogo;
import br.org.soares.lcda.model.Servico;
import br.org.soares.lcda.model.Usuario;
import br.org.soares.lcda.persistence.RepositorioXML;

public class CatalogoFacade implements Facade{

	private Catalogo catalogo;

	public CatalogoFacade(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	@Override
	public Usuario cadastrarUsuario(String login, String senha) {
		Usuario usuario = Factory.createUsuario();
		usuario.setLogin(login);
		usuario.setSenha(Factory.createSenha(senha));
		return usuario;
	}

	@Override
	public void configurarUsuario(Usuario usuario) {
		catalogo.setUsuario(usuario);		
	}

	@Override
	public boolean usuarioValido(String login, String senha) {
		return (catalogo.getUsuario() != null && 
				catalogo.getUsuario().getLogin().equals(login) && 
				catalogo.getUsuario().getSenha().getPalavra().equals(senha)) ? 
						true : false;
	}

	@Override
	public Servico cadastrarServico(String provedor, String descricao, String login, String senha) {
		Servico servico = Factory.createServico();
		servico.setProvedor(provedor);
		servico.setDescricao(Factory.createAnotacao(descricao));
		servico.setUsuario(cadastrarUsuario(login, senha));
		return servico;
	}

	@Override
	public void adicionarServico(Servico servico) throws ViolacaoDeCampoUnicoException {
		if (buscarServico(servico.getProvedor()) == null) {
			catalogo.getServicos().add(servico);
		} else {
			throw new ViolacaoDeCampoUnicoException();
		}
	}

	@Override
	public List<Servico> listarServicos() {
		return catalogo.getServicos();
	}
	
	@Override
	public Servico buscarServico(String provedor) {
		List<Servico> registros = listarServicos();
		if (listarServicos() != null) {
			for (Servico item : registros) {
				if (item.getProvedor().equals(provedor)) {
					return item;
				}
			}
		}
		return null;
	}

	@Override
	public void alterarServico(String provedor, String descricao, String login, String senha) {
		Servico registro = buscarServico(provedor);
		if (registro != null) {
			catalogo.getServicos().remove(registro);
			catalogo.getServicos().add(cadastrarServico(provedor, descricao, login, senha));
		}		
	}

	@Override
	public void removerServico(Servico servico) {
		if (buscarServico(servico.getProvedor()) != null) {
			catalogo.getServicos().remove(servico);
		}		
	}

	@Override
	public void gravarCatalogo() {
		RepositorioXML.getInstance().gravar(catalogo);
		
	}

	@Override
	public Catalogo acessarCatalogo() {
		return (Catalogo) RepositorioXML.getInstance().acessar();
	}	
	
}
