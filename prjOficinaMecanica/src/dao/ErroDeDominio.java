package dao;

public class ErroDeDominio {
	private int id;
	private String mensagem;
	private Class classedeOrigem;

	public ErroDeDominio(String mensagem) {
		super();
		//this.id = id;
		//this.classedeOrigem = classedeOrigem;
		this.mensagem = mensagem;
	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Class getClassedeOrigem() {
		return classedeOrigem;
	}

	public void setClassedeOrigem(Class classedeOrigem) {
		this.classedeOrigem = classedeOrigem;
	}

	@Override
	public String toString() {
		return "Erro #" + id + ": " + mensagem + " (Origem: " + classedeOrigem + ")";
	}

}
