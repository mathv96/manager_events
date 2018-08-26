package br.com.leslivros.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.leslivros.modelo.Autor;
import br.com.leslivros.modelo.EntidadeDominio;

public class AutorDAO extends AbstractJdbcDAO {
	
	public AutorDAO () {
		super("autor", "PK");
	}
	
	public List<EntidadeDominio> getLista() throws SQLException {
		openConnection();
		List<EntidadeDominio> autores = new	ArrayList<EntidadeDominio>();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM autor");
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			Autor autor	= new Autor();
			autor.setPK(rs.getLong("PK"));
			autor.setNomeArtistico(rs.getString("sNomeArtistico"));
			autor.setNomeCompleto(rs.getString("sNomeCompleto"));
			
			autores.add(autor);
		}
		rs.close();
		stmt.close();
		
		return autores;
	}
	
	public EntidadeDominio getEntidadeDominio(EntidadeDominio entidadeDominio) throws SQLException {
		openConnection();
		Autor autor = (Autor) entidadeDominio;
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM autor WHERE PK = ?");
		stmt.setLong(1, autor.getPK());;
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			autor.setPK(rs.getLong("PK"));
			autor.setNomeArtistico(rs.getString("sNomeArtistico"));
			autor.setNomeCompleto(rs.getString("sNomeCompleto"));
		}
		rs.close();
		stmt.close();
		
		return autor;
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