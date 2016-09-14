package modelo;


public class Contato {

	String telefone;
	String email;
	Pessoa pessoa;
	

	public Contato(String telefone, String email, Pessoa pessoa) {
		super();
		this.telefone = telefone;
		this.email = email;
		this.setPessoa(pessoa);
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
				
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Contato [telefone=" + telefone + ", email=" + email
				+ ", pessoa=" + pessoa + "]";
	}
	
	

}
