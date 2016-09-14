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

	@Override
	public String toString() {
		return "Servico [preco=" + preco + ", quantidade=" + quantidade
				+ ", periodo=" + periodo + ", funcionario=" + funcionario
				+ ", pecas=" + pecas + "]";
	}

}
