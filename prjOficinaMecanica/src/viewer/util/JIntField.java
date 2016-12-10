package viewer.util;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class JIntField extends JTextField {
	
	public int getValue() throws InterfaceException{
		int resultado;
		try {
			resultado = Integer.parseInt(this.getText());
		}
		catch(NumberFormatException nfe) {
			this.setBackground(Color.RED);
			JOptionPane.showMessageDialog(this, "Valor Inteiro Inv�lido");
			this.setBackground(Color.WHITE);
			throw new InterfaceException(new ErroDeInterface("Valor Inteiro Inv�lido"));
		}
		return resultado;
	}
	
	public void setValue(int value) {
		this.setText(Integer.toString(value));
	}

}
