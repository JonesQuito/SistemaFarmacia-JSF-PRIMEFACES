package br.com.farmacia.domain;

public class Produto {
	
	private Long codigo;
	private String descricao;
	private Long quantidade;
	private Double preco;
	private Fornecedor fornecedores = new Fornecedor();
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public Fornecedor getFornecedores() {
		return fornecedores;
	}
	public void setFornecedores(Fornecedor fornecedores) {
		this.fornecedores = fornecedores;
	}
	

}
