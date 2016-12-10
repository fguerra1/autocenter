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
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import modelo.DadosException;
import modelo.util.IDadosParaTabela;
import viewer.UICadastroFuncionario;
import controle.CtrlManterFuncionarios;
import controle.CtrlManterPecas;
import controle.util.ControleException;

public class JanelaCadastroFuncionario extends JFrame implements UICadastroFuncionario {
	/**
	 * Refer�ncia para o controlador do caso de uso
	 * Manter Departamentos
	 */
	private CtrlManterFuncionarios ctrl;
	/**
	 * Lista de objetos a serem exibidos 
	 */
	private List<IDadosParaTabela> objetos;
	/**
	 * Refer�ncia ao contentPane da Janela 
	 */
	private JPanel contentPane;
	/**
	 * Refer�ncia ao JTable embutido na janela
	 */
	private JTable table;
	/**
	 * Refer�ncia para o TableModel da tabela
	 */
	private DefaultTableModel tableModel;
	/**
	 * Create the frame.
	 */
	public JanelaCadastroFuncionario(CtrlManterFuncionarios c) {
		this.ctrl = c;
		this.criarUI();
	}

	/**
	 * Cria Visualmente a Janela 
	 */
	@Override
	public void criarUI() {
		setTitle("Funcionarios");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 419, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				solicitarExecucaoDeIncluirFuncionario();
			}
		});
		btnIncluir.setBounds(10, 232, 89, 23);
		contentPane.add(btnIncluir);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				solicitarExecucaoDeExcluirFuncionario();
			}
		});
		btnExcluir.setBounds(109, 232, 89, 23);
		contentPane.add(btnExcluir);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				solicitarExecucaoDeAlterarFuncionario();
			}
		});
		btnAlterar.setBounds(208, 232, 89, 23);
		contentPane.add(btnAlterar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				solicitarTerminoDeManterFuncionarios();
			}
		});
		btnSair.setBounds(307, 232, 89, 23);
		contentPane.add(btnSair);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 386, 212);
		contentPane.add(scrollPane);
		
		table = new JTable();
		this.tableModel = new DefaultTableModel(null, new String[] {"Funcionario"}); 
		table.setModel(this.tableModel);			
		scrollPane.setViewportView(table);
	}
		
	/* (non-Javadoc)
	 * @see viewer.UICadastroDepartamentos#exibirObjetos(java.util.List)
	 */
	@Override
	public void exibirObjetos(List<IDadosParaTabela> objetos) {
		this.limpar();
		if(objetos.size() > 0) {
			this.tableModel = new DefaultTableModel(null, objetos.get(0).getCamposDeTabela());
			table.setModel(this.tableModel);			
			table.getColumnModel().getColumn(0).setPreferredWidth(40);
			table.getColumnModel().getColumn(1).setPreferredWidth(200);
			table.getColumnModel().getColumn(2).setPreferredWidth(60);
		}		
		this.objetos = objetos;		
		for(IDadosParaTabela d : objetos)
			this.tableModel.addRow(d.getDadosParaTabela());
	}
		
	/* (non-Javadoc)
	 * @see viewer.UICadastroDepartamentos#limpar()
	 */
	@Override
	public void limpar() {
		while(this.tableModel.getRowCount() > 0)
			this.tableModel.removeRow(0);
	}

	/* (non-Javadoc)
	 * @see viewer.UICadastroDepartamentos#solicitarExecucaoDeIncluirDepartamento()
	 */
	@Override
	public void solicitarExecucaoDeIncluirFuncionario() {
		try {
			this.ctrl.iniciarCasoDeUsoIncluirFuncionario();
		} catch (DadosException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} catch (ControleException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see viewer.UICadastroDepartamentos#solicitarExecucaoDeExcluirDepartamento()
	 */
	@Override
	public void solicitarExecucaoDeExcluirFuncionario() {
		// Recupero a posi��o selecionada
		int pos = table.getSelectedRow();
		// Se a posi��o for -1, n�o h� ningu�m selecionado. Ent�o cancelo
		// a opera��o
		if(pos < 0)
			return;
		// Informo ao controlador para iniciar o processo de exclus�o
		try {
			ctrl.iniciarCasoDeUsoExcluirFuncionario(this.objetos.get(pos));
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
	 * @see viewer.UICadastroDepartamentos#solicitarExecucaoDeAlterarDepartamento()
	 */
	@Override
	public void solicitarExecucaoDeAlterarFuncionario() {
		// Recupero a posi��o selecionada
		int pos = table.getSelectedRow();
		// Se a posi��o for -1, n�o h� ningu�m selecionado. Ent�o cancelo
		// a opera��o
		if(pos < 0)
			return;
		// Informo ao controlador para iniciar o processo de altera��o
		try {
			ctrl.iniciarCasoDeUsoAlterarFuncionario(this.objetos.get(pos));
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
	 * @see viewer.UICadastroDepartamentos#solicitarTerminoDeManterDepartamentos()
	 */
	@Override
	public void solicitarTerminoDeManterFuncionarios() {
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
		
	/**
	 * Exemplo de manipula��o de altera��es no JTable
	 * @param e
	 */
	public void tratarModificacaoNaTabela(TableModelEvent e) {
        if(e.getType() != TableModelEvent.UPDATE)
        	return;
		int linha = e.getFirstRow();
        int coluna = e.getColumn();
 
        TableModel model = (TableModel) e.getSource();
 
        System.out.println("Voc� alterou a linha " + linha + ", coluna " + coluna);
        System.out.println("Valor da c�lula alterada: " + model.getValueAt(linha, coluna));
	}
}

// Exemplo de como tratar eventos atrav�s de TableModelListener
//this.tableModel.addTableModelListener(new TableModelListener() {
//    public void tableChanged(TableModelEvent e) {
//    	tratarModificacaoNaTabela(e);
//    }
//});	
// Obtendo � referencia para a 5 coluna da tabela
// TableColumn column = minhaTabela.getColumnModel().getColumn(4);
// Criando o ComboBox
// JComboBox comboSexo = new JComboBox();
// Definindo os valores para o ComboBox
// DefaultComboBoxModel comboModel = new DefaultComboBoxModel(new String[] { "Masculino", "Feminino" });
// comboSexo.setModel(comboModel);
// Associando o ComboBox para a coluna
// column.setCellEditor(new DefaultCellEditor(comboSexo));
