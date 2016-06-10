package br.org.soares.lcda.exceptions;

public class ViolacaoDeCampoUnicoException extends Exception {

	private static final long serialVersionUID = 1L;

	public ViolacaoDeCampoUnicoException() {
		super("Provedor de serviço já existe! Renomeie para continuar.");
	}
}
