package controle;

import model.dao.DAO;
import model.dao.IDAO;
import modelo.DadosException;
import modelo.Peca;
import controle.util.ControleException;
import controle.util.ErroDeControle;
import controle.util.ICtrlCasoDeUso;

public class CtrlAlterarPeca implements ICtrlCasoDeUso {
	//
	// ATRIBUTOS
	//
	public enum Status {
		ALTERANDO, ENCERRADO;

		public static void validarTransicaoStatus(Status anterior, Status novo) throws ControleException {
			if(anterior == null && novo == ALTERANDO || 
			   anterior == ALTERANDO  && novo == ENCERRADO)
				return;
			throw new ControleException(new ErroDeControle("N�o se pode sair do estado " + (anterior==null?"NULO":anterior) + " e ir para o estado " + novo));
		}
	}
	
	/**
	 * Refer�ncia para o controlador do caso de uso manter.
	 */
	private final CtrlManterPecas ctrlManterPeca;

	/**
	 * Refer�ncia para o objeto a ser atualizado 
	 */
	private Peca atual;
	
	/**
	 * Refer�ncia para a janela Departamento que permitir� a inclus�o e
	 * altera��o do Departamento
	 */
//	private UIPeca uiPeca;

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
	public CtrlAlterarPeca(CtrlManterPecas ctrl, Peca p) throws ControleException, DadosException {
		// Guardo a refer�ncia para o controlador do programa
		this.ctrlManterPeca = ctrl;
		// Guardo a refer�ncia para o objeto a ser alterado
		this.atual = p;
		// Iniciando o caso de uso
		this.iniciar();
	}

	/** 
	 * 
	 */
	public void iniciar() throws ControleException, DadosException {
//		// Informo que o controlador de caso de uso est� dispon�vel
//		this.setStatus(Status.ALTERANDO);
//		// Crio e abro a janela de cadastro
//		this.uiDepartamento = (UIDepartamento)ViewerManager.obterViewer(this, UIDepartamento.class);
//		// Solicito � interface que atualize os campos 
//		this.uiDepartamento.atualizarCampos(this.atual.getSigla(),this.atual.getNome());
//		// Solicito � interface que carregue os objetos
//		this.uiDepartamento.exibir();
	}

	/** 
	 * 
	 */
	public void terminar() throws DadosException, ControleException {
//		if(this.status == Status.ENCERRADO)
//			return;
//		// N�o h� departamento em manipula��o
//		this.atual = null;
//		// Fecho a janela
//		this.uiDepartamento.fechar();
//		// Informo que o controlador est� dispon�vel
//		this.setStatus(Status.ENCERRADO);
//		// Notifico ao controlador do programa o t�rmino deste caso de uso
//		ctrlManterDepartamentos.terminarCasoDeUsoAlterarDepartamento();
	}

	/** 
	 * 
	 */
	public void cancelarAlterar() throws DadosException, ControleException {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.status != Status.ALTERANDO)
			throw new ControleException(new ErroDeControle("N�o � poss�vel cancelar uma opera��o de altera��o"));
		// Termino o caso de uso
		this.terminar();
	}

	/** 
	 * 
	 */
	public void alterar(String descricao, String nome) throws DadosException, ControleException {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.status != Status.ALTERANDO)
			throw new ControleException(new ErroDeControle("N�o � poss�vel concluir uma opera��o de altera��o"));
		// Atualizo os campos
		this.atual.setNome(nome);
		this.atual.setDescricao(descricao);
		// Salvo o objeto Departamento usando o DAO
		dao.atualizar(this.atual);
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
