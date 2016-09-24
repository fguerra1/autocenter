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

	public void addOrdemServico(OrdemDeServico novaOS) {
		if (this.ordemDeServicos.contains(novaOS))
			return;
		this.ordemDeServicos.add(novaOS);
		novaOS.addTipoServico(this);
	}
	
	public void removeOrdemServico(OrdemDeServico oS){
		if (! this.ordemDeServicos.contains(oS))
			return;
		this.ordemDeServicos.remove(oS);
		oS.removeTipoServico(this);
		
	}

	@Override
	public String toString() {
		return "TipoDeServico [descricao=" + descricao + ", valor=" + valor
				+ ", os=" + ordemDeServicos + "]";
	}
	
	
	
	

}
