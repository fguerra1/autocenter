package modelo;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

public class OrdemDeServico {

	Date dataDeServico;
	Date dataFim;
	String descricao;
	Funcionario funcionario;
	Cliente cliente;
	Veiculo veiculo;
	Set<TipoDeServico> tipoServicos;

	public OrdemDeServico(Date dataDeServico, Date dataFim, String descricao) {
		super();
		this.setDataDeServico(dataDeServico);
		this.setDataFim(dataFim);
		this.setDescricao(descricao);
		this.tipoServicos = new TreeSet<TipoDeServico>();
	}


	public Set<TipoDeServico> getTipoServicos() {
		return tipoServicos;
	}


	public void addTipoServico(TipoDeServico novaOS) {
		if (this.tipoServicos.contains(novaOS))
			return;
		this.tipoServicos.add(novaOS);
		novaOS.addOrdemServico(this);
	}
	
	public void removeTipoServico (TipoDeServico oS){
		if (! this.tipoServicos.contains(oS))
			return;
		this.tipoServicos.remove(oS);
		oS.removeOrdemServico(this);
	}	

	public Date getDataDeServico() {
		return dataDeServico;
	}

	public void setDataDeServico(Date dataDeServico) {
		this.dataDeServico = dataDeServico;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

	public Veiculo getVeiculo() {
		return veiculo;
	}


	public void setVeiculo(Veiculo veiculo) {
		if (this.veiculo == veiculo)
			return;
		if (veiculo == null) {
			Veiculo antigo = this.veiculo;
			veiculo.removeOrdemServico(this);
		} else {
			if (this.veiculo != null)
				this.veiculo.removeOrdemServico(this);
			this.veiculo = veiculo;
			veiculo.addOrdemServico(this);
		}
	}
	
	
	public Funcionario getFuncionario() {
		return funcionario;
	}


	public void setFuncionario(Funcionario funcionario) {
		if (this.funcionario == funcionario)
			return;
		if (funcionario == null) {
			Funcionario antigo = this.funcionario;
			funcionario.removeOrdemServico(this);
		} else {
			if (this.funcionario != null)
				this.funcionario.removeOrdemServico(this);
			    this.funcionario = funcionario;
			    funcionario.addOrdemServico(this);
		}
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		if (this.cliente == cliente)
			return;
		if (cliente == null) {
			Cliente antigo = this.cliente;
			cliente.removeOrdemServico(this);
		} else {
			if (this.cliente != null)
				this.cliente.removeOrdemServico(this);
			    this.cliente = cliente;
			    cliente.addOrdemServico(this);
		}
	}


	@Override
	public String toString() {
		return "OrdemDeServico [dataDeServico=" + dataDeServico + ", dataFim="
				+ dataFim + ", descricao=" + descricao + ", tipoServico="
				+ tipoServicos + "]";
	}
	

}
