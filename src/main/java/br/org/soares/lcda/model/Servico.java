package br.org.soares.lcda.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("servico")
public class Servico {

	@XStreamAlias("provedor")
	private String provedor;
	
	@XStreamAlias("usuario")
	private Usuario usuario;
	
	@XStreamAlias("observacao")
	private String observacao;

	public String getProvedor() {
		return provedor;
	}

	public void setProvedor(String provedor) {
		this.provedor = provedor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public String toString() {
		return "Servico [provedor=" + provedor + ", usuario=" + usuario + ", observacao=" + observacao + "]";
	}

}
