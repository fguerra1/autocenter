package modelo;

import java.util.Date;
import java.util.Set;

public class OrdemDeServico {

	Date dataDeServico;
	Date dataFim;
	String descricao;
	Set<TipoDeServico> tipoServicos;
	Funcionario nomeFuncionario;
	Cliente nomeCliente;
	Veiculo placaVeiculo;

	public OrdemDeServico(Date dataDeServico, Date dataFim, String descricao, TipoDeServico tipoServico) {
		super();
		this.dataDeServico = dataDeServico;
		this.dataFim = dataFim;
		this.descricao = descricao;
		this.tipoServicos = tipoServicos;
	}


	public Set<TipoDeServico> getTipoServicos() {
		return tipoServicos;
	}


	public void setTipoServicos(Set<TipoDeServico> tipoServicos) {
		this.tipoServicos = tipoServicos;
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

	@Override
	public String toString() {
		return "OrdemDeServico [dataDeServico=" + dataDeServico + ", dataFim="
				+ dataFim + ", descricao=" + descricao + ", tipoServico="
				+ tipoServicos + "]";
	}
	

}
