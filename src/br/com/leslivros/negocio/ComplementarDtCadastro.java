package br.com.leslivros.negocio;

import java.util.Date;

import br.com.leslivros.inter.IStrategy;
import br.com.leslivros.modelo.EntidadeDominio;

public class ComplementarDtCadastro implements IStrategy {

	@SuppressWarnings("null")
	@Override
	public String processar(EntidadeDominio entidade) {
		if(entidade != null){
			Date data = new Date();		
			entidade.setDtCadastro(data);
		} else {
			return "Entidade: " + entidade.getClass().getCanonicalName() + " nula!";
		}
		
		return null;
	}
}