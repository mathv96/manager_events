package br.com.leslivros.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.leslivros.modelo.EntidadeDominio;
import br.com.leslivros.modelo.Precificacao;

public class PrecificacaoDAO extends AbstractJdbcDAO {
	
	public PrecificacaoDAO() {
		super("precificacao", "PK");
	}
	
	public List<EntidadeDominio> getLista() throws SQLException {
		openConnection();
		List<EntidadeDominio> precificacoes = new	ArrayList<EntidadeDominio>();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM precificacao");
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			Precificacao precificacao	= new Precificacao();
			precificacao.setPK(rs.getLong("PK"));
			precificacao.setDescricao(rs.getString("sDescricao"));
			precificacao.setPercentual(rs.getDouble("nPercentual"));
			
			precificacoes.add(precificacao);
		}
		rs.close();
		stmt.close();
		
		return precificacoes;
	}
	
	public EntidadeDominio getEntidadeDominio(EntidadeDominio entidadeDominio) throws SQLException {
		openConnection();
		Precificacao precificacao = (Precificacao) entidadeDominio;
		
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM precificacao WHERE PK = ?");
		stmt.setLong(1, precificacao.getPK());
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			precificacao.setPK(rs.getLong("PK"));
			precificacao.setDescricao(rs.getString("sDescricao"));
			precificacao.setPercentual(rs.getDouble("nPercentual"));
		}
		rs.close();
		stmt.close();
		
		return precificacao;
	}
	
	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		return null;
	}
}