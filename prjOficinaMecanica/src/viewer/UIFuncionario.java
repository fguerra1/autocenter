package viewer;

public interface UIFuncionario extends UI {
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
	public abstract void atualizarCampos(String sigla, String nome);
}