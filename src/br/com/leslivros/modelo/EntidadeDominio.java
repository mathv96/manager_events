package br.com.leslivros.modelo;

import java.util.Date;

public class EntidadeDominio implements IEntidade {
	private long PK;
	private Date dtCadastro;
	private String userCadastro;
	private Date dtAlteracao;
	private String userAlteracao;
	
	/**
	 * @return the pK
	 */
	public long getPK() {
		return PK;
	}
	
	/**
	 * @param pK the pK to set
	 */
	public void setPK(long pK) {
		PK = pK;
	}
	
	/**
	 * @return the dtCadastro
	 */
	public Date getDtCadastro() {
		return dtCadastro;
	}
	
	/**
	 * @param dtCadastro the dtCadastro to set
	 */
	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
	
	/**
	 * @return the userCadastro
	 */
	public String getUserCadastro() {
		return userCadastro;
	}
	
	/**
	 * @param userCadastro the userCadastro to set
	 */
	public void setUserCadastro(String userCadastro) {
		this.userCadastro = userCadastro;
	}
	
	/**
	 * @return the dtAlteracao
	 */
	public Date getDtAlteracao() {
		return dtAlteracao;
	}
	
	/**
	 * @param dtAlteracao the dtAlteracao to set
	 */
	public void setDtAlteracao(Date dtAlteracao) {
		this.dtAlteracao = dtAlteracao;
	}
	
	/**
	 * @return the userAlteracao
	 */
	public String getUserAlteracao() {
		return userAlteracao;
	}
	
	/**
	 * @param userAlteracao the userAlteracao to set
	 */
	public void setUserAlteracao(String userAlteracao) {
		this.userAlteracao = userAlteracao;
	}
}