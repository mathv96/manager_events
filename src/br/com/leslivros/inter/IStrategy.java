package br.com.leslivros.inter;

import br.com.leslivros.modelo.EntidadeDominio;

public interface IStrategy {
	
	public String processar(EntidadeDominio entidade);
}