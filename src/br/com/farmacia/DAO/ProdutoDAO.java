package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.farmacia.domain.Fornecedor;
import br.com.farmacia.domain.Produto;
import br.com.farmacia.factory.ConnectionFactory;

public class ProdutoDAO {

	public void salvar(Produto produto) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO produtos ");
		sql.append("(descricao, preco, quantidade, fornecedores_codigo) ");
		sql.append("VALUES (?, ?, ?, ?)");

		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql.toString());

		stmt.setString(1, produto.getDescricao());
		stmt.setDouble(2, produto.getPreco());
		stmt.setLong(3, produto.getQuantidade());
		stmt.setLong(4, produto.getFornecedores().getCodigo());

		stmt.executeUpdate();
	}

	public ArrayList<Produto> listar() throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.codigo, p.descricao, p.preco, p.quantidade, f.codigo, f.descricao ");
		sql.append("FROM produtos AS p ");
		sql.append("INNER JOIN fornecedores AS f ");
		sql.append("ON f.codigo = p.fornecedores_codigo ");
		sql.append("ORDER BY p.descricao ASC");

		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql.toString());

		ResultSet rs = stmt.executeQuery();
		ArrayList<Produto> produtos = new ArrayList<Produto>();

		while (rs.next()) {
			Fornecedor f = new Fornecedor();
			f.setCodigo(rs.getLong("f.codigo"));
			f.setDescricao(rs.getString("f.descricao"));
			
			Produto produto = new Produto();
			produto.setCodigo(rs.getLong("p.codigo"));
			produto.setDescricao(rs.getString("p.descricao"));
			produto.setPreco(rs.getDouble("p.preco"));
			produto.setQuantidade(rs.getLong("p.quantidade"));
			produto.setFornecedores(f);
			
			produtos.add(produto);
		}
		return produtos;
	}

	
	public void excluir(Produto produto) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM produtos ");
		sql.append("WHERE codigo = ? ");

		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql.toString());

		stmt.setLong(1, produto.getCodigo());
		stmt.executeUpdate();
	}
	
	public void editar(Produto p) throws SQLException {
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE produtos ");
		sql.append(" SET descricao = ?, preco = ?, quantidade = ?, fornecedores_codigo = ? ");
		sql.append("WHERE codigo = ?");
		
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql.toString());
		
		stmt.setString(1, p.getDescricao());
		stmt.setDouble(2, p.getPreco());
		stmt.setLong(3, p.getQuantidade());
		stmt.setLong(4, p.getFornecedores().getCodigo());
		stmt.setLong(5, p.getCodigo());
		
		stmt.executeUpdate();
	}
}


