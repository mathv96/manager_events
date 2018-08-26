package br.com.leslivros.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.leslivros.modelo.Editora;
import br.com.leslivros.modelo.EntidadeDominio;

public class EditoraDAO extends AbstractJdbcDAO {
	
	public EditoraDAO() {
		super("editora", "PK");
	}
	
	public List<EntidadeDominio> getLista() throws SQLException {
		openConnection();
		List<EntidadeDominio> editoras = new	ArrayList<EntidadeDominio>();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM editora");
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			Editora editora	= new Editora();
			editora.setPK(rs.getLong("PK"));
			editora.setRazao(rs.getString("sRazao"));
			
			editoras.add(editora);
		}
		rs.close();
		stmt.close();
		
		return editoras;
	}
	
	public EntidadeDominio getEntidadeDominio(EntidadeDominio entidadeDominio) throws SQLException {
		openConnection();
		Editora editora = (Editora) entidadeDominio;
		
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM editora WHERE PK = ?");
		stmt.setLong(1, editora.getPK());
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			editora.setPK(rs.getLong("PK"));
			editora.setRazao(rs.getString("sRazao"));
		}
		rs.close();
		stmt.close();
		
		return editora;
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