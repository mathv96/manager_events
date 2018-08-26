package br.com.leslivros.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.leslivros.factory.ConnectionFactory;
import br.com.leslivros.inter.IDAO;
import br.com.leslivros.modelo.EntidadeDominio;

public abstract class AbstractJdbcDAO implements IDAO {
	protected Connection connection;
	protected String table;
	protected String PKTable;
	protected boolean ctrlTransaction = true;
	
	public AbstractJdbcDAO(Connection connection, String table, String PKTable){
		this.table = table;
		this.PKTable = PKTable;
		this.connection = connection;
	}
	
	protected AbstractJdbcDAO(String table, String PKTable){
		this.table = table;
		this.PKTable = PKTable;
	}
	
	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		openConnection();
		PreparedStatement pst = null;		
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM ");
		sb.append(table);
		sb.append(" WHERE ");
		sb.append(PKTable);
		sb.append(" = ");
		sb.append(" ? ");	
		try {
			connection.setAutoCommit(false);
			pst = connection.prepareStatement(sb.toString());
			pst.setLong(1, entidade.getPK());
			
			pst.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			
		}finally{
			
			try {
				pst.close();
				if(ctrlTransaction)
					connection.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void openConnection(){
		try {
			if(connection == null || connection.isClosed())
				connection = new ConnectionFactory().getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}