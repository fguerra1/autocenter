package viewer;

import java.util.List;

import modelo.util.IDadosParaTabela;

public interface UICadastroPeca extends UI {
	/**
	 * Exibe os objetos na UI
	 * @param objetos
	 */
	public abstract void exibirObjetos(List<IDadosParaTabela> objetos);
	/**
	 * Solicita a execução do caso de uso Incluir
	 */
	public abstract void solicitarExecucaoDeIncluirPeca();

	/**
	 * Solicita a execução do caso de uso Excluir
	 */
	public abstract void solicitarExecucaoDeExcluirPeca();

	/**
	 * Solicita a execução do caso de uso Alterar
	 */
	public abstract void solicitarExecucaoDeAlterarPeca();

	/**
	 * Solicita o término da execução do caso de uso Manter
	 */
	public abstract void solicitarTerminoDeManterPecas();
}