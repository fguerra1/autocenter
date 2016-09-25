package modelo;

public class Contato {

	String telefone;
	String email;
	Pessoa pessoa;

	public Contato(String telefone, String email) throws DadosException {
		super();
		this.setTelefone(telefone);
		this.setEmail(email);
		this.pessoa = new Pessoa();
	}

	public Contato() {

	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone)throws DadosException {
		Contato.validarTelefone(telefone);
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		if (this.pessoa == pessoa)
			return;
		if (pessoa == null) {
			Pessoa antigo = this.pessoa;
			pessoa.removeContato(this);
		} else {
			if (this.pessoa != null)
				this.pessoa.removeContato(this);
			this.pessoa = pessoa;
			pessoa.addContato(this);
		}
	}

	public void setEmail(String email) throws DadosException{
		Contato.validarEmail(email);
		this.email = email;
	}

	@Override
	public String toString() {
		return "Contato [telefone=" + telefone + ", email=" + email + ", pessoa=" + pessoa + "]";
	}

	
	public static void validarTelefone(String telefone) throws DadosException {
		if (telefone.length() < 8)
			throw new DadosException(new ErroDeDominio(8, "O telefone está no formato incorreto!", Contato.class));
		if (telefone==null || telefone.length() == 0)
			throw new DadosException(new ErroDeDominio(9, "O telefone não pode ser nulo!", Contato.class));
}
	public static void validarEmail(String email) throws DadosException{
		if(email.indexOf('@') <= 0 )
			throw new DadosException(new ErroDeDominio(10, "O email deve conter o @dominio!", Contato.class));
	}
}


