package br.org.soares.lcda.security;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class CriptografiaAES {

	private static final String PADRAO = "AES";
	private static final String ALGORITMO = "AES/CBC/PKCS5Padding";
	private static final String CODIFICACAO = "UTF-8";
	private static final String CRIPTOGRAFIA = "PBKDF2WithHmacSHA1";
	private static final String CONTRA_SENHA = "12345asdqwe";

	private Base64.Encoder codificador;
	private Base64.Decoder decodificador;
	private Cipher cifra;

	public CriptografiaAES() {
		codificador = Base64.getEncoder();
		decodificador = Base64.getDecoder();
		try {
			cifra = Cipher.getInstance(ALGORITMO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String encriptar(String palavra, String segmento) {
		try {
			SecretKeySpec segredo = gerarChaveDeSeguranca(segmento);
			byte[] vetoresDeInicializacao = this.gerarVetorDeInicializacao();
			cifra.init(Cipher.ENCRYPT_MODE, segredo, new IvParameterSpec(vetoresDeInicializacao));
			byte[] bytesCriptografados = cifra.doFinal(palavra.getBytes(CODIFICACAO));
			ByteArrayOutputStream fluxoDeSaida = new ByteArrayOutputStream();
			fluxoDeSaida.write(vetoresDeInicializacao);
			fluxoDeSaida.write(bytesCriptografados);
			return codificador.encodeToString(fluxoDeSaida.toByteArray());
		} catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException
				| BadPaddingException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String decriptar(String palavraEncriptada, String segmento) {
		try {
			byte[] bytesDecodificados = decodificador.decode(palavraEncriptada);
			SecretKeySpec segredo = gerarChaveDeSeguranca(segmento);
			cifra.init(Cipher.DECRYPT_MODE, segredo, new IvParameterSpec(bytesDecodificados, 0, 16));
			return new String(cifra.doFinal(bytesDecodificados, 16, bytesDecodificados.length - 16));
		} catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException
				| BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	private SecretKeySpec gerarChaveDeSeguranca(String segmento) {
		try {
			SecretKeyFactory fabrica = SecretKeyFactory.getInstance(CRIPTOGRAFIA);
			PBEKeySpec chaveDeInspecao = new PBEKeySpec(CONTRA_SENHA.toCharArray(), segmento.getBytes(CODIFICACAO),
					65536, 128);
			SecretKey chaveSecreta = fabrica.generateSecret(chaveDeInspecao);
			SecretKeySpec segredo = new SecretKeySpec(chaveSecreta.getEncoded(), PADRAO);
			return segredo;
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return null;
	}

	private byte[] gerarVetorDeInicializacao() {
		byte bytes[] = new byte[16];
		SecureRandom garantia = new SecureRandom();
		garantia.nextBytes(bytes);
		return bytes;
	}

}
