package controle;

import model.dao.DAO;
import modelo.DadosException;
import controle.util.ControleException;
import controle.util.ICtrlCasoDeUso;


/**
 * Este � o controlador que gerencia a execu��o do meu programa.
 * @author Alessandro Cerqueira
 */
public class CtrlSessaoUsuario implements ICtrlCasoDeUso {
	//
	// ATRIBUTOS
	// ---------
	// O controlador do programa deve ter um atributo para
	// cada controlador de caso de uso do programa.
	//
	// Tamb�m o controlador do programa deve ter um atributo 
	// para referenciar a janela principal do programa.
	//

	/**
	 * Refer�ncia para o controlador do caso de uso "Manter Departamentos"
	 */
	private CtrlManterPecas 	ctrlPeca;
	/**
	 * Refer�ncia para o controlador do caso de uso "Manter Empregados"
	 */	private CtrlManterFuncionarios    	ctrlFuncionario;
	 
	 private CtrlRealizarServico ctrlrealizarServico;
//	private UIPrincipal uiPrincipal;
//	/**
//	 * Refer�ncia para o controlador do caso de uso "Manter Projetos"
//	 */
//	private CtrlManterProjetos    		ctrlProjetos;
//
//	/**
//	 * Refer�ncia para a UI principal do programa
//	 */
//	private UIPrincipal          	uiPrincipal;	
	
	//
	// M�TODOS
	//
	/**
	 * Construtor do CtrlPrograma
	 */
	public CtrlSessaoUsuario(){
	}

	/* (non-Javadoc)
	 * @see controle.ICtrlCasoDeUso#iniciar()
	 */
	@Override
	public void iniciar() {
//		// Cria e apresenta a janela principal. Observe que n�o estamos instanciando um objeto JanelaPrincipal 
//		// diretamente; ou seja, n�o estamos fazendo "this.uiPrincipal = new JanelaPrincipal(this);".
//		// Estamos utilizando o m�todo est�tico "obterViewer" para retornar qual � a implementa��o
//		// de UIPrincipal que iremos utilizar.
	//this.uiPrincipal = (UIPrincipal)ViewerManager.obterViewer(this, UIPrincipal.class);
//
//		// Inicializa os DAOs
		DAO.inicializarDAOs();
//		
 //Solicita a exibi��o da uiPrincipal
///this.uiPrincipal.exibir();
	}

	/* (non-Javadoc)
	 * @see controle.ICtrlCasoDeUso#terminar()
	 */
	@Override
	public void terminar(){
//		// Inicializa os DAOs
		DAO.fecharDAOs();
//		// M�todo est�tico da classe System que encerra o programa
//		System.exit(0);
	}
	
	/**
	 * 
	 */
	public void iniciarCasoDeUsoManterPeca() throws ControleException, DadosException {
		this.ctrlPeca = new CtrlManterPecas(this);
	}
	
	/**
	 * 
	 */
	public void terminarCasoDeUsoManterPeca() throws ControleException {
		if(this.ctrlPeca != null)
			this.ctrlPeca.terminar();
		this.ctrlPeca = null;
	}
	
	/** 
	 * 
	 */
	public void iniciarCasoDeUsoManterFuncionarios() throws ControleException, DadosException{
		// Instanciando os controladores de caso de uso do sistema
		this.ctrlFuncionario = new CtrlManterFuncionarios(this);
	}
	
	/**
	 *  
	 */
	public void terminarCasoDeUsoManterFuncionarios() throws ControleException{
		if(this.ctrlFuncionario != null)
			this.ctrlFuncionario.terminar();
		this.ctrlFuncionario = null;
	}
	
	
	public void iniciarCasoDeUsoRealizarServico() throws ControleException, DadosException{
		// Instanciando os controladores de caso de uso do sistema
	//	this.ctrlrealizarServico = new CtrlRealizarServico(this);
	}
	
	
	public void terminarCasoDeUsoRealizarServico() throws ControleException{
		//if(this.ctrlProjetos != null)
			//this.ctrlProjetos.terminar();
		//this.ctrlProjetos = null;
	}
	
//	/** 
//	 * 
//	 */
//	public void iniciarCasoDeUsoManterProjetos() throws ControleException, DadosException{
//		// Instanciando os controladores de caso de uso do sistema
//		this.ctrlProjetos = new CtrlManterProjetos(this);
//	}
//	
//	/**
//	 *  
//	 */
//	public void terminarCasoDeUsoManterProjetos() throws ControleException{
//		if(this.ctrlProjetos != null)
//			this.ctrlProjetos.terminar();
//		this.ctrlProjetos = null;
//	}
	
	/**
	 * O m�todo main corresponde ao ponto inicial de execu��o
	 * de um programa em Java.
	 * @param args
	 */
	public static void main(String[] args) throws ControleException, DadosException {
		ICtrlCasoDeUso prg = new CtrlSessaoUsuario();
		prg.iniciar();
	}
}