package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.farmacia.DAO.FornecedorDAO;
import br.com.farmacia.domain.Fornecedor;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "mgbFornecedor")
@ViewScoped
public class FornecedorBean {

	private Fornecedor fornecedor;
	private ArrayList<Fornecedor> itens;
	private ArrayList<Fornecedor> itensFiltrados;

	public ArrayList<Fornecedor> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Fornecedor> itens) {
		this.itens = itens;
	}
	
	public ArrayList<Fornecedor> getItensFiltrados() {
		return itensFiltrados;
	}
	
	public void setItensFiltrados(ArrayList<Fornecedor> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	@PostConstruct
	public void prepararPesquisa() {

		try {
			FornecedorDAO dao = new FornecedorDAO();
			itens = (ArrayList<Fornecedor>) dao.listar();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("Erro ao pesquisar: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void prepararNovo() {
		this.fornecedor = new Fornecedor();
	}

	public void novo() {
		try {
			FornecedorDAO dao = new FornecedorDAO();
			dao.salvar(fornecedor);
			itens = (ArrayList<Fornecedor>) dao.listar();
			JSFUtil.adicionarMensagemSucesso("Fornecedor salvo com sucesso!");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("Erro ao salvar: " + e.getMessage());
			e.printStackTrace();
		}
	}


	public void excluir() {
		try {
			FornecedorDAO dao = new FornecedorDAO();
			dao.excluir(fornecedor);
			itens = (ArrayList<Fornecedor>) dao.listar();
			JSFUtil.adicionarMensagemSucesso("Registro excluído com sucesso!!");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("Não foi possível excluir o fornecedor. "
										+ "Certifique-se de que não há um produto "
										+ "associado ao mesmo");
			e.printStackTrace();
		}
	}

	
	public void editar() {
		try {
			FornecedorDAO dao = new FornecedorDAO();
			dao.editar(fornecedor);
			itens = (ArrayList<Fornecedor>) dao.listar();
			JSFUtil.adicionarMensagemSucesso("Fornecedor alterado com sucesso!");
			
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("Não foi possível alterar o fornecedor");
			e.printStackTrace();
		}
	}

}
