package br.com.leslivros.web.command.imp;

import br.com.leslivros.aplicacao.Resultado;
import br.com.leslivros.modelo.EntidadeDominio;

public class ConsultarCommand extends AbstractCommand {

	@Override
	public Resultado execute(EntidadeDominio entidade) {
		return fachada.consultar(entidade);
	}
}