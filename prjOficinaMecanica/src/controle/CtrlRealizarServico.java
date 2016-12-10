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
			throw new ControleException(new ErroDeControle("Não se pode sair do estado " + (anterior==null?"NULO":anterior) + " e ir para o estado " + novo));
		}
	}
	
	/**
	 * Referência para o objeto Seriço sendo manipulado
	 */
	private Servico servico;
	

	/**
	 * Referência para os objetos DAOS
	 */
	private IDAO<Servico> dao = (IDAO<Servico>)DAO.getDAO(Servico.class);
	private IDAO<Funcionario> daoFun = (IDAO<Funcionario>)DAO.getDAO(Funcionario.class);

	/**
	 * Atributo que indica qual operação está em curso
	 */
	private Status status;

	//
	// MÉTODOS
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
		// Indico que o controlador de caso de uso está incluindo
		this.setStatus(Status.INCLUINDO);
		// Crio e abro a janela de cadastro
		//this.uiDepartamento = (UIDepartamento)ViewerManager.obterViewer(this, UIDepartamento.class);
		// Solicito à interface que carregue os objetos
		//this.uiDepartamento.exibir();
	}

	/** 
	 * 
	 */
	public void terminar() throws DadosException, ControleException {
		if(this.status == Status.ENCERRADO)
			return;
		// Não há servico em manipulação
		this.servico = null;
		// Fecho a UI
		//this.uiDepartamento.fechar();
		// Informo que o controlador está disponível
		this.setStatus(Status.ENCERRADO);
		// Notifico ao controlador do programa o término deste caso de uso
		//ctrlManterDepartamentos.terminarCasoDeUsoIncluirDepartamento();
	}

	/** 
	 * 
	 */
	public void cancelarIncluir() throws DadosException, ControleException {
		// Se o controlador não tinha ativado uma inclusao, não faço nada!
		if(this.status != Status.INCLUINDO)
			throw new ControleException(new ErroDeControle("Não é possível cancelar uma operação de inclusão"));
		// Termino o caso de uso
		this.terminar();
	}

	
	
	/** 
	 * 
	 */
	public void incluir(float preco, int quantidade, int matricula) throws DadosException, ControleException {
		// Se o controlador não tinha ativado uma inclusao, não faço nada!
		if(this.status != Status.INCLUINDO)
			throw new ControleException(new ErroDeControle("Não é possível concluir uma operação de inclusão"));
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
