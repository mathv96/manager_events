package br.com.leslivros.web.vh.imp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.leslivros.aplicacao.Resultado;
import br.com.leslivros.modelo.EntidadeDominio;
import br.com.leslivros.modelo.Precificacao;
import br.com.leslivros.web.vh.IViewHelper;

public class PrecificacaoViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String sDescricao = request.getParameter("txtDescricao");
		String sPercentual = request.getParameter("txtPercentual");
		String PK = request.getParameter("txtPKPreficicacao");
		
		Precificacao precificacao = new Precificacao();
		precificacao.setDescricao(sDescricao);
		precificacao.setPercentual(Double.parseDouble(sPercentual));
		
		if(PK != null && !PK.trim().equals("")){
			precificacao.setPK(Long.parseLong(PK));
		}
		
		return precificacao;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
	}
}