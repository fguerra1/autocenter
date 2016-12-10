package controle;

import controle.CtrlExcluirPeca.Status;
import controle.util.ControleException;
import controle.util.ErroDeControle;
import controle.util.ICtrlCasoDeUso;
import model.dao.DAO;
import model.dao.IDAO;
import modelo.DadosException;
import modelo.Funcionario;
import modelo.Peca;
import modelo.Servico;

public class CtrlExcluirFuncionario implements ICtrlCasoDeUso {
	//
	// ATRIBUTOS
	//
	public enum Status {
		EXCLUINDO, ENCERRADO;

		public static void validarTransicaoStatus(Status anterior, Status novo) throws ControleException {
			if(anterior == null && novo == EXCLUINDO || 
			   anterior == EXCLUINDO && novo == ENCERRADO)
				return;
			throw new ControleException(new ErroDeControle("N�o se pode sair do estado " + (anterior==null?"NULO":anterior) + " e ir para o estado " + novo));
		}
	}
	
	/**
	 * Refer�ncia para o controlador do caso de uso
	 */
	private final CtrlManterFuncionarios ctrlManterFuncionario;

	/**
	 * Refer�ncia para o objeto a ser atualizado 
	 */
	private Funcionario atual;
	
	/**
	 * Refer�ncia para a janela Departamento que permitir� a exclus�o do Departamento
	 */
	//private UIExcluirPeca uiExcluirPeca;

	/**
	 * Refer�ncia para o objeto DaoDepartamento
	 */
	private IDAO<Funcionario> dao = (IDAO<Funcionario>)DAO.getDAO(Funcionario.class);

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
	public CtrlExcluirFuncionario(CtrlManterFuncionarios ctrl, Funcionario p) throws ControleException, DadosException {
		// Guardo a refer�ncia para o controlador do programa
		this.ctrlManterFuncionario = ctrl;
		// Guardo a refer�ncia para o objeto a ser alterado
		this.atual = p;
		// Iniciando o caso de uso
		this.iniciar();
	}

	/** 
	 * 
	 */
	public void iniciar() throws ControleException, DadosException {
//		// Informo que o controlador de caso de uso est� excluindo
		this.setStatus(Status.EXCLUINDO);
//		// Crio e abro a janela de cadastro
//		this.uiExcluirDepartamento = (UIExcluirDepartamento)ViewerManager.obterViewer(this, UIExcluirDepartamento.class);
//		// Solicito � interface que atualize os campos 
//		this.uiExcluirDepartamento.atualizarCampos(this.atual.getSigla(),this.atual.getNome());
//		// Solicito � interface que carregue os objetos
//		this.uiExcluirDepartamento.exibir();
	}

	/** 
	 * 
	 */
	public void terminar() throws DadosException, ControleException {
		if(this.status == Status.ENCERRADO)
			return;
//		// N�o h� departamento em manipula��o
		this.atual = null;
//		// Solicito o fechamento da janela
//		this.uiExcluirDepartamento.fechar();		
//		// Informo que o controlador est� dispon�vel
//		this.setStatus(Status.ENCERRADO);
//		// Notifico ao controlador do programa o t�rmino deste caso de uso
		ctrlManterFuncionario.terminarCasoDeUsoExcluirFuncionario();
	}

	/** 
	 * 
	 */
	public void cancelarExcluir() throws DadosException, ControleException {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.status != Status.EXCLUINDO)
			throw new ControleException(new ErroDeControle("N�o � poss�vel cancelar uma opera��o de exclus�o"));
		// Termino o caso de uso
		this.terminar();
	}

	/** 
	 * 
	 */
	public void excluir() throws DadosException, ControleException {
		// Se o controlador n�o tinha ativado uma exclus�o, n�o fa�o nada!
		if(this.status != Status.EXCLUINDO)
			throw new ControleException(new ErroDeControle("N�o � poss�vel concluir uma opera��o de exclus�o"));
		// Desaloco todos os empregados do departamento
		//for(Servico s : this.atual.listarServicos())
			//this.atual.removeServico(s);
		// Salvo o objeto Departamento usando o DAO
		dao.remover(this.atual);
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
