package viewer;

import java.util.List;

import modelo.util.IDadosParaTabela;

public interface UICadastroFuncionario extends UI {

	/**
	 * Exibe os objetos na UI
	 * @param objetos
	 */
	public abstract void exibirObjetos(List<IDadosParaTabela> objetos);

	/**
	 * Solicita a execução do caso de uso Incluir
	 */
	public abstract void solicitarExecucaoDeIncluirFuncionario();

	/**
	 * Solicita a execução do caso de uso Excluir
	 */
	public abstract void solicitarExecucaoDeExcluirFuncionario();

	/**
	 * Solicita a execução do caso de uso Alterar
	 */
	public abstract void solicitarExecucaoDeAlterarFuncionario();

	/**
	 * Solicita o término da execução do caso de uso Manter
	 */
	public abstract void solicitarTerminoDeManterFuncionarios();



}