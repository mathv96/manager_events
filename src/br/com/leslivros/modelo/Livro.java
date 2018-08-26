package br.com.leslivros.modelo;

import java.util.Date;

public class Livro extends EntidadeDominio {
	private Autor autor;
	private Categoria categoria;
	private Date dtAno;
	private String titulo;
	private Editora editora;
	private String edicao;
	private String ISBN;
	private int numPaginas;
	private String sinopse;
	private Dimensao dimensao;
	private Precificacao precificacao;
	private String codBarras;
	
	/**
	 * @return the autor
	 */
	public Autor getAutor() {
		return autor;
	}
	
	/**
	 * @param autor the autor to set
	 */
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	/**
	 * @return the categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}
	
	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	/**
	 * @return the dtAno
	 */
	public Date getDtAno() {
		return dtAno;
	}
	
	/**
	 * @param dtAno the dtAno to set
	 */
	public void setDtAno(Date dtAno) {
		this.dtAno = dtAno;
	}
	
	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}
	
	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	/**
	 * @return the editora
	 */
	public Editora getEditora() {
		return editora;
	}
	
	/**
	 * @param editora the editora to set
	 */
	public void setEditora(Editora editora) {
		this.editora = editora;
	}
	
	/**
	 * @return the edicao
	 */
	public String getEdicao() {
		return edicao;
	}
	
	/**
	 * @param edicao the edicao to set
	 */
	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}
	
	/**
	 * @return the iSBN
	 */
	public String getISBN() {
		return ISBN;
	}
	
	/**
	 * @param iSBN the iSBN to set
	 */
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	
	/**
	 * @return the numPaginas
	 */
	public int getNumPaginas() {
		return numPaginas;
	}
	
	/**
	 * @param numPaginas the numPaginas to set
	 */
	public void setNumPaginas(int numPaginas) {
		this.numPaginas = numPaginas;
	}
	
	/**
	 * @return the sinopse
	 */
	public String getSinopse() {
		return sinopse;
	}
	
	/**
	 * @param sinopse the sinopse to set
	 */
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
	
	/**
	 * @return the dimensao
	 */
	public Dimensao getDimensao() {
		return dimensao;
	}
	
	/**
	 * @param dimensao the dimensao to set
	 */
	public void setDimensao(Dimensao dimensao) {
		this.dimensao = dimensao;
	}
	
	/**
	 * @return the precificacao
	 */
	public Precificacao getPrecificacao() {
		return precificacao;
	}
	
	/**
	 * @param precificacao the precificacao to set
	 */
	public void setPrecificacao(Precificacao precificacao) {
		this.precificacao = precificacao;
	}
	
	/**
	 * @return the codBarras
	 */
	public String getCodBarras() {
		return codBarras;
	}
	
	/**
	 * @param codBarras the codBarras to set
	 */
	public void setCodBarras(String codBarras) {
		this.codBarras = codBarras;
	}
}