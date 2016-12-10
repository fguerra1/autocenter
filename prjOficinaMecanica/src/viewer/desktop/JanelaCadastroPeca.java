package viewer.desktop;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo.DadosException;
import modelo.util.IDadosParaTabela;
import viewer.UICadastroPeca;
import controle.CtrlManterPecas;
import controle.util.ControleException;

public class JanelaCadastroPeca extends JFrame implements UICadastroPeca {
	/**
	 * Referência para o controlador do caso de uso
	 * Manter Empregados
	 */
	private CtrlManterPecas ctrl;
	/**
	 * Lista de objetos a serem exibidos 
	 */
	private List<IDadosParaTabela> objetos;
	/**
	 * Referência ao contentPane da Janela 
	 */
	private JPanel contentPane;
	/**
	 * Referência ao JTable embutido na janela
	 */
	private JTable table;
	/**
	 * Referência para o TableModel da tabela
	 */
	private DefaultTableModel tableModel;
	
	/**
	 * Create the frame.
	 */
	public JanelaCadastroPeca(CtrlManterPecas c) {
		this.ctrl = c;
		this.criarUI();
	}
	
	/**
	 * Cria Visualmente a Janela
	 */
	@Override
	public void criarUI() {
		setTitle("Pecas");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 569, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				solicitarExecucaoDeIncluirPeca();
			}
		});
		btnIncluir.setBounds(81, 234, 89, 23);
		contentPane.add(btnIncluir);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				solicitarExecucaoDeExcluirPeca();
			}
		});
		btnExcluir.setBounds(180, 234, 89, 23);
		contentPane.add(btnExcluir);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				solicitarExecucaoDeAlterarPeca();
			}
		});
		btnAlterar.setBounds(279, 234, 89, 23);
		contentPane.add(btnAlterar);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				solicitarTerminoDeManterPecas();
			}
		});
		btnSair.setBounds(378, 234, 89, 23);
		contentPane.add(btnSair);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 533, 212);
		contentPane.add(scrollPane);
		
		table = new JTable();
		this.tableModel = new DefaultTableModel(null,new String[] {"Pecas"}); 
		table.setModel(this.tableModel);
		scrollPane.setViewportView(table);
	}
	
	/* (non-Javadoc)
	 * @see viewer.UICadastroEmpregados#exibirObjetos(java.util.List)
	 */
	@Override
	public void exibirObjetos(List<IDadosParaTabela> objetos) {
		this.limpar();
		if(objetos.size() > 0) {
			this.tableModel = new DefaultTableModel(null, objetos.get(0).getCamposDeTabela());
			table.setModel(this.tableModel);
			table.getColumnModel().getColumn(0).setPreferredWidth(108);
			table.getColumnModel().getColumn(1).setPreferredWidth(70);
			table.getColumnModel().getColumn(2).setPreferredWidth(269);
			table.getColumnModel().getColumn(3).setPreferredWidth(247);
		}		
		this.objetos = objetos;		
		for(IDadosParaTabela d : objetos)
			this.tableModel.addRow(d.getDadosParaTabela());
	}
		
	/* (non-Javadoc)
	 * @see viewer.UICadastroEmpregados#limpar()
	 */
	@Override
	public void limpar() {
		while(this.tableModel.getRowCount() > 0)
			this.tableModel.removeRow(0);
	}

	/* (non-Javadoc)
	 * @see viewer.UICadastroEmpregados#solicitarExecucaoDeIncluirEmpregado()
	 */
	@Override
	public void solicitarExecucaoDeIncluirPeca() {
		try {
			this.ctrl.iniciarCasoDeUsoIncluirPeca();
		} catch (DadosException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} catch (ControleException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see viewer.UICadastroEmpregados#solicitarExecucaoDeExcluirEmpregado()
	 */
	@Override
	public void solicitarExecucaoDeExcluirPeca() {
		// Recupero a posição selecionada
		int pos = table.getSelectedRow();
		// Se a posição for -1, não há ninguém selecionado. Então cancelo
		// a operação
		if(pos < 0)
			return;
		// Informo ao controlador para iniciar o processo de exclusão
		try {
			ctrl.iniciarCasoDeUsoExcluirPeca(this.objetos.get(pos));
		} catch(ControleException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}	
		catch(DadosException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}	
	}
	
	/* (non-Javadoc)
	 * @see viewer.UICadastroEmpregados#solicitarExecucaoDeAlterarEmpregado()
	 */
	@Override
	public void solicitarExecucaoDeAlterarPeca() {
		// Recupero a posição selecionada
		int pos = table.getSelectedRow();
		// Se a posição for -1, não há ninguém selecionado. Então cancelo
		// a operação
		if(pos < 0)
			return;
		// Informo ao controlador para iniciar o processo de alteração
		try {
			ctrl.iniciarCasoDeUsoAlterarPeca(this.objetos.get(pos));
		} catch(ControleException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		catch(DadosException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see viewer.UICadastroEmpregados#solicitarTerminoDeManterEmpregados()
	 */
	@Override
	public void solicitarTerminoDeManterPecas() {
		try {
			ctrl.terminar();
		} catch (ControleException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see viewer.UICadastroDepartamentos#fechar()
	 */
	@Override
	public void exibir() {
		this.setVisible(true);
	}
		
	/* (non-Javadoc)
	 * @see viewer.UICadastroDepartamentos#fechar()
	 */
	@Override
	public void fechar() {
		this.setVisible(false);
	}

		
}