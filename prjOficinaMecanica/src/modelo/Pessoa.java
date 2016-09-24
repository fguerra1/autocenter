package modelo;

import java.util.Set;
import java.util.TreeSet;

public class Pessoa {

	private String nome;
	private String cpf;
	Set<Contato> contatos;

	public Pessoa(String nome, String cpf) {
		this.setNome(nome);
		this.setCpf(cpf);
		this.contatos = new TreeSet<Contato>();
	}

	public Pessoa() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void addContato(Contato nvoContato) {
		if (this.contatos.contains(nvoContato))
			return;
		this.contatos.add(nvoContato);
		nvoContato.setPessoa(this);
	}

	public void removeContato(Contato contato) {
		if (!this.contatos.contains(contato))
			return;
		this.contatos.remove(contato);
		contato.setPessoa(null);
	}

	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", cpf=" + cpf + ", contatos=" + contatos + "]";
	}

}
