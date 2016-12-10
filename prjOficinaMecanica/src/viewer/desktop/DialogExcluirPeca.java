package viewer.desktop;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modelo.DadosException;
import viewer.UIExcluirPeca;
import controle.CtrlExcluirFuncionario;
import controle.util.ControleException;

public class DialogExcluirPeca implements UIExcluirPeca {
	//
	// ATRIBUTOS
	//
	/**
	 * Referência para o controlador do caso de uso Excluir Empregado
	 */
	private final CtrlExcluirFuncionario ctrl;
	/**
	 * Content Pane da Janela
	 */
	private JPanel contentPane;
	/**
	 * Mensagem de Exclusão
	 */
	private String mensagem;
	
	/**
	 * Construtor para exclusão
	 * @param ct
	 * @throws DadosException
	 * @throws ControleException
	 */
	public DialogExcluirPeca(CtrlExcluirFuncionario ct) throws DadosException, ControleException {
		this.ctrl = ct;
	}

	/**
	 * Criação da UI
	 */
	public void criarUI() {
	}

	/* (non-Javadoc)
	 * @see viewer.UIEmpregado#solicitarExecucaoDeEfetivacao()
	 */
	@Override
	public void solicitarExecucaoDeEfetivacao() {
		try {
			ctrl.excluir();
		} catch (DadosException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} catch (ControleException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see viewer.UIEmpregado#solicitarCancelamentoDeEfetivacao()
	 */
	@Override
	public void solicitarCancelamentoDeEfetivacao() {
		try {
			ctrl.cancelarExcluir();
		} catch (DadosException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} catch (ControleException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see viewer.UIEmpregado#fechar()
	 */
	@Override
	public void exibir() {
		if(JOptionPane.showConfirmDialog(null, this.mensagem) == JOptionPane.YES_OPTION)
			this.solicitarExecucaoDeEfetivacao();
		else
			this.solicitarCancelamentoDeEfetivacao();
	}

	/* (non-Javadoc)
	 * @see viewer.UIEmpregado#fechar()
	 */
	@Override
	public void fechar() {
	}

	/* (non-Javadoc)
	 * @see viewer.UIEmpregado#limpar()
	 */
	@Override
	public void limpar() {
	}

	/* (non-Javadoc)
	 * @see viewer.UIEmpregado#atualizarCampos(java.lang.String, java.lang.String)
	 */
	@Override
	public void atualizarCampos(String cpf, int matrFuncional, String nome, Object depto, List<Object> deptos) {
		this.mensagem = "Deseja excluir o Empregado " + cpf + "-" + nome + " do departamento " + depto + "?";
	}
}
