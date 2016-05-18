package br.org.soares.lcda.security;

import java.security.SecureRandom;

public class ChaveDeSegmento {

	private static SecureRandom garantia = new SecureRandom();

	public static String gerar() {
		byte bytes[] = new byte[16];
		garantia.nextBytes(bytes);
		return new String(bytes);
	}

}
