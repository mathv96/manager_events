package br.com.leslivros.modelo;

public class Dimensao extends EntidadeDominio {
	private double altura;
	private double largura;
	private double peso;
	private double profundidade;
	
	/**
	 * @return the altura
	 */
	public double getAltura() {
		return altura;
	}
	
	/**
	 * @param altura the altura to set
	 */
	public void setAltura(double altura) {
		this.altura = altura;
	}
	
	/**
	 * @return the largura
	 */
	public double getLargura() {
		return largura;
	}
	
	/**
	 * @param largura the largura to set
	 */
	public void setLargura(double largura) {
		this.largura = largura;
	}
	
	/**
	 * @return the peso
	 */
	public double getPeso() {
		return peso;
	}
	
	/**
	 * @param peso the peso to set
	 */
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	/**
	 * @return the profundidade
	 */
	public double getProfundidade() {
		return profundidade;
	}
	
	/**
	 * @param profundidade the profundidade to set
	 */
	public void setProfundidade(double profundidade) {
		this.profundidade = profundidade;
	}
}