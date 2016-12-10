package controle;

import java.util.List;

import model.dao.DAO;
import model.dao.IDAO;
import modelo.DadosException;
import modelo.Peca;
import modelo.util.IDadosParaTabela;
import controle.util.ControleException;
import controle.util.ErroDeControle;
import controle.util.ICtrlCasoDeUso;



public class CtrlManterPecas implements ICtrlCasoDeUso {
	//
	// ATRIBUTOS
	//
	public enum Status {
		DISPONIVEL, INCLUINDO, EXCLUINDO, ALTERANDO, ENCERRADO;

		public static void validarTransicaoStatus(Status anterior, Status novo) throws ControleException {
			if(anterior == null && novo == DISPONIVEL || 
			   anterior == DISPONIVEL && novo == INCLUINDO  || 
			   anterior == DISPONIVEL && novo == EXCLUINDO  ||
			   anterior == DISPONIVEL && novo == ALTERANDO  ||
			   anterior == DISPONIVEL && novo == ENCERRADO  ||
			   anterior == INCLUINDO  && novo == DISPONIVEL ||
			   anterior == EXCLUINDO  && novo == DISPONIVEL ||
			   anterior == ALTERANDO  && novo == DISPONIVEL)
				return;
			throw new ControleException(new ErroDeControle("N�o se pode sair do estado " + (anterior==null?"NULO":anterior) + " e ir para o estado " + novo));
		}
	}
	
	/**
	 * Refer�ncia para o controlador do programa.
	 */
	private final CtrlSessaoUsuario ctrlPrg;

	/**
	 * Refer�ncia para o controlador do caso de uso Incluir Departamento.
	 */
	private CtrlIncluirPeca ctrlIncluirPeca;

	/**
	 * Refer�ncia para o controlador do caso de uso Alterar Departamento.
	 */
	private CtrlAlterarPeca ctrlAlterarPeca;

	/**
	 * Refer�ncia para o controlador do caso de uso Alterar Departamento.
	 */
	private CtrlExcluirPeca ctrlExcluirPeca;

	/**
	 * Refer�ncia para a janela do cadastro de Departamentos
	 */
//	private UICadastroDepartamentos uiCadastro;

	/**
	 * Refer�ncia para o objeto DaoDepartamento
	 */
	private IDAO<Peca> dao = (IDAO<Peca>)DAO.getDAO(Peca.class);

	/**
	 * Atributo que indica qual opera��o est� em curso
	 */
	private Status status;

	//
	// M�TODOS
	//

	/**
	 * Construtor da classe CtrlManterDepartamentos
	 */
	public CtrlManterPecas(CtrlSessaoUsuario p) throws ControleException, DadosException {
		// Guardo a refer�ncia para o controlador do programa
		this.ctrlPrg = p;
		// Iniciando o caso de uso
		this.iniciar();
	}

	/** 
	 * Inicia o caso de uso "Manter Departamentos"
	 */
	public void iniciar() throws ControleException, DadosException {
//		// Recupero os objetos Departamento do DAO
//		this.dao = (IDAO<Peca>)DAO.getDAO(Peca.class);
//		List<IDadosParaTabela> deptos = modelo.dao.getListaObjs();
//		// Crio e abro a janela de cadastro
//		this.uiCadastro = (UICadastroDepartamentos)ViewerManager.obterViewer(this, UICadastroDepartamentos.class);
//		// Solicito � interface que carregue os objetos
//		this.uiCadastro.exibirObjetos(deptos);
//		// Solicito � interface que carregue os objetos
//		this.uiCadastro.exibir();
//		// Informo que o controlador de caso de uso est� dispon�vel
//		this.setStatus(Status.DISPONIVEL);
	}

