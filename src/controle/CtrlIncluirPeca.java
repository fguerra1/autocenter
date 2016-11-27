package controle;


import model.dao.DAO;
import model.dao.IDAO;
import modelo.DadosException;
import modelo.Peca;
import controle.util.ControleException;
import controle.util.ErroDeControle;
import controle.util.ICtrlCasoDeUso;

public class CtrlIncluirPeca implements ICtrlCasoDeUso {
	//
	// ATRIBUTOS
	//
	public enum Status {
		INCLUINDO, ENCERRADO;

		public static void validarTransicaoStatus(Status anterior, Status novo) throws ControleException {
			if(anterior == null && novo == INCLUINDO || 
			   anterior == INCLUINDO && novo == ENCERRADO)
				return;
			throw new ControleException(new ErroDeControle("N�o se pode sair do estado " + (anterior==null?"NULO":anterior) + " e ir para o estado " + novo));
		}
	}
	
	/**
	 * Refer�ncia para o controlador do caso de uso Manter Departamentos.
	 */
	private final CtrlManterPeca ctrlManterPeca;

	/**
	 * Refer�ncia para a UI Departamento que permitir� a inclus�o e
	 * altera��o do Departamento
	 */
	//private UIDepartamento uiDepartamento;

	/**
	 * Refer�ncia para o objeto Departamento sendo manipulado
	 */
	private Peca atual;

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
	public CtrlIncluirPeca(CtrlManterPeca ctrl) throws DadosException, ControleException {
		// Guardo a refer�ncia para o controlador do programa
		this.ctrlManterPeca = ctrl;
		// Iniciando o caso de uso
		this.iniciar();
	}

	/** 
	 * 
	 */
	public void iniciar() throws DadosException, ControleException {
		// Indico que o controlador de caso de uso est� incluindo
		//this.setStatus(Status.INCLUINDO);
		// Crio e abro a janela de cadastro
		//this.uiDepartamento = (UIDepartamento)ViewerManager.obterViewer(this, UIDepartamento.class);
		// Solicito � interface que carregue os objetos
		//this.uiDepartamento.exibir();
	}

	/** 
	 * 
	 */
	public void terminar() throws DadosException, ControleException {
		//if(this.status == Status.ENCERRADO)
		//	return;
		// N�o h� departamento em manipula��o
		//this.atual = null;
		// Fecho a UI
		//this.uiDepartamento.fechar();
		// Informo que o controlador est� dispon�vel
		//this.setStatus(Status.ENCERRADO);
		// Notifico ao controlador do programa o t�rmino deste caso de uso
		//ctrlManterDepartamentos.terminarCasoDeUsoIncluirDepartamento();
	}

	/** 
	 * 
	 */
	public void cancelarIncluir() throws DadosException, ControleException {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.status != Status.INCLUINDO)
			throw new ControleException(new ErroDeControle("N�o � poss�vel cancelar uma opera��o de inclus�o"));
		// Termino o caso de uso
		this.terminar();
	}

	/** 
	 * 
	 */
	public void incluir(String descricao, String nome) throws DadosException, ControleException {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.status != Status.INCLUINDO)
			throw new ControleException(new ErroDeControle("N�o � poss�vel concluir uma opera��o de inclus�o"));
		// Crio um novo objeto Departamento
		this.atual = new Peca(descricao, nome);
		// Salvo o objeto Departamento usando o DAO
		dao.salvar(this.atual);
		// Termino o caso de uso
		this.terminar();
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
