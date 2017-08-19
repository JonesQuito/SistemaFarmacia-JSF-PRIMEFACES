package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.farmacia.domain.Fornecedor;
import br.com.farmacia.factory.ConnectionFactory;

public class FornecedorDAO {

	public void salvar(Fornecedor fornecedor) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fornecedores ");
		sql.append("(descricao) VALUES (?)");

		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql.toString());
		stmt.setString(1, fornecedor.getDescricao());

		stmt.executeUpdate();
	}

	public void excluir(Fornecedor fornecedor) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fornecedores ");
		sql.append("WHERE codigo = ? ");

		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql.toString());

		stmt.setLong(1, fornecedor.getCodigo());
		stmt.executeUpdate();
	}

	public void editar(Fornecedor fornecedor) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fornecedores ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ? ");

		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql.toString());

		stmt.setString(1, fornecedor.getDescricao());
		stmt.setLong(2, fornecedor.getCodigo());
		stmt.executeUpdate();
	}

	public Fornecedor buscaPorCodigo(Fornecedor fornecedor) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE codigo = ? ");

		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql.toString());
		stmt.setLong(1, fornecedor.getCodigo());

		ResultSet rs = stmt.executeQuery();
		Fornecedor retorno = null;

		if (rs.next()) {
			retorno = new Fornecedor();
			retorno.setCodigo(rs.getLong(1));
			retorno.setDescricao(rs.getString(2));
		}

		return retorno;
	}

	public List<Fornecedor> listar() throws SQLException {

		String sql = "SELECT codigo, descricao FROM fornecedores ORDER BY descricao ASC";

		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();
		List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();

		while (rs.next()) {
			Fornecedor fornecedor = new Fornecedor();
			fornecedor.setCodigo(rs.getLong("codigo"));
			fornecedor.setDescricao(rs.getString("descricao"));
			fornecedores.add(fornecedor);
		}
		return fornecedores;
	}

	public List<Fornecedor> buscarPorDescricao(Fornecedor f) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC ");

		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql.toString());
		stmt.setString(1, "%" + f.getDescricao() + "%");

		ResultSet rs = stmt.executeQuery();
		List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();

		while (rs.next()) {
			Fornecedor fornecedor = new Fornecedor();
			fornecedor.setCodigo(rs.getLong("codigo"));
			fornecedor.setDescricao(rs.getString("descricao"));
			fornecedores.add(fornecedor);
		}
		return fornecedores;
	}

	public static void main(String[] args) {
		/*
		Fornecedor f1 = new Fornecedor();
		f1.setDescricao("PALMOLIVE S/A");

		FornecedorDAO fDao = new FornecedorDAO();

		try {
			fDao.salvar(f1);

			System.out.println("Salvo com sucesso!!");
		} catch (SQLException e) {
			System.out.println("Erro ao Salvar!!");
			e.printStackTrace();
		} 
			 * Fornecedor f1 = new Fornecedor(); f1.setCodigo(2L);
			 * 
			 * FornecedorDAO fDao = new FornecedorDAO();
			 * 
			 * try { fDao.excluir(f1);
			 * System.out.println("Excluído com sucesso!!"); } catch
			 * (SQLException e) { System.out.println("Erro ao Excluir!!");
			 * e.printStackTrace(); } /* Fornecedor f1 = new Fornecedor();
			 * f1.setCodigo(1L); f1.setDescricao("Jone");
			 */
			 FornecedorDAO fDao = new FornecedorDAO();
			  
			 try { ArrayList<Fornecedor> fornecedores =
			 (ArrayList<Fornecedor>) fDao.listar();
			 for(Fornecedor f: fornecedores){ System.out.println(" - " +
			 f.getDescricao()); } } catch (SQLException e) {
			 System.out.println("Erro ao Buscar!!"); e.printStackTrace(); }
			 
	}

}
