package viewer;

import java.util.List;

public interface UIPeca extends UI {
	/**
	 * Solicita a efetiva��o da a��o de inclus�o ou altera��o
	 */
	public abstract void solicitarExecucaoDeEfetivacao();
	/**
	 * Solicita o cancelamento da a��o de inclus�o ou altera��o
	 */
	public abstract void solicitarCancelamentoDeEfetivacao();
	/**
	 * Atualiza os campos na UI
	 */
	public abstract void atualizarCampos(List<Object> deptos);
	/**
	 * Atualiza os campos na UI
	 */
	public abstract void atualizarCampos(String cpf, int matrFuncional, String nome, Object depto, List<Object> deptos);
}