package controle.util;

import modelo.DadosException;

public interface ICtrlCasoDeUso {
	/**
	 * 
	 */
	public abstract void iniciar() throws ControleException, DadosException;

	/**
	 * 
	 */
	public abstract void terminar() throws ControleException, DadosException;

}