package controle;

import java.util.List;

import controle.CtrlManterPeca.Status;
import controle.util.ControleException;
import controle.util.ErroDeControle;
import controle.util.ICtrlCasoDeUso;
import model.dao.DAO;
import model.dao.IDAO;
import modelo.DadosException;
import modelo.Funcionario;
import modelo.Peca;
import modelo.util.IDadosParaTabela;

public class CtrlManterFuncionario  implements ICtrlCasoDeUso{
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
			throw new ControleException(new ErroDeControle("Não se pode sair do estado " + (anterior==null?"NULO":anterior) + " e ir para o estado " + novo));
		}
	}
	
	/**
	 * Referência para o controlador do programa.
	 */
	private final CtrlSessaoUsuario ctrlPrg;

	/**
	 * Referência para o controlador do caso de uso Incluir Departamento.
	 */
	private CtrlIncluirFuncionario ctrlIncluirFuncionario;

	/**
	 * Referência para o controlador do caso de uso Alterar Departamento.
	 */
	private CtrlAlterarFuncionario ctrlAlterarFuncionario;

	/**
	 * Referência para o controlador do caso de uso Alterar Departamento.
	 */
	private CtrlExcluirFuncionario ctrlExcluirFuncionario;

	/**
	 * Referência para a janela do cadastro de Departamentos
	 */
//	private UICadastroDepartamentos uiCadastro;

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
	 * Construtor da classe CtrlManterFuncionario
	 */
	public CtrlManterFuncionario(CtrlSessaoUsuario p) throws ControleException, DadosException {
		// Guardo a referência para o controlador do programa
		this.ctrlPrg = p;
		// Iniciando o caso de uso
		this.iniciar();
	}

	/** 
	 * Inicia o caso de uso "Manter Funcionario"
	 */
	public void iniciar() throws ControleException, DadosException {
//		// Recupero os objetos Departamento do DAO
//		this.dao = (IDAO<Peca>)DAO.getDAO(Peca.class);
//		List<IDadosParaTabela> deptos = modelo.dao.getListaObjs();
//		// Crio e abro a janela de cadastro
//		this.uiCadastro = (UICadastroDepartamentos)ViewerManager.obterViewer(this, UICadastroDepartamentos.class);
//		// Solicito à interface que carregue os objetos
//		this.uiCadastro.exibirObjetos(deptos);
//		// Solicito à interface que carregue os objetos
//		this.uiCadastro.exibir();
//		// Informo que o controlador de caso de uso está disponível
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
//		// Informo que o controlador está encerrado
//		this.setStatus(Status.ENCERRADO);
//		// Notifico ao controlador do programa o término deste caso de uso
//		this.ctrlPrg.terminarCasoDeUsoManterDepartamentos();
	}

	/** 
	 * 
	 */
	public void iniciarCasoDeUsoIncluirFuncionario() throws DadosException, ControleException {
		// Indico que o controlador de caso de uso está incluindo
		this.setStatus(Status.INCLUINDO);
		// Abro a janela de departamento
		this.ctrlIncluirFuncionario = new CtrlIncluirFuncionario(this);
	}

	/**
	 *  
	 */
	public void terminarCasoDeUsoIncluirFuncionario() throws DadosException, ControleException {
		if(this.ctrlIncluirFuncionario != null)
			this.ctrlIncluirFuncionario.terminar();
		this.ctrlIncluirFuncionario = null;
		// Indico que o controlador de caso de uso está disponível
		this.setStatus(Status.DISPONIVEL);
	// Recupero os objetos Departamento do DAO
		List<IDadosParaTabela> pecas = dao.getListaObjs();
		// Solicito a atualização da interface após as ações de inclusão
//		this.uiCadastro.exibirObjetos(deptos);
//		this.uiCadastro.exibir();
	}
	
	/** 
	 * 
	 */
	public void iniciarCasoDeUsoAlterarFuncionario(IDadosParaTabela selecionado) throws DadosException, ControleException {
		// Indico que o controlador de caso de uso está incluindo
		this.setStatus(Status.ALTERANDO);
		// Promovo o casting
		Funcionario p = (Funcionario)selecionado;
		// Abro a janela de departamento
		this.ctrlAlterarFuncionario = new CtrlAlterarFuncionario(this, p);
	}

	/**
	 *  
	 */
	public void terminarCasoDeUsoAlterarFuncionario() throws DadosException, ControleException{
		if(this.ctrlAlterarFuncionario != null)
			this.ctrlAlterarFuncionario.terminar();
		this.ctrlAlterarFuncionario = null;
		// Indico que o controlador de caso de uso está disponível
		this.setStatus(Status.DISPONIVEL);
		// Recupero os objetos Departamento do DAO
		List<IDadosParaTabela> funcionarios = dao.getListaObjs();
		// Solicito a atualização da interface após as ações de inclusão
//		this.uiCadastro.exibirObjetos(deptos);
//		this.uiCadastro.exibir();
	}
	
	/** 
	 * 
	 */
	public void iniciarCasoDeUsoExcluirFuncionario(IDadosParaTabela selecionado) throws DadosException, ControleException {
		// Indico que o controlador de caso de uso está incluindo
		this.setStatus(Status.EXCLUINDO);
		// Promovo o casting
		Funcionario p = (Funcionario)selecionado;
		// Abro a janela de departamento
		this.ctrlExcluirFuncionario = new CtrlExcluirFuncionario(this, p);
	}

	/**
	 *  
	 */
	public void terminarCasoDeUsoExcluirFuncionario() throws DadosException, ControleException{
		if(this.ctrlExcluirFuncionario != null)
			this.ctrlExcluirFuncionario.terminar();
		this.ctrlExcluirFuncionario = null;
		// Indico que o controlador de caso de uso está disponível
		this.setStatus(Status.DISPONIVEL);
		// Recupero os objetos Departamento do DAO
		List<IDadosParaTabela> funcionarios = dao.getListaObjs();
		// Solicito a atualização da interface após as ações de inclusão
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
