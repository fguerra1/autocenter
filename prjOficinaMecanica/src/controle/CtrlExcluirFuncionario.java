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
			throw new ControleException(new ErroDeControle("Não se pode sair do estado " + (anterior==null?"NULO":anterior) + " e ir para o estado " + novo));
		}
	}
	
	/**
	 * Referência para o controlador do caso de uso
	 */
	private final CtrlManterFuncionarios ctrlManterFuncionario;

	/**
	 * Referência para o objeto a ser atualizado 
	 */
	private Funcionario atual;
	
	/**
	 * Referência para a janela Departamento que permitirá a exclusão do Departamento
	 */
	//private UIExcluirPeca uiExcluirPeca;

	/**
	 * Referência para o objeto DaoDepartamento
	 */
	private IDAO<Funcionario> dao = (IDAO<Funcionario>)DAO.getDAO(Funcionario.class);

	/**
	 * Atributo que indica qual operação está em curso
	 */
	private Status status;

	//
	// MÉTODOS
	//

	/**
	 * Construtor da classe CtrlManterDepartamentos
	 */
	public CtrlExcluirFuncionario(CtrlManterFuncionarios ctrl, Funcionario p) throws ControleException, DadosException {
		// Guardo a referência para o controlador do programa
		this.ctrlManterFuncionario = ctrl;
		// Guardo a referência para o objeto a ser alterado
		this.atual = p;
		// Iniciando o caso de uso
		this.iniciar();
	}

	/** 
	 * 
	 */
	public void iniciar() throws ControleException, DadosException {
//		// Informo que o controlador de caso de uso está excluindo
		this.setStatus(Status.EXCLUINDO);
//		// Crio e abro a janela de cadastro
//		this.uiExcluirDepartamento = (UIExcluirDepartamento)ViewerManager.obterViewer(this, UIExcluirDepartamento.class);
//		// Solicito à interface que atualize os campos 
//		this.uiExcluirDepartamento.atualizarCampos(this.atual.getSigla(),this.atual.getNome());
//		// Solicito à interface que carregue os objetos
//		this.uiExcluirDepartamento.exibir();
	}

	/** 
	 * 
	 */
	public void terminar() throws DadosException, ControleException {
		if(this.status == Status.ENCERRADO)
			return;
//		// Não há departamento em manipulação
		this.atual = null;
//		// Solicito o fechamento da janela
//		this.uiExcluirDepartamento.fechar();		
//		// Informo que o controlador está disponível
//		this.setStatus(Status.ENCERRADO);
//		// Notifico ao controlador do programa o término deste caso de uso
		ctrlManterFuncionario.terminarCasoDeUsoExcluirFuncionario();
	}

	/** 
	 * 
	 */
	public void cancelarExcluir() throws DadosException, ControleException {
		// Se o controlador não tinha ativado uma inclusao, não faço nada!
		if(this.status != Status.EXCLUINDO)
			throw new ControleException(new ErroDeControle("Não é possível cancelar uma operação de exclusão"));
		// Termino o caso de uso
		this.terminar();
	}

	/** 
	 * 
	 */
	public void excluir() throws DadosException, ControleException {
		// Se o controlador não tinha ativado uma exclusão, não faço nada!
		if(this.status != Status.EXCLUINDO)
			throw new ControleException(new ErroDeControle("Não é possível concluir uma operação de exclusão"));
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
