package br.org.soares.lcda.security;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import br.org.soares.lcda.model.Senha;

public class ConverteSenha implements Converter {

	private String segmento;
	private CriptografiaAES criptografia;
	
	public ConverteSenha(String segmento) {
		this.segmento = segmento;
		this.criptografia = new CriptografiaAES();
	}
	
	@Override
	public boolean canConvert(@SuppressWarnings("rawtypes") Class classe) {
		return classe.equals(Senha.class);
	}

	@Override
	public void marshal(Object objeto, HierarchicalStreamWriter escritor, MarshallingContext contexto) {
		Senha senha = (Senha) objeto;
		String conteudo = criptografia.encriptar(senha.getPalavra(), segmento);
		senha.setPalavra(conteudo);
		escritor.startNode("criptograma");		
		escritor.setValue(conteudo);
		escritor.endNode();
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader leitor, UnmarshallingContext context) {
		Senha senha = new Senha();
		leitor.moveDown();
		senha.setPalavra(criptografia.decriptar(leitor.getValue(), segmento));
		leitor.moveUp();		
		return senha;
	}

}
