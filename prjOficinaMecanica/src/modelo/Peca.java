package modelo;

import java.util.Set;

public class Peca {

	String nome;
	String descricao;
	Servico servico;
	Set<Servico> servicos;
	
	public Peca(String nome, String descricao, Servico servico) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.servico = servico;
	}
	
	public Peca () {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	@Override
	public String toString() {
		return "Peca [nome=" + nome + ", descricao=" + descricao + ", servico="
				+ servico + "]";
	}
	
	
	
	
}
