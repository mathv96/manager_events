package br.com.leslivros.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.leslivros.modelo.Dimensao;
import br.com.leslivros.modelo.EntidadeDominio;

public class DimensaoDAO extends AbstractJdbcDAO {
	
	public DimensaoDAO() {
		super("dimensao", "PK");
	}
	
	public List<EntidadeDominio> getLista() throws SQLException {
		openConnection();
		List<EntidadeDominio> dimensoes = new	ArrayList<EntidadeDominio>();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM dimensao");
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			Dimensao dimensao	= new Dimensao();
			dimensao.setPK(rs.getLong("PK"));
			dimensao.setAltura(rs.getDouble("nAltura"));
			dimensao.setLargura(rs.getDouble("nLargura"));
			dimensao.setPeso(rs.getDouble("nPeso"));
			dimensao.setProfundidade(rs.getDouble("nProfundidade"));
			
			dimensoes.add(dimensao);
		}
		rs.close();
		stmt.close();
		
		return dimensoes;
	}
	
	public EntidadeDominio getEntidadeDominio(EntidadeDominio entidadeDominio) throws SQLException {
		openConnection();
		Dimensao dimensao = (Dimensao) entidadeDominio;
		
		PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM dimensao WHERE PK = ?");
		stmt.setLong(1, dimensao.getPK());
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			dimensao.setPK(rs.getLong("PK"));
			dimensao.setAltura(rs.getDouble("nAltura"));
			dimensao.setLargura(rs.getDouble("nLargura"));
			dimensao.setPeso(rs.getDouble("nPeso"));
			dimensao.setProfundidade(rs.getDouble("nProfundidade"));
		}
		rs.close();
		stmt.close();
		
		return dimensao;
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