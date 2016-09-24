package modelo;
//teste commit
import java.util.Set;

public class Cliente extends Pessoa {
	
	Set<Veiculo> veiculos;
	Set<OrdemDeServico> ordemServicos;

	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cliente(String nome, String cpf) {
		super(nome, cpf);
		// TODO Auto-generated constructor stub
	}

	
}
