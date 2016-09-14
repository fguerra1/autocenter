package modelo;

import java.util.Set;

public class TipoDeServico {
	
	String descricao;
	double valor;
	Set<OrdemDeServico> ordemDeServicos;
	
	public TipoDeServico(String descricao, double valor, OrdemDeServico os) {
		super();
		this.descricao = descricao;
		this.valor = valor;
		this.ordemDeServicos = ordemDeServicos;
	}
	
	public TipoDeServico() {
		
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public void setOrdemDeServicos(OrdemDeServico os) {
		this.ordemDeServicos = ordemDeServicos;
	}

	public TipoDeServico(String descricao, double valor,
			Set<OrdemDeServico> ordemDeServicos) {
		super();
		this.descricao = descricao;
		this.valor = valor;
		this.ordemDeServicos = ordemDeServicos;
	}

	public Set<OrdemDeServico> getOrdemDeServicos() {
		return ordemDeServicos;
	}

	public void setOrdemDeServicos(Set<OrdemDeServico> ordemDeServicos) {
		this.ordemDeServicos = ordemDeServicos;
	}

	@Override
	public String toString() {
		return "TipoDeServico [descricao=" + descricao + ", valor=" + valor
				+ ", os=" + ordemDeServicos + "]";
	}
	
	
	
	

}
