package br.com.leslivros.web.vh.imp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.leslivros.aplicacao.Resultado;
import br.com.leslivros.modelo.Editora;
import br.com.leslivros.modelo.EntidadeDominio;
import br.com.leslivros.web.vh.IViewHelper;

public class EditoraViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String sRazao = request.getParameter("txtRazao");
		String PK = request.getParameter("txtPKEditora");
		
		Editora editora = new Editora();
		editora.setRazao(sRazao);
		
		if(PK != null && !PK.trim().equals("")){
			editora.setPK(Long.parseLong(PK));
		}
		
		return editora;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
	}
}