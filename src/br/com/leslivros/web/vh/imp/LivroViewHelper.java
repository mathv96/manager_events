package br.com.leslivros.web.vh.imp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.leslivros.aplicacao.Resultado;
import br.com.leslivros.modelo.Autor;
import br.com.leslivros.modelo.Categoria;
import br.com.leslivros.modelo.Dimensao;
import br.com.leslivros.modelo.Editora;
import br.com.leslivros.modelo.EntidadeDominio;
import br.com.leslivros.modelo.Livro;
import br.com.leslivros.modelo.Precificacao;
import br.com.leslivros.util.ConverteDate;
import br.com.leslivros.web.vh.IViewHelper;

public class LivroViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		Livro livro = null; 
		
		if(!operacao.equals("VISUALIZAR")){
			String sAutor = request.getParameter("txtAutor");
			String sCategoria = request.getParameter("txtCategoria");
			String sAno = request.getParameter("txtAno");
			String sTitulo = request.getParameter("txtTitulo");
			String sEditora = request.getParameter("txtEditora");
			String sEdicao = request.getParameter("txtEdicao");
			String sISBN = request.getParameter("txtISBN");
			String sNumPaginas = request.getParameter("txtNumPaginas");
			String sSinopse = request.getParameter("txtSinopse");
			String sDimensao = request.getParameter("txtDimensao");
			String sPrecificacao = request.getParameter("txtPrecificacao");
			String sCodBarras = request.getParameter("txtCodBarras");
			String sDtCadastro = request.getParameter("txtDtCadastro");
			String sDtAtualizacao = request.getParameter("txtDtAtualizacao");
			
			livro = new Livro();
			
			if(sAutor != null && !sAutor.trim().equals("")){
				Autor autor = new Autor();
				autor.setPK(Long.parseLong(sAutor));
				
				livro.setAutor(autor);
			}
			
			if(sCategoria != null && !sCategoria.trim().equals("")){
				Categoria categoria = new Categoria();
				categoria.setPK(Long.parseLong(sCategoria));
				
				livro.setCategoria(categoria);
			}
			
			if(sAno != null && !sAno.trim().equals("")){				
				livro.setDtAno(ConverteDate.converteString2Date(sAno));
			}
			
			if(sTitulo != null && !sTitulo.trim().equals("")){
				livro.setTitulo(sTitulo);
			}
			
			if(sEditora != null && !sEditora.trim().equals("")){
				Editora editora = new Editora();
				editora.setPK(Long.parseLong(sEditora));
				
				livro.setEditora(editora);
			}
			
			if(sEdicao != null && !sEdicao.trim().equals("")){
				livro.setEdicao(sEdicao);
			}
			
			if(sISBN != null && !sISBN.trim().equals("")){
				livro.setISBN(sISBN);
			}
			
			if(sNumPaginas != null && !sNumPaginas.trim().equals("")){
				livro.setNumPaginas(Integer.parseInt(sNumPaginas));
			}
			
			if(sSinopse != null && !sSinopse.trim().equals("")){
				livro.setSinopse(sSinopse);
			}
			
			if(sDimensao != null && !sDimensao.trim().equals("")){
				Dimensao dimensao = new Dimensao();
				dimensao.setPK(Long.parseLong(sDimensao));
				
				livro.setDimensao(dimensao);
			}
			
			if(sPrecificacao != null && !sPrecificacao.trim().equals("")){
				Precificacao precificacao = new Precificacao();
				precificacao.setPK(Long.parseLong(sPrecificacao));
				
				livro.setPrecificacao(precificacao);
			}
			
			if(sCodBarras != null && !sCodBarras.trim().equals("")){
				livro.setCodBarras(sCodBarras);
			}
			
			if(sDtCadastro != null && !sDtCadastro.trim().equals("")){
				livro.setDtCadastro(ConverteDate.converteString2Date(sDtCadastro));
			}
			
			if(sDtAtualizacao != null && !sDtAtualizacao.trim().equals("")){
				livro.setDtAlteracao(ConverteDate.converteString2Date(sDtAtualizacao));
			}
		} else {
			HttpSession session = request.getSession();
			Resultado resultado = (Resultado) session.getAttribute("resultado");
			String sPK = request.getParameter("txtPKLivro");
			int PK = 0;
			
			if(sPK != null && !sPK.trim().equals("")){
				PK = Integer.parseInt(sPK);
			}
			
			for(EntidadeDominio e: resultado.getEntidades()){
				if(e.getPK() == PK){
					livro = (Livro) e;
				}
			}
		}
		
		return livro;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		RequestDispatcher d = null;
		
		String operacao = request.getParameter("operacao");
		
		if(resultado.getMsg() == null) {
			if(operacao.equals("SALVAR")) {
				resultado.setMsg("Livro cadastrado com sucesso!");
			}
			
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("ConsultaLivro.jsp");  			
		}
		
		if(resultado.getMsg() == null && operacao.equals("ALTERAR")) {
			d = request.getRequestDispatcher("ConsultaLivro.jsp");  
		}
		
		if(resultado.getMsg() == null && operacao.equals("VISUALIZAR")) {
			request.setAttribute("livro", resultado.getEntidades().get(0));
			d = request.getRequestDispatcher("ConsultaLivro.jsp");
		}
		
		if(resultado.getMsg() == null && operacao.equals("EXCLUIR")){
			request.getSession().setAttribute("resultado", null);
			d= request.getRequestDispatcher("ConsultaLivro.jsp");  
		}
		
		if(resultado.getMsg() != null){
			if(operacao.equals("SALVAR") || operacao.equals("ALTERAR")){
				request.getSession().setAttribute("resultado", resultado);
				d = request.getRequestDispatcher("ConsultaLivro.jsp");  	
			}
		}
		
		d.forward(request,response); 	
	}
}