	/** 
	 * 
	 */
	public void terminar() throws ControleException {
//		if(this.status == Status.ENCERRADO)
//			return;
//		// Fecho a janela
//		this.uiCadastro.fechar();
//		// Informo que o controlador est� encerrado
//		this.setStatus(Status.ENCERRADO);
//		// Notifico ao controlador do programa o t�rmino deste caso de uso
//		this.ctrlPrg.terminarCasoDeUsoManterDepartamentos();
	}

	/** 
	 * 
	 */
	public void iniciarCasoDeUsoIncluirPeca() throws DadosException, ControleException {
		// Indico que o controlador de caso de uso est� incluindo
		this.setStatus(Status.INCLUINDO);
		// Abro a janela de departamento
		this.ctrlIncluirPeca = new CtrlIncluirPeca(this);
	}

	/**
	 *  
	 */
	public void terminarCasoDeUsoIncluirPeca() throws DadosException, ControleException {
		if(this.ctrlIncluirPeca != null)
			this.ctrlIncluirPeca.terminar();
		this.ctrlIncluirPeca = null;
		// Indico que o controlador de caso de uso est� dispon�vel
		this.setStatus(Status.DISPONIVEL);
	// Recupero os objetos Departamento do DAO
		List<IDadosParaTabela> pecas = dao.getListaObjs();
		// Solicito a atualiza��o da interface ap�s as a��es de inclus�o
//		this.uiCadastro.exibirObjetos(deptos);
//		this.uiCadastro.exibir();
	}
	
	/** 
	 * 
	 */
	public void iniciarCasoDeUsoAlterarPeca(IDadosParaTabela selecionado) throws DadosException, ControleException {
		// Indico que o controlador de caso de uso est� incluindo
		this.setStatus(Status.ALTERANDO);
		// Promovo o casting
		Peca p = (Peca)selecionado;
		// Abro a janela de departamento
		this.ctrlAlterarPeca = new CtrlAlterarPeca(this, p);
	}

	/**
	 *  
	 */
	public void terminarCasoDeUsoAlterarPeca() throws DadosException, ControleException{
		if(this.ctrlAlterarPeca != null)
			this.ctrlAlterarPeca.terminar();
		this.ctrlAlterarPeca = null;
		// Indico que o controlador de caso de uso est� dispon�vel
		this.setStatus(Status.DISPONIVEL);
		// Recupero os objetos Departamento do DAO
		List<IDadosParaTabela> deptos = dao.getListaObjs();
		// Solicito a atualiza��o da interface ap�s as a��es de inclus�o
//		this.uiCadastro.exibirObjetos(deptos);
//		this.uiCadastro.exibir();
	}
	
	/** 
	 * 
	 */
	public void iniciarCasoDeUsoExcluirPeca(IDadosParaTabela selecionado) throws DadosException, ControleException {
		// Indico que o controlador de caso de uso est� incluindo
		this.setStatus(Status.EXCLUINDO);
		// Promovo o casting
		Peca p = (Peca)selecionado;
		// Abro a janela de departamento
		this.ctrlExcluirPeca = new CtrlExcluirPeca(this, p);
	}

	/**
	 *  
	 */
	public void terminarCasoDeUsoExcluirPeca() throws DadosException, ControleException{
		if(this.ctrlExcluirPeca != null)
			this.ctrlExcluirPeca.terminar();
		this.ctrlExcluirPeca = null;
		// Indico que o controlador de caso de uso est� dispon�vel
		this.setStatus(Status.DISPONIVEL);
		// Recupero os objetos Departamento do DAO
		List<IDadosParaTabela> pecas = dao.getListaObjs();
		// Solicito a atualiza��o da interface ap�s as a��es de inclus�o
		//this.uiCadastro.exibirObjetos(deptos);
		//this.uiCadastro.exibir();
	}	

	/**
	 * 
	 * @return
	 */
	public Status getStatus() {
		return this.status;
	}
	
	/**
	 * 
	 * @param novo
	 * @throws ControleException
	 */
	public void setStatus(Status novo) throws ControleException {
		Status.validarTransicaoStatus(this.status,novo);
		this.status = novo;
	}
}
