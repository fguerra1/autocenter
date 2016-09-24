package modelo;

import java.util.Set;
import java.util.TreeSet;

public class Veiculo {

	String placa;
	String modelo;
	Cliente proprietario;
	Set<OrdemDeServico> ordemDeServicos;

	public Veiculo(String placa, String modelo) {
		super();
		this.setPlaca(placa);
		this.setModelo(modelo);
		this.ordemDeServicos = new TreeSet<OrdemDeServico>();
		this.proprietario = new Cliente();
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Cliente getProprietario() {
		return proprietario;
	}

	public void setProprietario(Cliente cliente) {
		if (this.proprietario == cliente)
			return;
		if (cliente == null) {
			Cliente antigo = this.proprietario;
			cliente.removeVeiculo(this);
		} else {
			if (this.proprietario != null)
				this.proprietario.removeVeiculo(this);
			    this.proprietario = proprietario;
			    cliente.addVeiculos(this);
		}
	}

	public void addOrdemServico(OrdemDeServico novaOS) {
		if (this.ordemDeServicos.contains(novaOS))
			return;
		this.ordemDeServicos.add(novaOS);
		novaOS.setVeiculo(this);
	}

	public void removeOrdemServico(OrdemDeServico oS) {
		if (!this.ordemDeServicos.contains(oS))
			return;
		this.ordemDeServicos.remove(oS);
		oS.setVeiculo(null);

	}

	@Override
	public String toString() {
		return "Veiculo [placa=" + placa + ", modelo=" + modelo + ", proprietario=" + proprietario
				+ ", ordemDeServicos=" + ordemDeServicos + "]";
	}

}
