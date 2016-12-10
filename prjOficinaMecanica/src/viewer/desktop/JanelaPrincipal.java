package viewer.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.DadosException;
import viewer.UIPrincipal;
import controle.CtrlSessaoUsuario;
import controle.util.ControleException;

/**
 * Classe que implementa a janela principal do sistema
 * @author Alessandro Cerqueira
 */
public class JanelaPrincipal extends JFrame implements UIPrincipal {

	//
	// ATRIBUTOS
	//
	
	/**
	 * Referência para o painel de conteúdo da janela
	 */
	private JPanel contentPane;
	/**
	 * Referência para o controlador do programa a quem a janela
	 * principal irá sempre mandar a notificação.
	 */
	private CtrlSessaoUsuario ctrlPrg;
	
	//
	// MÉTODOS
	//
	/**
	 * Create the frame.
	 */
	//public JanelaPrincipal() {
	public JanelaPrincipal(CtrlSessaoUsuario p) {
		this.ctrlPrg = p;
		this.criarUI();
	}
	
	/**
	 * Cria visualmente a Janela
	 */
	public void criarUI() {
		setTitle("Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 188);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnDepartamentos = new JButton("Peça");
		btnDepartamentos.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		btnDepartamentos.addActionListener(new ActionListener() {
			// Método acionado quando o botão "Departamentos" 
			// for pressionado (Método de Callback).
			public void actionPerformed(ActionEvent arg0) {
				try {
					ctrlPrg.iniciarCasoDeUsoManterPeca();
				} catch(ControleException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				} catch(DadosException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}
			}
		});
		btnDepartamentos.setBounds(10, 11, 142, 55);
		contentPane.add(btnDepartamentos);
		
		JButton btnEmpregados = new JButton("Funcionario");
		btnEmpregados.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		btnEmpregados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Método acionado quando o botão "Empregados" 
				// for pressionado (Método de Callback).
				try {
					ctrlPrg.iniciarCasoDeUsoManterFuncionarios();
				} catch(ControleException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				} catch(DadosException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}
			}
		});
		btnEmpregados.setBounds(180, 11, 142, 55);
		contentPane.add(btnEmpregados);
		
		JButton btnProjetos = new JButton("Serviço");
		btnProjetos.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		btnProjetos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Método acionado quando o botão "Projetos" 
				// for pressionado (Método de Callback).
				try {
					ctrlPrg.iniciarCasoDeUsoRealizarServico();
				} catch(ControleException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				} catch(DadosException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}
			}
		});
		btnProjetos.setBounds(10, 84, 142, 55);
		contentPane.add(btnProjetos);
			
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fechar();
			}
		});
		btnSair.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/com/sun/java/swing/plaf/windows/icons/HardDrive.gif")));
		btnSair.setBounds(180, 84, 142, 55);
		contentPane.add(btnSair);		
	}
	
	/**
	 * Fecha a janela
	 */
	public void fechar() {
		this.setVisible(false);
		ctrlPrg.terminar();		
	}

	/**
	 * Limpa a Janela
	 */
	public void limpar() {
	}

	/**
	 * Exibe a Janela
	 */
	public void exibir() {
		this.setVisible(true);
	}
}
