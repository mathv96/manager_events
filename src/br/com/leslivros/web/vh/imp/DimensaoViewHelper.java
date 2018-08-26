package br.com.leslivros.web.vh.imp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.leslivros.aplicacao.Resultado;
import br.com.leslivros.modelo.Dimensao;
import br.com.leslivros.modelo.EntidadeDominio;
import br.com.leslivros.web.vh.IViewHelper;

public class DimensaoViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String sAltura = request.getParameter("txtAltura");
		String sLargura = request.getParameter("txtLargura");
		String sPeso = request.getParameter("txtPso");
		String sProfundidade = request.getParameter("txtProfundidade");
		String PK = request.getParameter("txtPKDimensao");
		
		Dimensao dimensao = new Dimensao();
		dimensao.setAltura(Double.parseDouble(sAltura));
		dimensao.setLargura(Double.parseDouble(sLargura));
		dimensao.setPeso(Double.parseDouble(sPeso));
		dimensao.setProfundidade(Double.parseDouble(sProfundidade));
		
		if(PK != null && !PK.trim().equals("")){
			dimensao.setPK(Long.parseLong(PK));
		}
		
		return dimensao;
	}
	
	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
	}
}