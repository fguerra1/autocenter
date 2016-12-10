package modelo;

import java.util.Set;
import java.util.TreeSet;


import modelo.DadosException;
import modelo.ErroDeDominio;

public class Funcionario extends Pessoa {

	int matricula;
	String funcao;
	Set<Servico> nomeServico;
	Set<OrdemDeServico> ordemDeServicos;

	public Funcionario(String nome, String cpf, int matricula, String funcao)throws DadosException {
		super(nome, cpf);
		this.setMatricula(matricula);
		this.setFuncao(funcao);
		this.nomeServico = new TreeSet<Servico>();
		this.ordemDeServicos = new TreeSet<OrdemDeServico>();
		
	}

	public Funcionario(int matricula, String funcao) {
		// TODO Auto-generated constructor stub
		//coloquei para parar de dar erro no CtrlIncluirFuncionario no método incluir
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) throws DadosException{
		Funcionario.validarMatricula(matricula);
	// validarMatricula//colocar validar no SET (ver prjMVC)
		this.matricula = matricula;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) throws DadosException{
		Funcionario.validarFuncao(funcao);
	
		this.funcao = funcao;
	}

	public void addOrdemServico(OrdemDeServico novaOS) {
		if (this.ordemDeServicos.contains(novaOS))
			return;
		this.ordemDeServicos.add(novaOS);
		novaOS.setFuncionario(this);
	}

	public void removeOrdemServico(OrdemDeServico oS) {
		if (!this.ordemDeServicos.contains(oS))
			return;
		this.ordemDeServicos.remove(oS);
		oS.setFuncionario(null);

	}

	public void addServico(Servico servico) {
		if (this.nomeServico.contains(servico))
			return;
		this.nomeServico.add(servico);
		servico.addFuncionario(this);
	}

	public void removeServico(Servico servico) {
		if (!this.nomeServico.contains(servico))
			return;
		this.nomeServico.remove(servico);
		servico.removeFuncionario(this);

	}

	@Override
	public String toString() {
		return "Funcionario [matricula=" + matricula + ", funcao=" + funcao + "]";
	}

	public static void validarMatricula(int matricula) throws DadosException {
		if(matricula == 0)
			throw new DadosException(new ErroDeDominio(6, "A matrícula não pode ser nula!",Funcionario.class));	
		
	}
	public static void validarFuncao(String funcao) throws DadosException {
		if(funcao == null || funcao.length() == 0)
			throw new DadosException(new ErroDeDominio(7, "A função não pode ser nula!",Funcionario.class));	
	
	
	// public static void validar matricula(int matricula){
	//
	// }
	// if(this.gerente == gerente)
	// return;
	// if(gerente == null){
	// Funcionario antigo = this.gerente;
	// this.gerente = null;
	// }

}
}
