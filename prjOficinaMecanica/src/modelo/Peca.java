package modelo;

import java.util.Set;
import java.util.TreeSet;

import dao.DadosException;
import dao.ErroDeDominio;

public class Peca {

	String nome;
	String descricao;
	Set<Servico> servicos;

	public Peca(String nome, String descricao) throws DadosException{
		super();
		this.setNome(nome);
		this.setDescricao(descricao);
		this.servicos = new TreeSet<Servico>();

	}

	public Peca() {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws DadosException{
		Peca.validarNome(nome);
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) throws DadosException {
		Peca.validarDescricao(descricao);
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

	public static void validarNome (String nome) throws DadosException{
		if(nome == null || nome.length() == 0)
			throw new DadosException(new ErroDeDominio(1, "O nome não pode ser nulo!", Peca.class));	
	}
	public static void validarDescricao (String descricao) throws DadosException{
		if(descricao == null || descricao.length() == 0)
			throw new DadosException(new ErroDeDominio(2, "A descrição não pode ser nula!", Peca.class));	
	}
	
	}

