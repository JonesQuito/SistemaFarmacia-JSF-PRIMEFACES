package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.farmacia.DAO.FornecedorDAO;
import br.com.farmacia.DAO.ProdutoDAO;
import br.com.farmacia.domain.Fornecedor;
import br.com.farmacia.domain.Produto;
import br.com.farmacia.util.JSFUtil;

@ManagedBean
@ViewScoped
public class ProdutoBean {
	private Produto produto;
	private ArrayList<Produto> itens;
	private ArrayList<Produto> itensFiltrados;
	private ArrayList<Fornecedor> comboFornecedores;
	
	public ArrayList<Fornecedor> getComboFornecedores() {
		return comboFornecedores;
	}
	
	public void setComboFornecedores(ArrayList<Fornecedor> comboFornecedores) {
		this.comboFornecedores = comboFornecedores;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public ArrayList<Produto> getItens() {
		return itens;
	}
	
	public void setItens(ArrayList<Produto> itens) {
		this.itens = itens;
	}
	
	public ArrayList<Produto> getItensFiltrados() {
		return itensFiltrados;
	}
	
	public void setItensFiltrados(ArrayList<Produto> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}
	
	
	
	@PostConstruct
	public void prepararPesquisa(){
		try{
			ProdutoDAO dao = new ProdutoDAO();
			itens = dao.listar();
		}catch(SQLException e){
			JSFUtil.adicionarMensagemErro("Erro ao obter a lista de produtos. " + e.getMessage());
		}
	}
	
	public void prepararNovo(){
		try{
			this.produto = new Produto();
			FornecedorDAO dao = new FornecedorDAO();
			comboFornecedores = (ArrayList<Fornecedor>) dao.listar();
		}catch(SQLException e){
			JSFUtil.adicionarMensagemErro("Erro ao listar fornecedores!!");
			e.printStackTrace();
		}
	}
	
	public void novo() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			dao.salvar(produto);
			itens = (ArrayList<Produto>) dao.listar();
			JSFUtil.adicionarMensagemSucesso("Produto salvo com sucesso!");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("Erro ao salvar: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void excluir(){
		try{
			ProdutoDAO dao = new ProdutoDAO();
			dao.excluir(produto);
			
			itens = dao.listar();
			JSFUtil.adicionarMensagemSucesso("Produto excluído com sucesso!!");
		}catch(SQLException e){
			JSFUtil.adicionarMensagemErro("Erro ao excluir: " + e.getMessage());
		}
	}
	
	public void prepararEditar(){
		try{
			this.produto = new Produto();
			FornecedorDAO dao = new FornecedorDAO();
			comboFornecedores = (ArrayList<Fornecedor>) dao.listar();
		}catch(SQLException e){
			JSFUtil.adicionarMensagemErro("Não foi possível carregar a lista de fornecedores!!");
			e.printStackTrace();
		}
	}
	
	public void editar(){
		try{
			ProdutoDAO dao = new ProdutoDAO();
			dao.editar(produto);
			
			itens = dao.listar();
			JSFUtil.adicionarMensagemSucesso("Alteração gravada com sucesso!!");
		}catch(SQLException e){
			JSFUtil.adicionarMensagemErro("Erro ao editar o produto. " + e.getMessage());
		}
	}
	
	

}
