package br.com.leslivros.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.leslivros.modelo.Categoria;
import br.com.leslivros.modelo.EntidadeDominio;

public class CategoriaDAO extends AbstractJdbcDAO {
	
	public CategoriaDAO() {
		super("categoria", "PK");
	}
	
	public List<EntidadeDominio> getLista() throws SQLException {
		openConnection();
		List<EntidadeDominio> categorias = new	ArrayList<EntidadeDominio>();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM categoria");
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			Categoria categoria	= new Categoria();
			categoria.setPK(rs.getLong("PK"));
			categoria.setDescricao(rs.getString("sDescricao"));
			
			categorias.add(categoria);
		}
		rs.close();
		stmt.close();
		
		return categorias;
	}
	
	public EntidadeDominio getEntidadeDominio(EntidadeDominio entidadeDominio) throws SQLException {
		openConnection();
		Categoria categoria = (Categoria) entidadeDominio;
		
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM categoria WHERE PK = ?");
		stmt.setLong(1, categoria.getPK());
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			categoria.setPK(rs.getLong("PK"));
			categoria.setDescricao(rs.getString("sDescricao"));
		}
		rs.close();
		stmt.close();
		
		return categoria;
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