package br.com.leslivros.web.vh.imp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.leslivros.aplicacao.Resultado;
import br.com.leslivros.modelo.Autor;
import br.com.leslivros.modelo.EntidadeDominio;
import br.com.leslivros.web.vh.IViewHelper;

public class AutorViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String sNomeArtistico = request.getParameter("txtNomeArtistico");
		String sNomeCompleto = request.getParameter("txtNomeCompleto");
		String PK = request.getParameter("txtPKAutor");
		
		Autor autor = new Autor();
		autor.setNomeArtistico(sNomeArtistico);
		autor.setNomeCompleto(sNomeCompleto);
		
		if(PK != null && !PK.trim().equals("")){
			autor.setPK(Long.parseLong(PK));
		}
		
		return autor;
	}
	
	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
	}
}