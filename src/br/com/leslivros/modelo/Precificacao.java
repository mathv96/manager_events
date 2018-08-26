package br.com.leslivros.modelo;

public class Precificacao extends EntidadeDominio {
	private String descricao;
	private double percentual;
	
	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	
	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	/**
	 * @return the percentual
	 */
	public double getPercentual() {
		return percentual;
	}
	
	/**
	 * @param percentual the percentual to set
	 */
	public void setPercentual(double percentual) {
		this.percentual = percentual;
	}
}