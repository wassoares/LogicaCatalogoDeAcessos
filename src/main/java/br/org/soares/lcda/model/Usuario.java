package br.org.soares.lcda.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("usuario")
public class Usuario {

	@XStreamAlias("login")
	private String login;

	@XStreamAlias("senha")
	private Senha senha;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Senha getSenha() {
		return senha;
	}

	public void setSenha(Senha senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Usuario [login=" + login + ", senha=" + senha + "]";
	}

}
