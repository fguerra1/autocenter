package modelo;

import java.util.Set;
import java.util.TreeSet;

public class Cliente extends Pessoa {
	
	Set<Veiculo> veiculos;
	Set<OrdemDeServico> ordemServicos;

	public Cliente() {
		super();
		
	}

	public Cliente(String nome, String cpf) {
		super(nome, cpf);
		this.veiculos = new TreeSet<Veiculo>();
		this.ordemServicos = new TreeSet<OrdemDeServico>();
		
	}

	public Set<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void addVeiculos(Veiculo novoVeiculo) {
		if (this.veiculos.contains(novoVeiculo))
			return;
		this.veiculos.add(novoVeiculo);
		novoVeiculo.setProprietario(this);
	}
	
	public void removeVeiculo (Veiculo veiculo){
		if (! this.veiculos.contains(veiculo))
			return;
		this.veiculos.remove(veiculo);
		veiculo.setProprietario(null);
		
	}

	public Set<OrdemDeServico> getOrdemServicos() {
		return ordemServicos;
	}

	public void addOrdemServico(OrdemDeServico novaOS) {
		if (this.ordemServicos.contains(novaOS))
			return;
		this.ordemServicos.add(novaOS);
		novaOS.setCliente(this);
	}

	public void removeOrdemServico(OrdemDeServico oS) {
		if (!this.ordemServicos.contains(oS))
			return;
		this.ordemServicos.remove(oS);
		oS.setCliente(null);

	}
	
	

	
}
