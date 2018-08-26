package br.com.leslivros.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.leslivros.modelo.Autor;
import br.com.leslivros.modelo.Categoria;
import br.com.leslivros.modelo.Dimensao;
import br.com.leslivros.modelo.Editora;
import br.com.leslivros.modelo.EntidadeDominio;
import br.com.leslivros.modelo.Livro;
import br.com.leslivros.modelo.Precificacao;

public class LivroDAO extends AbstractJdbcDAO {
	
	public LivroDAO() {
		super("livro", "PK");
	}
	
	public List<EntidadeDominio> getLista() throws SQLException {
		openConnection();
		List<EntidadeDominio> livros = new	ArrayList<EntidadeDominio>();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM livro");
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			Autor autor = new Autor();
			autor.setPK(rs.getLong("iAutor"));
			autor = (Autor) new AutorDAO().getEntidadeDominio(autor);
			
			Categoria categoria = new Categoria();
			categoria.setPK(rs.getLong("iCategoria"));
			categoria = (Categoria) new CategoriaDAO().getEntidadeDominio(categoria);
			
			Dimensao dimensao = new Dimensao();
			dimensao.setPK(rs.getLong("iDimensao"));
			dimensao = (Dimensao) new DimensaoDAO().getEntidadeDominio(dimensao);
			
			Editora editora = new Editora();
			editora.setPK(rs.getLong("iEditora"));
			editora = (Editora) new EditoraDAO().getEntidadeDominio(editora);
			
			Precificacao precificacao = new Precificacao();
			precificacao.setPK(rs.getLong("iPrecificacao"));
			precificacao = (Precificacao) new PrecificacaoDAO().getEntidadeDominio(precificacao);
			
			Livro livro	= new Livro();
			livro.setPK(rs.getLong("PK"));
			livro.setAutor(autor);
			livro.setCategoria(categoria);
			livro.setDimensao(dimensao);
			livro.setEditora(editora);
			livro.setPrecificacao(precificacao);
			livro.setDtAno(rs.getDate("sAno"));
			livro.setTitulo(rs.getString("sTitulo"));
			livro.setEdicao(rs.getString("sEdicao"));
			livro.setISBN(rs.getString("sISBN"));
			livro.setNumPaginas(rs.getInt("nNumPaginas"));
			livro.setSinopse(rs.getString("sSinopse"));
			
			livros.add(livro);
		}
		rs.close();
		stmt.close();
		
		return livros;
	}
	
	public EntidadeDominio getEntidadeDominio(EntidadeDominio entidadeDominio) throws SQLException {
		openConnection();
		Livro livro = (Livro) entidadeDominio;
		
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM livro WHERE PK = ?");
		stmt.setLong(1, livro.getPK());
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			Autor autor = new Autor();
			autor.setPK(rs.getLong("iAutor"));
			autor = (Autor) new AutorDAO().getEntidadeDominio(autor);
			
			Categoria categoria = new Categoria();
			categoria.setPK(rs.getLong("iCategoria"));
			categoria = (Categoria) new CategoriaDAO().getEntidadeDominio(categoria);
			
			Dimensao dimensao = new Dimensao();
			dimensao.setPK(rs.getLong("iDimensao"));
			dimensao = (Dimensao) new DimensaoDAO().getEntidadeDominio(dimensao);
			
			Editora editora = new Editora();
			editora.setPK(rs.getLong("iEditora"));
			editora = (Editora) new EditoraDAO().getEntidadeDominio(editora);
			
			Precificacao precificacao = new Precificacao();
			precificacao.setPK(rs.getLong("iPrecificacao"));
			precificacao = (Precificacao) new PrecificacaoDAO().getEntidadeDominio(precificacao);
			
			livro.setPK(rs.getLong("PK"));
			livro.setAutor(autor);
			livro.setCategoria(categoria);
			livro.setDimensao(dimensao);
			livro.setEditora(editora);
			livro.setPrecificacao(precificacao);
			livro.setDtAno(rs.getDate("sAno"));
			livro.setTitulo(rs.getString("sTitulo"));
			livro.setEdicao(rs.getString("sEdicao"));
			livro.setISBN(rs.getString("sISBN"));
			livro.setNumPaginas(rs.getInt("nNumPaginas"));
			livro.setSinopse(rs.getString("sSinopse"));
		}
		rs.close();
		stmt.close();
		
		return livro;
	}
	
	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		openConnection();
		Livro livro = (Livro) entidade;		
		String sql = "INSERT INTO livro (iAutor, iCategoria, sAno, sTitulo, iEditora, sEdicao, sISBN, nNumPaginas, sSinopse, iDimensao, iPrecificacao, sCodBarras)" +
				" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1, livro.getAutor().getPK());
		stmt.setLong(2, livro.getCategoria().getPK());
		stmt.setDate(3, new java.sql.Date(livro.getDtAno().getTime()));
		stmt.setString(4, livro.getTitulo());
		stmt.setLong(5, livro.getEditora().getPK());
		stmt.setString(6, livro.getEdicao());
		stmt.setString(7, livro.getISBN());
		stmt.setInt(8, livro.getNumPaginas());
		stmt.setString(9, livro.getSinopse());
		stmt.setLong(10, livro.getDimensao().getPK());
		stmt.setLong(11, livro.getPrecificacao().getPK());
		stmt.setString(12, livro.getCodBarras());
		
		stmt.execute();
		stmt.close();		
	}
	
	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		openConnection();
		Livro livro = (Livro) entidade;
		String sql = "UPDATE livro SET iAutor = ?, iCategoria = ?, sAno = ?, sTitulo = ?, iEditora = ?, sEdicao = ?, sISBN = ?, nNumPaginas = ?, sSinopse = ?, iDimensao = ?, iPrecificacao = ?, sCodBarras = ? WHERE PK = ?";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1, livro.getAutor().getPK());
		stmt.setLong(2, livro.getCategoria().getPK());
		stmt.setDate(3, new java.sql.Date(livro.getDtAno().getTime()));
		stmt.setString(4, livro.getTitulo());
		stmt.setLong(5, livro.getEditora().getPK());
		stmt.setString(6, livro.getEdicao());
		stmt.setString(7, livro.getISBN());
		stmt.setInt(8, livro.getNumPaginas());
		stmt.setString(9, livro.getSinopse());
		stmt.setLong(10, livro.getDimensao().getPK());
		stmt.setLong(11, livro.getPrecificacao().getPK());
		stmt.setString(12, livro.getCodBarras());
		stmt.setLong(13, livro.getPK());
		
		stmt.execute();
		stmt.close();
	}
	
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		return null;
	}
}