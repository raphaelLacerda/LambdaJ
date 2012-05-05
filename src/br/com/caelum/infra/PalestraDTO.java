package br.com.caelum.infra;

import java.io.Serializable;

public class PalestraDTO implements Serializable {

	private static final long	serialVersionUID	= 1L;

	public PalestraDTO(int id, String nome) {

		super();
		this.id = id;
		this.nome = nome;
	}

	private final int		id;
	private final String	nome;

	public int getId() {

		return id;
	}

	public String getNome() {

		return nome;
	}

	@Override
	public String toString() {

		return "PalestraDTO [id=" + id + ", nome=" + nome + "]";
	}

}
