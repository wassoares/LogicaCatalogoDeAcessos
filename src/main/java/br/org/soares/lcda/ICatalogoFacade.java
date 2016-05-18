package br.org.soares.lcda;

import java.util.List;

import br.org.soares.lcda.model.Catalogo;
import br.org.soares.lcda.model.Servico;

public interface ICatalogoFacade {

	public void configurar(String login, String Senha);

	public void adicionar(String provedor, String login, String Senha, String observacao);

	public Servico buscar(String provedor);

	public List<Servico> listar();

	public void remover(Servico servico);

	public void salvar(Catalogo catalogo);

	public Catalogo acessar();

}
