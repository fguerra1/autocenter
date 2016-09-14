package modelo;

import java.util.Set;

public class Funcionario extends Pessoa {

	int matricula;
	String funcao;
	Set<Servico> nomeServico;
	Set<OrdemDeServico> ordem;

	public Funcionario(String nome, String cpf, int matricula, String funcao) {
		super(nome, cpf);
		this.matricula = matricula;//colocar metodo set (ver prjMVC)
		this.funcao = funcao;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		//validarMatricula//colocar validar no SET (ver prjMVC)
		this.matricula = matricula;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	@Override
	public String toString() {
		return "Funcionario [matricula=" + matricula + ", funcao=" + funcao
				+ "]";
	}
		
//	public static void validar matricula(int matricula){
//		
//	}
//		if(this.gerente == gerente)
//			return;
//		if(gerente == null){
//			Funcionario antigo = this.gerente;
//			this.gerente = null;
//		}

	}

