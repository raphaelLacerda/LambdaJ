package br.com.caelum.dominio;

public class Palestra {

	private final String		titulo;
	private final Palestrante	palestrante;
	private final int			quantidadeDeVotos;

	public Palestra(String titulo, Palestrante palestrante, int quantidadeDeVotos) {

		this.titulo = titulo;
		this.palestrante = palestrante;
		this.quantidadeDeVotos = quantidadeDeVotos;
	}

	public Palestrante getPalestrante() {

		return palestrante;
	}

	public String getTitulo() {

		return titulo;
	}

	public int getQuantidadeDeVotos() {

		return quantidadeDeVotos;
	}

}
