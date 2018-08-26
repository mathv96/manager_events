package br.com.leslivros.web.vh.imp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.leslivros.aplicacao.Resultado;
import br.com.leslivros.modelo.Categoria;
import br.com.leslivros.modelo.EntidadeDominio;
import br.com.leslivros.web.vh.IViewHelper;

public class CategoriaViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String sDescricao = request.getParameter("txtDescricao");
		String PK = request.getParameter("txtPKCategoria");
		
		Categoria categoria = new Categoria();
		categoria.setDescricao(sDescricao);
		
		if(PK != null && !PK.trim().equals("")){
			categoria.setPK(Long.parseLong(PK));
		}
		
		return categoria;
	}
	
	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
	}
}