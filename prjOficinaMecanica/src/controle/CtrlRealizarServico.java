package controle;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import controle.util.ControleException;
import controle.util.ErroDeControle;
import controle.util.ICtrlCasoDeUso;
import model.dao.DAO;
import model.dao.IDAO;
import modelo.DadosException;
import modelo.Funcionario;
import modelo.Servico;

public class CtrlRealizarServico implements ICtrlCasoDeUso {
	
	
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
	 * Refer�ncia para o objeto Seri�o sendo manipulado
	 */
	private Servico servico;
	

	/**
	 * Refer�ncia para os objetos DAOS
	 */
	private IDAO<Servico> dao = (IDAO<Servico>)DAO.getDAO(Servico.class);
	private IDAO<Funcionario> daoFun = (IDAO<Funcionario>)DAO.getDAO(Funcionario.class);

	/**
	 * Atributo que indica qual opera��o est� em curso
	 */
	private Status status;

	//
	// M�TODOS
	//

	/**
	 * Construtor da classe CtrlRealizarServico
	 */
	public CtrlRealizarServico(Servico servico) throws DadosException, ControleException {
		
		this.servico = servico;
		// Iniciando o caso de uso
		this.iniciar();
	}

	/** 
	 * 
	 */
	public void iniciar() throws DadosException, ControleException {
		// Indico que o controlador de caso de uso est� incluindo
		this.setStatus(Status.INCLUINDO);
		// Crio e abro a janela de cadastro
		//this.uiDepartamento = (UIDepartamento)ViewerManager.obterViewer(this, UIDepartamento.class);
		// Solicito � interface que carregue os objetos
		//this.uiDepartamento.exibir();
	}

	/** 
	 * 
	 */
	public void terminar() throws DadosException, ControleException {
		if(this.status == Status.ENCERRADO)
			return;
		// N�o h� servico em manipula��o
		this.servico = null;
		// Fecho a UI
		//this.uiDepartamento.fechar();
		// Informo que o controlador est� dispon�vel
		this.setStatus(Status.ENCERRADO);
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
	public void incluir(float preco, int quantidade, int matricula) throws DadosException, ControleException {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.status != Status.INCLUINDO)
			throw new ControleException(new ErroDeControle("N�o � poss�vel concluir uma opera��o de inclus�o"));
		// Crio um novo objeto Servico
		Date periodo = new Date();
		Funcionario funcionario = daoFun.recuperarPelaChave(matricula);
		this.servico = new Servico(preco, quantidade, periodo);
		this.servico.addFuncionario(funcionario);
		// Salvo o objeto Servico usando o DAO
		dao.salvar(this.servico);
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
