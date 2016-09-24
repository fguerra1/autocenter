package modelo;

import java.util.Set;

public class Peca {

	String nome;
	String descricao;
	Set<Servico> servicos;

	public Peca(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;

	}

	public Peca() {

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

	public void addServico(Servico servico) {
		if (this.servicos.contains(servico))
			return;
		this.servicos.add(servico);
		servico.addPeca(this);
	}

	public void removeServico(Servico servico) {
		if (!this.servicos.contains(servico))
			return;
		this.servicos.remove(servico);
		servico.removePeca(this);

	}

	@Override
	public String toString() {
		return "Peca [nome=" + nome + ", descricao=" + descricao + ", servicos=" + servicos + "]";
	}

}
