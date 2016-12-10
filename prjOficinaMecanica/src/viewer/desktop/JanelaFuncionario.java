package viewer.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.DadosException;
import viewer.UIFuncionario;
import controle.CtrlAlterarFuncionario;
import controle.CtrlIncluirFuncionario;
import controle.util.ControleException;

public class JanelaFuncionario extends JFrame implements UIFuncionario {
	//
	// ATRIBUTOS
	//
	/**
	 * Referência para o controlador do caso de uso Incluir Departamento
	 */
	private final CtrlIncluirFuncionario ctrlIncluir;
	/**
	 * Referência para o controlador do caso de uso Alterar Departamento
	 */
	private final CtrlAlterarFuncionario ctrlAlterar;
	/**
	 * Indica se estou fazendo uma operação de inclusão ou alteração
	 */
	private boolean ehAlteração;
	/**
	 * Content Pane da Janela
	 */
	private JPanel contentPane;
	/**
	 * TextField para a sigla do departamento
	 */
	private JTextField tfFuncao;
	/**
	 * TextField para o nome do departamento
	 */
	private JTextField tfMatricula;

	/**
	 * Construtor para inclusão
	 * @param ct
	 * @throws DadosException
	 * @throws ControleException
	 */
	public JanelaFuncionario(CtrlIncluirFuncionario ct) throws DadosException, ControleException {
		this.ehAlteração = false;
		this.ctrlIncluir = ct;
		this.ctrlAlterar = null;
		this.criarUI();
	}

	/**
	 * Construtor para alteração
	 * @param ct
	 * @throws DadosException
	 * @throws ControleException
	 */
	public JanelaFuncionario(CtrlAlterarFuncionario ct) throws DadosException, ControleException {
		this.ehAlteração = true;
		this.ctrlIncluir = null;
		this.ctrlAlterar = ct;
		this.criarUI();
	}
	
	/**
	 * Criação da UI
	 */
	public void criarUI() {
		setTitle("Departamento");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 178);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSigla = new JLabel("Sigla:");
		lblSigla.setBounds(20, 11, 46, 14);
		contentPane.add(lblSigla);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(20, 48, 46, 14);
		contentPane.add(lblNome);

		tfFuncao = new JTextField();
		tfFuncao.setBounds(76, 8, 86, 20);
		contentPane.add(tfFuncao);
		tfFuncao.setColumns(10);

		tfMatricula = new JTextField();
		tfMatricula.setBounds(76, 45, 334, 20);
		contentPane.add(tfMatricula);
		tfMatricula.setColumns(10);

		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				solicitarExecucaoDeEfetivacao();
			}
		});
		btnOk.setBounds(73, 95, 143, 23);
		contentPane.add(btnOk);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				solicitarCancelamentoDeEfetivacao();
			}
		});
		btnCancelar.setBounds(256, 95, 154, 23);
		contentPane.add(btnCancelar);
	}

	/* (non-Javadoc)
	 * @see viewer.UIDepartamento#solicitarExecucaoDeEfetivacao()
	 */
	@Override
	public void solicitarExecucaoDeEfetivacao() {
		try {
			// Recupero os valores digitados nos textfields
			String funcao = tfFuncao.getText();
			String mat = tfMatricula.getText();
			int matricula  = Integer.parseInt(mat);
			// Verifico qual é a operação que estou fazendo
			// e notifico ao controlador
			if(!ehAlteração)
				ctrlIncluir.incluir(matricula, funcao);
			else
				ctrlAlterar.alterar(matricula, funcao);
		} catch (DadosException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} catch (ControleException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see viewer.UIDepartamento#solicitarCancelamentoDeEfetivacao()
	 */
	@Override
	public void solicitarCancelamentoDeEfetivacao() {
		try {
			if (!ehAlteração)
				ctrlIncluir.cancelarIncluir();
			else
				ctrlAlterar.cancelarAlterar();
		} catch (DadosException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} catch (ControleException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see viewer.UIDepartamento#fechar()
	 */
	@Override
	public void exibir() {
		this.setVisible(true);
	}

	/* (non-Javadoc)
	 * @see viewer.UIDepartamento#fechar()
	 */
	@Override
	public void fechar() {
		this.setVisible(false);
	}
	
	/* (non-Javadoc)
	 * @see viewer.UIDepartamento#limpar()
	 */
	@Override
	public void limpar() {
		this.tfFuncao.setText(null);
		this.tfMatricula.setText(null);
	}

	/* (non-Javadoc)
	 * @see viewer.UIDepartamento#atualizarCampos(java.lang.String, java.lang.String)
	 */
	@Override
	public void atualizarCampos(String sigla, String nome) {
		limpar();
		this.tfFuncao.setText(sigla);
		this.tfMatricula.setText(nome);
	}
}
