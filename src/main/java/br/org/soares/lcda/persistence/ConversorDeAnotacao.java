package br.org.soares.lcda.persistence;

import java.io.UnsupportedEncodingException;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import br.org.soares.lcda.model.Anotacao;

public class ConversorDeAnotacao implements Converter {

	@Override
	public boolean canConvert(@SuppressWarnings("rawtypes") Class classe) {
		return classe.equals(Anotacao.class);
	}

	@Override
	public void marshal(Object objeto, HierarchicalStreamWriter escritor, MarshallingContext contexto) {
		try {
			Anotacao anotacao = (Anotacao) objeto;
			String conteudo = anotacao.getTexto();
			byte[] bytes = conteudo.getBytes("UTF-8");
			anotacao.setTexto(new String(bytes));
			escritor.startNode("texto");		
			escritor.setValue(anotacao.getTexto());
			escritor.endNode();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader leitor, UnmarshallingContext contexto) {
		Anotacao anotacao = new Anotacao();
		leitor.moveDown();
		anotacao.setTexto(leitor.getValue());
		leitor.moveUp();		
		return anotacao;
	}

}
