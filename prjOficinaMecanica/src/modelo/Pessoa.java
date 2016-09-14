package modelo;

import java.util.Set;

public class Pessoa {
	
	private String nome;
	private String cpf;
	Set<Contato> contatos;
	
	public Pessoa ( String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
		
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

	public void addContato(){
		
	}
	
	public void removeContato(){
		
	}

	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", cpf=" + cpf + ", contatos="
				+ contatos + "]";
	}
	
	
}
