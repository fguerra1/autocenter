package modelo.util;

/**
 * O conceito de interface em Java tem as seguintes caracter�sticas:
 * 	- Se assemelha a uma classe abstrata
 *  - Que n�o tem atributos
 *  - E todos os seus m�todos s�o abstratos (ou seja n�o tem implementa��o)
 * Uma interface � um CONTRATO DE PRESTA��O DE SERVI�OS que
 * pode ser REALIZADO por uma ou mais classes.
 * 
 * As classes que implementam esta interface ser�o aquelas cujo estado 
 * pode ser apresentado em uma tabela
 * @author Alessandro Cerqueira
 */
public interface IDadosParaTabela {
	/**
	 * Retorna um array de Strings com os nomes dos campos para apresenta��o numa
	 * tabela de interface 
	 * dos objetos
	 * @return array de string com os campos da tabela de interface
	 */
	public String[] getCamposDeTabela();
	/**
	 * Retorna um array de Objects com os estados dos campos do IDadosParaTabela para 
	 * apresenta��o numa tabela 
	 * @return array de objects com valores para tabela
	 */
	public Object[] getDadosParaTabela();
}
