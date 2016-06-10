package br.org.soares.lcda.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("servico")
public class Servico {

	@XStreamAlias("provedor")
	private String provedor;
	
	@XStreamAlias("descricao")
	private Anotacao descricao;
	
	@XStreamAlias("usuario")
	private Usuario usuario;
	
	public String getProvedor() {
		return provedor;
	}

	public void setProvedor(String provedor) {
		this.provedor = provedor;
	}

	public Anotacao getDescricao() {
		return descricao;
	}

	public void setDescricao(Anotacao descricao) {
		this.descricao = descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Servico [provedor=" + provedor + ", descricao=" + descricao + ", usuario=" + usuario + "]";
	}
	
}
