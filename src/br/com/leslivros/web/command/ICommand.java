package br.com.leslivros.web.command;

import br.com.leslivros.aplicacao.Resultado;
import br.com.leslivros.modelo.EntidadeDominio;

public interface ICommand {
	
	public Resultado execute(EntidadeDominio entidade);
}