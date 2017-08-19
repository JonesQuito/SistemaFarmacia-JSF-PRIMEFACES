package br.com.farmacia.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import br.com.farmacia.DAO.ProdutoDAO;
import br.com.farmacia.domain.Fornecedor;
import br.com.farmacia.domain.Produto;


public class ProdutoDAOTeste {
	
	@Test
	@Ignore
	public void salvar(){
		Produto p1 = new Produto();
		p1.setDescricao("DIPIRONA");
		p1.setPreco(2.99);
		p1.setQuantidade(12L);
		
		Fornecedor f = new Fornecedor();
		f.setCodigo(12L);
		p1.setFornecedores(f);
		ProdutoDAO dao = new ProdutoDAO();

		try {
			dao.salvar(p1);

			System.out.println("Salvo com sucesso!!");
		} catch (SQLException e) {
			System.out.println("Erro ao Salvar!!");
			e.printStackTrace();
		}
	}
	
	@Test
	@Ignore
	public void listar() throws SQLException{
		ProdutoDAO dao = new ProdutoDAO();
		ArrayList<Produto> lista = dao.listar();
		
		for(Produto p : lista){
			System.out.println(p.getDescricao());
		}
		
	}
	
	@Test
	@Ignore
	public void excluir() throws SQLException{
		Produto p = new Produto();
		p.setCodigo(5L);
		
		ProdutoDAO dao = new ProdutoDAO();
		dao.excluir(p);
	}
	
	@Test
	public void editar() throws SQLException{
		
		Fornecedor f = new Fornecedor();
		f.setCodigo(12L);
		
		Produto p = new Produto();
		p.setCodigo(1L);
		p.setDescricao("ASPIRINA");
		p.setPreco(6.50);
		p.setQuantidade(15L);
		p.setFornecedores(f);
		
		ProdutoDAO dao = new ProdutoDAO();
		dao.editar(p);
		
	}
}
