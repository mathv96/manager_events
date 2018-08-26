package br.com.leslivros.negocio;

import java.util.Date;

import br.com.leslivros.inter.IStrategy;
import br.com.leslivros.modelo.EntidadeDominio;
import br.com.leslivros.modelo.Livro;

public class ValidadorDadosObrigatoriosLivro implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		if(entidade instanceof Livro){
			Livro livro = (Livro) entidade;
			
			long PK = livro.getPK();
			System.out.println(PK);
			long autor = livro.getAutor().getPK();
			long categoria = livro.getCategoria().getPK();
			Date ano = livro.getDtAno();
			String titulo = livro.getTitulo();
			long editora = livro.getEditora().getPK();
			String edicao = livro.getEdicao();
			String ISBN = livro.getISBN();
			long precificacao = livro.getPrecificacao().getPK();
			String codBarras = livro.getCodBarras();
			
			if(autor <= 0 || categoria <= 0 || ano == null || titulo == null || editora <= 0 || edicao == null || 
					ISBN == null || precificacao <= 0 || codBarras == null){
				return "Autor, Categoria, Ano, Título, Editora, Edição, ISBN, Precificação e Cod. Barras são de preenchimento obrigatório!";
			}
			
			if(titulo.trim().equals("") || edicao.trim().equals("") || 
					ISBN.trim().equals("")|| codBarras.trim().equals("")){
				return "Autor, Categoria, Ano, Título, Editora, Edição, ISBN, Precificação e Cod. Barras são de preenchimento obrigatório!";
			}
		} else {
			return "Deve ser registrado um Livro!";
		}
		
		return null;
	}
}