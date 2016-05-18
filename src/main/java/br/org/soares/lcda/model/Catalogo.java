package br.org.soares.lcda.model;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("catalogo")
public class Catalogo {

	@XStreamAlias("usuario")
	private Usuario usuario;
	
	@XStreamAlias("servicos")
	private List<Servico> servicos;
	
	public Catalogo() {
		this.servicos = new ArrayList<Servico>();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	@Override
	public String toString() {
		return "Catalogo [usuario=" + usuario + ", servicos=" + servicos + "]";
	}

}
