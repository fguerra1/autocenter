package modelo;

import java.util.Date;
import java.util.Set;

public class Servico {

	float preco;
	int quantidade;
	Date periodo;
	Set<Funcionario> funcionario;
	Set<Peca> pecas;

	public Servico(float preco, int quantidade, Date perido, Set<Peca> pecas) {
		super();
		this.preco = preco;
		this.quantidade = quantidade;
		this.periodo = perido;
		this.pecas = pecas;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public void setPecas(Set<Peca> pecas) {
		this.pecas = pecas;
	}

	public Set<Peca> getPecas() {
		return pecas;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Date getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Date periodo) {
		this.periodo = periodo;
	}

	public void addFuncionario(Funcionario funcionario) {
		if (this.funcionario.contains(funcionario))
			return;
		this.funcionario.add(funcionario);
		funcionario.addServico(this);
	}

	public void removeFuncionario(Funcionario funcionario) {
		if (!this.funcionario.contains(funcionario))
			return;
		this.funcionario.remove(funcionario);
		funcionario.removeServico(this);

	}

	public void addPeca(Peca peca) {
		if (this.pecas.contains(peca))
			return;
		this.pecas.add(peca);
		peca.addServico(this);
	}

	public void removePeca(Peca peca) {
		if (!this.pecas.contains(peca))
			return;
		this.pecas.remove(peca);
		peca.removeServico(this);

	}

	@Override
	public String toString() {
		return "Servico [preco=" + preco + ", quantidade=" + quantidade + ", periodo=" + periodo + ", funcionario="
				+ funcionario + ", pecas=" + pecas + "]";
	}

}
