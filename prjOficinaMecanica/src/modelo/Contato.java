package modelo;

public class Contato {

	String telefone;
	String email;
	Pessoa pessoa;

	public Contato(String telefone, String email) {
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

	public void setTelefone(String telefone) {
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

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Contato [telefone=" + telefone + ", email=" + email + ", pessoa=" + pessoa + "]";
	}

}
